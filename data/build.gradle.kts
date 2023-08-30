plugins {
    id("android-features")
}
android {
    namespace = "com.jokejournal.data"
}

dependencies {
    //remote
    implementation(libs.bundles.remote.deps)

    //local
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.ktx)
    testImplementation(libs.room.testing)
    kapt(libs.room.compiler)

    implementation(project(":domain"))
}