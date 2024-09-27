import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
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

    namespace = "com.terning.point"
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature"))

    // KotlinDependencies
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coroutines.android)
    implementation(libs.kotlin)

    // AndroidXDependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.manager)
    implementation(libs.androidx.workManager)
    implementation(libs.androidx.hiltWorkManager)
    implementation(libs.hilt.compiler)
    implementation(libs.androidx.hiltWorkManagerCompiler)

    // TestDependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // ThirdPartyDependencies
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.timber)
    implementation(libs.ossLicense)
    implementation(libs.process.phoenix)

    // Compose Preview
    implementation(libs.compose.ui.tooling)

    // KakaoDependencies
    implementation(libs.kakao.user)
}
