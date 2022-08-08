plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
//
//    implementation("androidx.core:core-ktx:1.7.0")
//    implementation("androidx.appcompat:appcompat:1.3.0")
//    implementation("com.google.android.material:material:1.4.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation(project(":base-android"))
    implementation(project(":base-ui-compose"))

    implementation(Libs.FIREBASE_AUTH)
    implementation(platform(Libs.FIREBASE_BOM))
    // coroutine
    implementation(Libs.COROUTINES)
    implementation(Libs.COROUTINES_CORE)
    // hilt
    implementation(Libs.HILT_LIBRARY)
    kapt(Libs.HILT_COMPILER)

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

    implementation(Libs.TIMBER)
}