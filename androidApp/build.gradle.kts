import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}
sourceSets {
    dependencies {
        implementation(projects.shared)
        implementation("androidx.core:core-splashscreen:1.0.1")
        implementation(libs.androidx.activity.compose)

        implementation(libs.koin.core)
        implementation(libs.koin.compose)
        implementation(libs.koin.compose.viewmodel)
        implementation(libs.compose.uiToolingPreview)
        implementation(libs.firebase.common.ktx)
        debugImplementation(libs.compose.uiTooling)
        implementation(project(":shared"))
        implementation(project(":di"))
    }
}

android {
    namespace = "com.example.nutrisport"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.nutrisport"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}