import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.onboarding")
}

dependencies {
    // domain
    implementation(projects.domain.auth)
    implementation(projects.domain.user)

    // kakao
    implementation(libs.kakao.user)
}