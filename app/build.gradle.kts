import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // add more
}

android {
    namespace = "com.terning.point"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.terning.point"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField(
            "String",
            "OPEN_BASE_URL",
            gradleLocalProperties(rootDir, providers).getProperty("open.base.url")
        )
    }

    buildTypes {
//        debug {
//            buildConfigField(
//                "String",
//                "OPEN_BASE_URL",
//                gradleLocalProperties(rootDir, providers).getProperty("open.base.url")
//            )
//        }
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
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature"))

    // <1> KotlinDependencies
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coroutines.android)
    //kotlin

    // <2> AndroidXDependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)
    // workManager
    // hiltWorkManager

    // <3> KaptDependencies
    implementation(libs.hilt.compiler)
    // hiltWorkManagerCompiler

    // <4> TestDependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // <5> ThirdPartyDependencies
    // okHttpBom
    // okHttp
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    // retrofitJsonConverter
    implementation(libs.timber)
    // ossLicense

    // debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // original
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

}
