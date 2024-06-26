plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
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
}

dependencies {
    implementation(project(":domain"))

    // <1> AndroidXDependencies
    implementation(libs.hilt.android)
    // security
    implementation(libs.androidx.core.ktx)

    // <2> KotlinDependencies
    // kotlin
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coroutines.android)
    // datetime

    // <3> ThirdPartyDependencies
    implementation(libs.retrofit.core)
    // okhttp
    // okHttpBom
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.timber)

    // <4> TestDependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // original
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)

}