// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt.android.plugin) apply false
    alias(libs.plugins.com.android.library) apply false
}
buildscript {
    dependencies {
        classpath(":build-logic")
    }
}