import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.splash")
}

dependencies {
    // core
    implementation(projects.core.designsystem)

    // domain
    implementation(projects.domain.update)
    implementation(projects.domain.token)

    implementation(libs.semver)
}