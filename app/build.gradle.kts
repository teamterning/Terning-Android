import java.util.Properties
import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.application)
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

    // feature
    implementation(project(":feature:main"))

    // data
    implementation(project(":data:auth"))
    implementation(project(":data:calendar"))
    implementation(project(":data:filtering"))
    implementation(project(":data:home"))
    implementation(project(":data:intern"))
    implementation(project(":data:mypage"))
    implementation(project(":data:scrap"))
    implementation(project(":data:token"))
    implementation(project(":data:tokenreissue"))

    // core
    implementation(project(":core:network"))
    implementation(project(":core:local"))
    implementation(project(":core:common"))

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
