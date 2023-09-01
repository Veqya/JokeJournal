plugins {
    id("android-app-features")
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.jokejournal.android"
    defaultConfig {
        applicationId = "com.jokejournal.android"
    }
}
dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    androidTestImplementation(libs.androidx.navigation.testing)
    implementation(libs.bundles.navigation.fragment.deps)

    implementation(libs.bundles.remote.deps)

    implementation(libs.androidx.navigation.fragment)
}