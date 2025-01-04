import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.auth")
}

dependencies {
    // domain
    implementation(projects.domain.auth)
}