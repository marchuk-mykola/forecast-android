import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Versions {
    const val compileSdkVersion = 30
    const val targetSdkVersion = 30
    const val minSdkVersion = 23
    const val buildToolsVersion = "30.0.1"
    const val kotlin = "1.4.10"
    const val appId = "com.marchuk.forecast"
    const val versionCode = 1
    const val versionName = "1.0"
}

object GradlePlugins {
    private const val androidBuildToolsVersion = "4.1.1"

    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"
//    const val navigationComponentSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Libs.jetpack.navigationComponentVersion}"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    val ui = UI
    val androidX = AndroidX
    val room = Room
    val test = Test
    val retrofit = Retrofit
    val moshi = Moshi
    val coroutines = Coroutines
    val timber = Timber
    val navigation = Navigation

    object UI {
        private const val materialComponentsVersion = "1.1.0"
        private const val adapterDelegateVersion = "4.3.0"
        private const val glideVersion = "4.11.0"

        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val glideKapt = "com.github.bumptech.glide:compiler:$glideVersion"

        const val materialComponents = "com.google.android.material:material:$materialComponentsVersion"
        const val adapterDelegateKotlinDsl = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:$adapterDelegateVersion"
        const val adapterDelegateKotlinDslViewBinding =
            "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:$adapterDelegateVersion"
    }

    object Koin {
        private const val koinVersion = "2.2.0-rc-4"

        const val koinScope = "org.koin:koin-androidx-scope:$koinVersion"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
        const val koinFragment = "org.koin:koin-androidx-fragment:$koinVersion"
        const val koinExtensions = "org.koin:koin-androidx-ext:$koinVersion"
    }

    object Timber {
        private const val timberVersion = "4.7.1"

        // Timber
        const val timber = "com.jakewharton.timber:timber:$timberVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.8.1"

        const val refrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    }

    object Moshi {
        private const val moshiVersion = "1.11.0"

        const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    }

    object AndroidX {
        private const val constraintLayoutVersion = "2.0.4"
        private const val appCompatVersion = "1.2.0"
        private const val coreKtxVersion = "1.3.2"
        private const val recyclerViewVersion = "1.1.0"
        private const val viewModelVersion = "2.2.0"
        private const val swipeRefreshLayoutVersion = "1.1.0"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$viewModelVersion"
        const val viewModelExtensions = "androidx.lifecycle:lifecycle-extensions:$viewModelVersion"
        const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$viewModelVersion"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayoutVersion"
    }

    object Navigation {
        private const val navigationVersion = "2.3.1"

        const val navigationKtx = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    }

    object Coroutines {
        private const val coroutinesVersion = "1.4.0"

        // Kotlin Android Coroutines
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object Room {
        private const val roomVersion = "2.2.5"

        const val roomCompilerKapt = "androidx.room:room-compiler:$roomVersion"
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }

    object Test {
        private const val junitVersion = "4.13"
        private const val junitExtVersion = "1.1.1"
        private const val espressoVersion = "3.2.0"
        private const val robolectricVersion = "4.3.1"
        private const val mockKVersion = "1.9.3"
        private const val truthExtVersion = "1.3.0-alpha03"
        private const val truthVersion = "1.0"
        private const val androidxTestRunnerVersion = "1.3.0-alpha03"
        private const val coreTestingVersion = "2.1.0"
        private const val coroutinesTestVersion = "1.4.1"

        const val androidxTestRunner = "androidx.test:runner:$androidxTestRunnerVersion"
        const val androidxTestRules = "androidx.test:rules:$androidxTestRunnerVersion"
        const val androidxTestCore = "androidx.arch.core:core-testing:$coreTestingVersion"
        const val kotlinCoroutinesText = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"

        const val truth = "com.google.truth:truth:$truthVersion"
        const val truthExt = "androidx.test.ext:truth:$truthExtVersion"
        const val mockK = "io.mockk:mockk:$mockKVersion"
        const val junit = "junit:junit:$junitVersion"
        const val junitExt = "androidx.test.ext:junit:$junitExtVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val robolectric = "org.robolectric:robolectric:$robolectricVersion"
    }

}

inline val DependencyHandler.core get() = project(":app-core")
inline val DependencyHandler.domain get() = project(":domain")
inline val DependencyHandler.data get() = project(":data")
inline val DependencyHandler.feature_forecast get() = project(":feature-forecast")
inline val DependencyHandler.feature_search get() = project(":feature-search")


