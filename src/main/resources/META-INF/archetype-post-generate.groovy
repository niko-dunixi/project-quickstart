import java.nio.file.Paths

dir = Paths.get(request.getOutputDirectory(), request.getArtifactId()).toFile()

def run(String... cmd) {
    try {
        mustRun(cmd)
    } catch (Exception ignored) {
    }
}

def mustRun(String... cmd) {
    def process = cmd.execute(null, dir)
//    println ' -> ' + cmd
//    def outputBuilder = new StringBuilder()
//    process.waitForProcessOutput(outputBuilder, System.err)
//    String output = outputBuilder.toString()
//            .replaceAll("\n{2,}", "\n") // Replace two or more new lines with just one...
//            .replaceAll("\n", "\n\t") // Replace each new line with a new line and a tab...
//            .trim() // remove any excess
//    if (!output.isEmpty()) {
//        println '\t' + output
//    }
    process.waitForProcessOutput((Appendable) System.out, System.err)
    if (process.exitValue() != 0) {
        throw new Exception("Command '$cmd' exited with code: ${process.exitValue()}")
    }
}


// Make project portable.
mustRun("mvn", "-N", "io.takari:maven:wrapper")

// Update the .gitignore if possible, but don't fail if the curl fails
run("curl", "https://www.gitignore.io/api/vim,osx,linux,emacs,nanoc,eclipse,windows,intellij,jetbrains,sublimetext,intellij+iml,intellij+all,jetbrains+iml,jetbrains+all,microsoftoffice,exercism,visualstudio,visualstudiocode,openframeworks+visualstudio,serverless,java,java-web,code-java",
    "-o", ".gitignore")

// Now we need to initialize and create all the git version control stuff
mustRun("git", "init", ".")
mustRun("git", "add", ".")
String commitMessage = "Initial commit\n" +
        "Generated via mvn archetype:generate -DarchetypeGroupId=io.paulbaker.archetypes -DarchetypeArtifactId=project-quickstart\n" +
        "Report issues: https://github.com/paul-nelson-baker/project-quickstart"
mustRun("git", "commit", "-am", commitMessage)