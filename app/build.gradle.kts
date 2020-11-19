plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

repositories {
    jcenter()
    google()
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion = Versions.buildToolsVersion
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)

        applicationId = Versions.appId
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(core)
    implementation(domain)
    implementation(data)
    implementation(feature_forecast)
    implementation(feature_search)

    // Navigation
    implementation(Libs.navigation.navigationKtx)
    implementation(Libs.navigation.navigationUi)

    // Kotlin
    implementation(Libs.kotlin)

    // AndroidX
    implementation(Libs.androidX.coreKtx)
    implementation(Libs.androidX.appCompat)

    // UI libraries
    implementation(Libs.ui.materialComponents)
    implementation(Libs.androidX.constraintLayout)
    implementation(Libs.androidX.recyclerView)

    // Timber
    implementation(Libs.timber.timber)

    // Coroutines
    implementation(Libs.coroutines.coroutinesCore)
    implementation(Libs.coroutines.coroutinesAndroid)

    // Glide
    implementation(Libs.ui.glide)
    kapt(Libs.ui.glideKapt)

    // Koin
    implementation(Libs.Koin.koinFragment)
    implementation(Libs.Koin.koinScope)
    implementation(Libs.Koin.koinViewModel)
    implementation(Libs.Koin.koinExtensions)

}