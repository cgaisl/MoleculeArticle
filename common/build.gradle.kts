@file:Suppress("UnstableApiUsage")


plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("app.cash.molecule") version "1.2.1"
}

// cocoapods requires version
version = "1.0-SNAPSHOT"

kotlin {
    androidTarget()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
                implementation ("app.cash.turbine:turbine:1.0.0")
            }
        }
    }
}

android {
    namespace = "at.cgaisl.moleculearticle"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}
