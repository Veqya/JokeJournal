import extensions.configureAndroidLib

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

configureAndroidLib()

