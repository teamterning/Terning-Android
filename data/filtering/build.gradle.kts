import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.filtering")
}

dependencies {
    // domain
    implementation(project(":domain:filtering"))
}