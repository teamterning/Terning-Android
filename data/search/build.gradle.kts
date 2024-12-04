import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.search")
}

dependencies {
    // domain
    implementation(project(":domain:search"))
}