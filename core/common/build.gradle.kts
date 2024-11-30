import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.library)
    alias(libs.plugins.terning.compose)
}

android {
    setNamespace("core.common")
}

dependencies {
    implementation(project(":core:designsystem"))
}