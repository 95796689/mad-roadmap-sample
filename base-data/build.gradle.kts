

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            kapt {
                arguments {
                    arg("room.schemaLocation", "$projectDir/schemas")
                    arg("room.incremental", "true")
                }
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":base-android"))

    // room
    implementation(Libs.ROOM_COROUTINE)
    implementation(Libs.ROOM_RUNTIME)
    implementation(Libs.ROOM_TEST)
    implementation(Libs.ROOM_PAGING)
    kapt(Libs.ROOM_COMPILER)
    // hilt
    implementation(Libs.HILT_LIBRARY)
    kapt(Libs.HILT_COMPILER)
    // firebase
    implementation(platform(Libs.FIREBASE_BOM))
    implementation(Libs.FIREBASE_DATABASE)
    // timber
    implementation(Libs.TIMBER)
}