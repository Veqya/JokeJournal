plugins {
    `kotlin-dsl`
}
repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
}
dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.hilt.android.gradle.plugin)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}