plugins {
    id("android-app-features")
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
}