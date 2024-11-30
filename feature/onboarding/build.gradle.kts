import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.onboarding")
}

dependencies {
    // domain
    implementation(project(":domain:auth"))
    implementation(project(":domain:token"))

    implementation(libs.kakao.user)
}