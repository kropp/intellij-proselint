Proselint for IntelliJ-based IDEs
========================================

Get it from plugin repository: https://plugins.jetbrains.com/plugin/9854-proselint

Plugin integrates [Proselint](https://github.com/amperser/proselint) in the IDE.


### Development

The plugin is built using Gradle and uses [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin)
to integrate with IntelliJ Platform.
 
To build a plugin run

```
$ ./gradlew buildPlugin
```

Plugin zip file will be created in `build/distributions`

To test plugin in IDE run `./gradlew runIdea`
 
### Contribution

Plugin is written in [Kotlin](http://kotlinlang.org/).
