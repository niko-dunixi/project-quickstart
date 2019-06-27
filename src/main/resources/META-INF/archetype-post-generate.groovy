import java.nio.file.Paths

dir = Paths.get(request.getOutputDirectory(), request.getArtifactId()).toFile()

def run(String... cmd) {
//    println ' -> ' + cmd
    def process = cmd.execute(null, dir)
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
run("mvn", "-N", "io.takari:maven:wrapper")
//run("./mvnw sortpom:sort")
//run("./mvnw license:update-file-header@config")
//run("./mvnw license:update-file-header@sources")
//run("./mvnw fmt:format")

// Now we need to initialize and create all the git version control stuff
run("git", "init", ".")
run("git", "add", ".")
String commitMessage = "Initial commit\n" +
        "Generated via mvn archetype:generate -DarchetypeGroupId=io.paulbaker.archetypes -DarchetypeArtifactId=project-quickstart\n" +
        "Report issues: https://github.com/paul-nelson-baker/project-quickstart"
run("git", "commit", "-am", commitMessage)