import java.nio.file.Path
import java.nio.file.Paths

// We need to change directory to where the project actually was generated.
Path projectPath = Paths.get(request.getOutputDirectory(), request.getArtifactId())
// Now we need to initialize and create all the git version control stuff
"git -C $projectPath init .".execute()
def url = "https://www.gitignore.io/api/vim,osx,linux,emacs,nanoc,eclipse,windows,intellij,jetbrains,sublimetext,intellij+iml,intellij+all,jetbrains+iml,jetbrains+all,microsoftoffice,exercism,visualstudio,visualstudiocode,openframeworks+visualstudio,serverless,java,java-web,code-java"
//"curl '$url' -o '$projectPath/.gitignore'".execute()
"git -C $projectPath add *".execute()
"git -C $projectPath commit -am 'Initial commit\n" +
        "Generated via `mvn archetype:generate -DarchetypeGroupId=io.paulbaker.archetypes -DarchetypeArtifactId=project-quickstart`"