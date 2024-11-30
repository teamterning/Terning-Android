import java.util.Properties
import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.library)
    alias(libs.plugins.terning.compose)
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    setNamespace("core.analytics")

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
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.amplitude)
    implementation(libs.timber)
}