package extensions

/**
 * An extension class for configuring the greeting module within the project.
 *
 * The `GreetExtension` class provides a configurable property `module` that specifies the name of the module that will
 * greet the user. This extension can be used within Gradle build scripts to customize the behavior of greeting-related
 * tasks or plugins.
 *
 * @property module The name of the module to be greeted. This property must be set
 * before executing any greeting-related tasks that depend on the module name.
 */
abstract class GreetExtension {
    /**
     * The name of the module that will greet the user.
     */
    abstract var module: String
}
