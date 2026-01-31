import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

buildkonfig {
    packageName = "th.skylabmek.kmp_frontend.core.common"
    objectName = "AppBuildKonfig"

    defaultConfigs {
        buildConfigField("String", "PROFILE_ID", "profile_001")
        buildConfigField("String", "APP_ID", "website_main_001")
        buildConfigField("Boolean", "IS_PRODUCTION", "false")
    }

    defaultConfigs("release") {
        buildConfigField("Boolean", "IS_PRODUCTION", "true")
    }
}
