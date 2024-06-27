plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-kapt")

//    id ("com.android.application")
//    id ("kotlin-android")
//    id ("dagger.hilt.android.plugin")
    id ("kotlinx-serialization")
}

android {
    namespace = "com.terning.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

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
    buildFeatures{
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))

    // <1> AndroidXDependencies
    implementation(libs.hilt.android)
    // security
    implementation(libs.androidx.core.ktx)

    // <2> KotlinDependencies
    implementation(libs.kotlin)
//    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coroutines.android)
    // datetime

    // <3> ThirdPartyDependencies
    implementation(libs.retrofit.core)
    implementation(libs.okhttp)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logging)
//    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.timber)

    // <4> TestDependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
//    implementation "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1") // 호환되는 최신 버전 사용
    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // original
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)


}