import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.intern")
}

dependencies {
    // domain
    implementation(projects.domain.intern)
}