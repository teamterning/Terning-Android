import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("featrue.dialog")
}

dependencies {
    // domain
    implementation(project(":domain:scrap"))
}