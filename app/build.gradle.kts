import com.terning.build_logic.extension.setNamespace
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.terning.application)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.service)
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

    setNamespace("point")
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.terning.point"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            "String",
            "NATIVE_APP_KEY",
            properties.getProperty("native.app.key"),
        )
        manifestPlaceholders["NATIVE_APP_KEY"] =
            properties.getProperty("nativeAppKey")
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
            proguardFiles("baseline-profiles-rules.pro")
        }
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // feature
    implementation(projects.feature.main)

    // data
    implementation(projects.data.auth)
    implementation(projects.data.calendar)
    implementation(projects.data.filtering)
    implementation(projects.data.home)
    implementation(projects.data.intern)
    implementation(projects.data.mypage)
    implementation(projects.data.scrap)
    implementation(projects.data.token)
    implementation(projects.data.tokenreissue)
    implementation(projects.data.search)
    implementation(projects.data.update)

    // baseline profile
    baselineProfile(projects.baselineprofile)
    implementation(libs.androidx.profileinstaller)

    implementation(libs.timber)
    implementation(libs.kakao.user)
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging)
}
