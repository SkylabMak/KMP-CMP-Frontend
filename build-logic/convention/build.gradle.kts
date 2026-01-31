plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.android.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    // Add BuildKonfig and its compiler to the classpath
    implementation(libs.buildkonfig.gradlePlugin)
    implementation(libs.buildkonfig.compiler)
}

gradlePlugin {
    plugins {
        register("buildKonfig") {
            id = "skylabmek.buildkonfig"
            implementationClass = "BuildKonfigConventionPlugin"
        }
    }
}
