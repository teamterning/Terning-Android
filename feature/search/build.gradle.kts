import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.search")
}

dependencies {
    // domain
    implementation(projects.domain.search)

    // feature
    implementation(projects.feature.dialog)
}