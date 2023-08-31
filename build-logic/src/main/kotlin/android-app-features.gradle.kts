import extensions.configureAndroidApp

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

configureAndroidApp()

