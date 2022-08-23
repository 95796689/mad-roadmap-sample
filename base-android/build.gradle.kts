plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // hilt
    implementation(Libs.HILT_LIBRARY)
    kapt(Libs.HILT_COMPILER)
    // firebase
    implementation(Libs.FIREBASE_CRASHLYTICS)
    implementation(Libs.FIREBASE_ANALYTICS)
    implementation(platform(Libs.FIREBASE_BOM))
    implementation(Libs.FIREBASE_DATABASE)
    // coroutine
    implementation(Libs.COROUTINES)
    implementation(Libs.COROUTINES_CORE)
    // timber
    implementation(Libs.TIMBER)
}