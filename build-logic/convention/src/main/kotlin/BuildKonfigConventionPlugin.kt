import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class BuildKonfigConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.codingfeline.buildkonfig")

            extensions.configure<BuildKonfigExtension> {
                packageName = "th.skylabmek.kmp_frontend.core.common"
                objectName = "AppBuildKonfig"

                defaultConfigs {
                    buildConfigField(Type.STRING, "PROFILE_ID", "profile_001")
                    buildConfigField(Type.STRING, "APP_ID", "website_main_001")
                    buildConfigField(Type.BOOLEAN, "IS_PRODUCTION", "false")
                }

                defaultConfigs("release") {
                    buildConfigField(Type.BOOLEAN, "IS_PRODUCTION", "true")
                }

                // Define target-specific configurations
                targetConfigs {
                    create("wasmJs") {
                        // Inherits IS_PRODUCTION = false from defaultConfigs
                    }
                    create("js") {
                        // Inherits IS_PRODUCTION = false from defaultConfigs
                    }
                }

                // Explicitly configure release flavor for specific targets if needed
                targetConfigs("release") {
                    create("wasmJs") {
                        buildConfigField(Type.BOOLEAN, "IS_PRODUCTION", "true")
                    }
                    create("js") {
                        buildConfigField(Type.BOOLEAN, "IS_PRODUCTION", "true")
                    }
                }
            }
        }
    }
}
