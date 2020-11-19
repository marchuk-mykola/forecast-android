buildscript {
    val kotlinVersion by extra("1.4.0")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(GradlePlugins.androidBuildTools)
        classpath(GradlePlugins.kotlin)
//        classpath(GradlePlugins.navigationComponentSafeArgs)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.1.0")
    }
}