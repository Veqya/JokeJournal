plugins {
    kotlin("jvm")
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.converter.gson)
    runtimeOnly(libs.androidx.room.common)
}