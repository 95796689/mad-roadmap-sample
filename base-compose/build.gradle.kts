plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("com.google.firebase.crashlytics")
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILE
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
    // compose
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_PREVIEW)
    implementation(Libs.COMPOSE_TEST)
    implementation(Libs.COMPOSE_TOOL)
    implementation(Libs.COMPOSE_FOUNDATION)
    implementation(Libs.COMPOSE_ICON_CORE)
    implementation(Libs.COMPOSE_ICON_EXTEND)
    // lifecycle
    implementation(Libs.LIFECYCLE_KTX)
}