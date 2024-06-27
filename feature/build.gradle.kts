plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
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
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewModelCompose )
    // lifecycleJava8
    // splashScreen
    // pagingRuntime
    // workManager

    // <3> KaptDependencies
    implementation(libs.hilt.compiler)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.manager)

    // <4>
    implementation(libs.material)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui.tooling.preview)

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