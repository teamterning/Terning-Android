import com.terning.build_logic.extension.setNamespace
import java.util.Properties

plugins {
    alias(libs.plugins.terning.library)
    alias(libs.plugins.kotlin.serialization)
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    signingConfigs {
        create("release") {
            keyAlias = properties.getProperty("release.keyAlias")
            keyPassword = properties.getProperty("release.keyPassword")
            storeFile = file(properties.getProperty("release.storeFile") + "/terning/terning.jks")
            storePassword = properties.getProperty("release.storePassword")
        }
    }

    setNamespace("core.network")

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                properties.getProperty("test.base.url")
            )
        }
        release {
            buildConfigField(
                "String",
                "BASE_URL",
                properties.getProperty("base.url")
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // core
    implementation(projects.core.local)

    //domain
    implementation(projects.domain.tokenreissue)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.timber)
    implementation(libs.process.phoenix)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    implementation(platform(libs.okhttp.bom))
}