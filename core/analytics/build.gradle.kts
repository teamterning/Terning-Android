import java.util.Properties

plugins {
    alias(libs.plugins.terning.library)
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
//    setNamespace("core.analytics")
    namespace = "com.terning.core.analytics"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk =  libs.versions.minSdk.get().toInt()

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
        jvmTarget =  libs.versions.jvmTarget.get()
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}