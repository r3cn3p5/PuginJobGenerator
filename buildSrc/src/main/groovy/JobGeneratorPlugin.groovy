import org.gradle.api.Plugin
import org.gradle.api.Project
import org.yaml.snakeyaml.Yaml

class JobGeneratorPlugin implements Plugin<Project> {
    void apply(Project project) {

        project.extensions.create("jobGeneratorSettings", JobGeneratorPluginExtension)

        project.task('jobGenerator') {
            doLast {
                println("Autosys Job Generator started")

                def aFile = new File("${project.jobGeneratorSettings.cfgFile}")
                def cfg = new Yaml().load(aFile.newInputStream() )

                println ("NAS base directory: " + cfg.NASbaseDirectory)

                // This clunky will replace
                File adir = new File("${project.buildDir}/autosys")
                adir.mkdir()

                for (environment in cfg.enviroments) {
                    println "Environment: " + environment.name

                    File dir = new File("${project.buildDir}/autosys/${environment.name}")
                    dir.mkdir()


                    // for each locator
                    for (locator in environment.locators) {

                        println "Locator: " + locator
                        // Backup job
                        File file = new File("${project.buildDir}/autosys/${environment.name}/${environment.name}-backup-${locator}.JIL")
                        file.write"Backup file for - " + environment.name + "-" + locator


                    }

                    // for each node
                    for (node in environment.nodes) {
                        println "Node: " + node

                        // Restore job
                        File file = new File("${project.buildDir}/autosys/${environment.name}/${environment.name}-restore-${node}.JIL")
                        file.write"Restore file for - " + environment.name + "-" + node

                    }
                }

            }
        }
    }
}

class JobGeneratorPluginExtension {
    String cfgFile
    String outputDirectory
}