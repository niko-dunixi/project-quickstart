# Maven Project Quickstart

Generating a new Java Maven project is as simple as running 
`mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart`,
but I frequently find that I'm making essentially the same changes to 
the default project over and over again. I pull in the same dependencies,
plugins, and properties. Let's remove some friction and make our own archetype
for generating projects the way I find the most sane and simple.

## Usage

With maven installed, simply run `mvn archetype:generate -DarchetypeGroupId=io.paulbaker.archetypes -DarchetypeArtifactId=project-quickstart`
to generate your new project.
