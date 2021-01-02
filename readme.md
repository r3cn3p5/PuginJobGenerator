# Autosys Job Generator Plugin

## gradle.build

### Apply plugin

```
apply plugin: AutosysJobGeneratorPlugin
```

### Configuration



```
jobGeneratorSettings {
    cfgFile = 'gemfire/autosys-config/gemfire-config.yaml'
}
```

### Running on build

```
build.dependsOn autosysJobGenerator
```