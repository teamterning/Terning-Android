import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.filtering")
}

dependencies {
    // domain
    implementation(project(":domain:filtering"))
    implementation(project(":domain:token"))
}