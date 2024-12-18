import com.terning.build_logic.convention.configureHiltAndroid
import com.terning.build_logic.convention.configureSerializationAndroid
import com.terning.build_logic.extension.libs

plugins {
    id("terning.android.library")
}

android {
    packaging {
        resources {
            excludes.add("META-INF/**")
        }
    }
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureHiltAndroid()
configureSerializationAndroid()

dependencies {
    val libs = project.extensions.libs

    // modules
    implementation(project(":core:network"))

    // ThirdPartyDependencies
    implementation(libs.findLibrary("timber").get())
    implementation(libs.findLibrary("retrofit.core").get())
    implementation(libs.findLibrary("okhttp").get())
    implementation(platform(libs.findLibrary("okhttp.bom").get()))
    implementation(libs.findLibrary("okhttp.logging").get())
    implementation(libs.findLibrary("retrofit2.kotlinx.serialization.converter.v080").get())
}