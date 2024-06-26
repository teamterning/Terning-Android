plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.terning.feature"
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
    implementation(project(":core"))
    implementation(project(":domain"))

    // <1> KotlinDependencies
    // kotlin
    implementation(libs.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    // datetime

    // <2> AndroidXDependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    // constraintLayout
    // startup
    // legacy
    // security
    implementation(libs.hilt.android)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // lifecycleJava8
    // splashScreen
    // pagingRuntime
    // workManager
    // hiltWorkManager

    // <3> KaptDependencies
    implementation(libs.hilt.compiler)
    // hiltWorkManagerCompiler

    // <4>
    implementation(libs.material)
    implementation(libs.androidx.material3)

    // <5> TestDependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // <6> ThirdPartyDependencies
    implementation(libs.compose.coil)
    implementation(libs.timber)
    // ossLicense
    // progressView
    // balloon
    // lottie
    // circularProgressBar
    // circleIndicator
    // flexbox
    // circleImageView

}