import com.terning.build_logic.convention.configureHiltAndroid
import com.terning.build_logic.convention.configureSerializationAndroid
import com.terning.build_logic.extension.libs

plugins {
    id("terning.android.library")
    id("terning.android.compose")
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
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:analytics"))

    // ThirdPartyDependencies
    implementation(libs.findLibrary("coil.compose").get())
    implementation(libs.findLibrary("coil.network.okhttp").get())
    implementation(libs.findLibrary("timber").get())
    implementation(libs.findLibrary("lottie").get())
    implementation(libs.findLibrary("accompanist.systemuicontroller").get())
    implementation(libs.findLibrary("androidx.browser").get())
    implementation(libs.findLibrary("kotlin.collections.immutable").get())
}
