plugins {
    id("android-lib-features")
}

android {
    namespace = "com.jokejournal.data"
}

dependencies {
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)

    implementation(libs.bundles.remote.deps)

    implementation(project(":domain"))
}
