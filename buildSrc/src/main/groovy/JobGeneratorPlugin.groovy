import org.gradle.api.Plugin;
import org.gradle.api.Project;

class JobGeneratorPlugin implements Plugin<Project> {
    void apply(Project project) {

        project.extensions.create("jobGeneratorSettings", JobGeneratorPluginExtension)

        project.task('jobGenerator') {
            doLast {
                println("Autosys Job Generator started")

                def aFile = new File("${project.jobGeneratorSettings.cfgFile}")
                def cfg = new org.yaml.snakeyaml.Yaml().load(aFile.newInputStream() )

                println "NAS base directory: " + cfg.NASbaseDirectory

                for (environment in cfg.enviroments) {
                    println "Enviroment: " + environment.name

                    // for each locator
                    for (locator in environment.locators) {

                        println "Locator: " + locator
                        // Backup job

                    }

                    // for each node
                    for (node in environment.nodes) {

                        println "Node: " + node
                        // Restore job

                    }
                }

            }
        }
    }
}

class JobGeneratorPluginExtension {
    def String cfgFile
}