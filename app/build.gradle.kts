plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.app.moviesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.moviesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    android.buildFeatures.buildConfig = true

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String","BaseUrl","\"https://api.themoviedb.org/3/\"")
            //example : http://image.tmdb.org/t/p/w500/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg
            buildConfigField("String","FileUrl","\"http://image.tmdb.org/t/p/\"")
            buildConfigField("String","ApiKey","\"0a9fde0429b439d13975fb8dfd531bc0\"")
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String","BaseUrl","\"https://api.themoviedb.org/3/\"")
            buildConfigField("String","FileUrl","\"http://image.tmdb.org/t/p/\"")
            buildConfigField("String","ApiKey","\"0a9fde0429b439d13975fb8dfd531bc0\"")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)
//    implementation (libs.androidx.hilt.lifecycle.viewmodel)
    kapt (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    // Coil
    implementation(libs.coil.compose)

    // Nav controller
    implementation(libs.navigation.compose)
//    implementation("androidx.compose.material3:material3-android:1.2.1")

}