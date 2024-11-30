import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.library)
    alias(libs.plugins.terning.compose)
}

android {
    setNamespace("core.designsystem")
}

dependencies {
    implementation(libs.lottie)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}