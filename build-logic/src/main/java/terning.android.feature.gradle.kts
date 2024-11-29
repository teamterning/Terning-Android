import com.terning.build_logic.configureHiltAndroid
import com.terning.build_logic.configureSerializationAndroid
import com.terning.build_logic.libs

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
    // todo: 정리하기
    val libs = project.extensions.libs

    // modules
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

//    // navigation
//    implementation(libs.findLibrary("hilt.navigation.compose").get())
//    implementation(libs.findLibrary("androidx.compose.navigation").get())
//    androidTestImplementation(libs.findLibrary("androidx.compose.navigation.test").get())
//
//    // lifecycle
//    implementation(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
//    implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())

    // ThirdPartyDependencies
    implementation(libs.findLibrary("coil.compose").get())
    implementation(libs.findLibrary("coil.network.okhttp").get())
    implementation(libs.findLibrary("timber").get())
    implementation(libs.findLibrary("ossLicense").get())
    implementation(libs.findLibrary("lottie").get())
    implementation(libs.findLibrary("process.phoenix").get())
    implementation(libs.findLibrary("accompanist.systemuicontroller").get())
    implementation(libs.findLibrary("androidx.core.splashscreen").get())
    implementation(libs.findLibrary("androidx.browser").get())

}
