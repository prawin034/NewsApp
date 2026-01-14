plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.skyapp.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.skyapp.newsapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // -> RETROFIT
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // -> OKHTTP
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // -> COMPOSE NAVIGATION
    implementation(libs.androidx.navigation.compose)

    // -> COROUTINES
    implementation(libs.kotlinx.coroutines.core)

    // -> VIEW MODELS
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // -> MATERIAL ICONS
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material3.window.size)

    // -> LIVE DATA
    implementation(libs.androidx.runtime.livedata)

    // -> COIL
    implementation(libs.coil.compose)

    // -> HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


    // -> Room Db

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.19.0")

    // Google fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.8.2")
}