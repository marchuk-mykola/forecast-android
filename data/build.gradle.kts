plugins {
    id("com.android.library")
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
    // Kotlin
    implementation(Libs.kotlin)

    // AndroidX
    implementation(Libs.androidX.coreKtx)
    implementation(Libs.androidX.appCompat)

    // Jetpack
    kapt(Libs.room.roomCompilerKapt)
    implementation(Libs.room.roomKtx)
    implementation(Libs.room.roomRuntime)

    // UI libraries
    implementation(Libs.ui.materialComponents)
    implementation(Libs.androidX.constraintLayout)
    implementation(Libs.androidX.recyclerView)

    // ViewModel
    implementation(Libs.androidX.viewModelKtx)
    implementation(Libs.androidX.viewModelExtensions)
    implementation(Libs.androidX.viewModelSavedState)

    // Timber
    implementation(Libs.timber.timber)

    // Retrofit
    implementation(Libs.retrofit.refrofit)
    implementation(Libs.retrofit.retrofitMoshiConverter)

    // Moshi
    kapt(Libs.moshi.moshiKapt)
    implementation(Libs.moshi.moshiKotlin)

    // Navigation
    implementation(Libs.ui.adapterDelegateKotlinDsl)
    implementation(Libs.ui.adapterDelegateKotlinDslViewBinding)

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

    implementation(domain)
    implementation(core)

    testImplementation(Libs.test.junit)
    testImplementation(Libs.test.junitExt)
    testImplementation(Libs.test.espresso)
    testImplementation(Libs.test.robolectric)
    testImplementation(Libs.test.mockK)
    testImplementation(Libs.test.truth)
    testImplementation(Libs.test.truthExt)
    testImplementation(Libs.test.androidxTestRules)
    testImplementation(Libs.test.androidxTestRunner)
    testImplementation(Libs.test.androidxTestCore)
    testImplementation(Libs.test.kotlinCoroutinesText)

}