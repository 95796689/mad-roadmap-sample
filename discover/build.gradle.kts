
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

    implementation(project(":base-android"))
    implementation(project(":base-compose"))
    implementation(project(":base-data"))

    // compose
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_PREVIEW)
    implementation(Libs.COMPOSE_TEST)
    implementation(Libs.COMPOSE_TOOL)
    implementation(Libs.COMPOSE_FOUNDATION)
    implementation(Libs.COMPOSE_ICON_CORE)
    implementation(Libs.COMPOSE_ICON_EXTEND)
    implementation(Libs.COMPOSE_ACTIVITY)
    implementation(Libs.COMPOSE_VIEW_MODEL)
    implementation(Libs.COMPOSE_HILT_NAVIGATION)
    // timber
    implementation(Libs.TIMBER)
    // hilt
    implementation(Libs.HILT_LIBRARY)
    kapt(Libs.HILT_COMPILER)
    testImplementation(Libs.HILT_TEST)
    kaptTest(Libs.HILT_TEST_COMPILER)
    // test
    testImplementation(Libs.JUNIT)
    testImplementation(Libs.ROBOLECTRIC)
    testImplementation(Libs.JUNIT_KTX)
    testImplementation(Libs.TEST_CORE)
    testImplementation(Libs.COROUTINES_TEST)
    testImplementation(Libs.HAMCREST)
    testImplementation(Libs.MOCKITO)

    implementation(Libs.FIREBASE_AUTH)
    implementation(platform(Libs.FIREBASE_BOM))
}