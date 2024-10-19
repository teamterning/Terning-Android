import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dokka)
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.terning.core"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            val devAmplitude = properties["amplitudeDevKey"] as? String ?: ""
            buildConfigField("String", "AMPLITUDE_KEY", devAmplitude)
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val prodAmplitude = properties["amplitudeProdKey"] as? String ?: ""
            buildConfigField("String", "AMPLITUDE_KEY", prodAmplitude)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
}

tasks.dokkaHtml.configure {
    dokkaSourceSets {
        named("main") {
            includes.from("docs.md")
            noAndroidSdkLink.set(false)
        }
    }
}

dependencies {
    // Kotlin
    implementation(libs.kotlin)

    // AndroidXDependencies
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.saveable)

    // Hilt
    implementation(libs.hilt.android)

    // Material Design
    implementation(libs.material)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)

    // Compose Preview
    implementation(libs.compose.ui.tooling)

    // Test Dependency
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //ThirdPartyDependencies
    implementation(libs.compose.coil)
    implementation(libs.okhttp)
    implementation(libs.lottie)
    implementation(libs.amplitude)
    implementation(libs.timber)
}