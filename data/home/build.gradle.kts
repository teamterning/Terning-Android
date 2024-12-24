import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.home")
}

dependencies {
    // domain
    implementation(projects.domain.home)
}