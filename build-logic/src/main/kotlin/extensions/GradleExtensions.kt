package extensions

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies

fun Project.configureAndroidApp() {
    androidApp {
        compileSdk = ANDROID_COMPILE_SDK_VERSION
        defaultConfig {
            minSdk = ANDROID_MIN_SDK_VERSION
            targetSdk = ANDROID_TARGET_SDK_VERSION
            versionCode = ANDROID_VERSION_CODE
            versionName = ANDROID_VERSION_NAME

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = COMPILE_TARGET
            targetCompatibility = COMPILE_TARGET
        }
        kotlinOptionsAndroidApp {
            jvmTarget = JVM_TARGET
        }
    }

    kapt {
        correctErrorTypes = true
    }

    dependencies {
        implementation(libs.core.ktx)
        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.test.ext.junit)
        androidTestImplementation(libs.espresso.core)
        implementation(libs.hilt.android)
        kapt(libs.hilt.android.compiler)
    }
}

fun Project.configureAndroidLib() {
    androidLib {
        compileSdk = ANDROID_COMPILE_SDK_VERSION
        defaultConfig {
            minSdk = ANDROID_MIN_SDK_VERSION
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = COMPILE_TARGET
            targetCompatibility = COMPILE_TARGET
        }
        kotlinOptionsAnndroidLib {
            jvmTarget = JVM_TARGET
        }
    }

    kapt {
        correctErrorTypes = true
    }

    dependencies {
        implementation(libs.core.ktx)
        implementation(libs.appcompat)
        implementation(libs.material)

        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.test.ext.junit)
        androidTestImplementation(libs.espresso.core)

        runtimeOnly(libs.androidx.lifecycle.viewmodel.ktx)


        implementation(libs.hilt.android)
        kapt(libs.hilt.android.compiler)
    }
}

private val Project.`libs`: LibrariesForLibs
    get() = (this as ExtensionAware).extensions.getByName("libs") as LibrariesForLibs

private fun Project.androidApp(configure: Action<BaseAppModuleExtension>): Unit =
    (this as ExtensionAware).extensions.configure("android", configure)

private fun Project.androidLib(configure: Action<LibraryExtension>): Unit =
    (this as ExtensionAware).extensions.configure("android", configure)

private fun BaseAppModuleExtension.`kotlinOptionsAndroidApp`(configure: Action<org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions>): Unit =
    (this as ExtensionAware).extensions.configure("kotlinOptions", configure)

private fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.`testImplementation`(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.`androidTestImplementation`(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun Project.`kapt`(configure: Action<org.jetbrains.kotlin.gradle.plugin.KaptExtension>): Unit =
    (this as ExtensionAware).extensions.configure("kapt", configure)

private fun DependencyHandler.`kapt`(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun LibraryExtension.`kotlinOptionsAnndroidLib`(configure: Action<org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions>): Unit =
    (this as ExtensionAware).extensions.configure("kotlinOptions", configure)

fun DependencyHandler.`runtimeOnly`(dependencyNotation: Any): Dependency? =
    add("runtimeOnly", dependencyNotation)
