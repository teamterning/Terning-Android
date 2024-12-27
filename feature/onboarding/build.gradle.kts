import com.terning.build_logic.extension.configureComposeMetrics
import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.onboarding")
    configureComposeMetrics("onboarding")
}

dependencies {
    // domain
    implementation(projects.domain.auth)
    implementation(projects.domain.token)

    implementation(libs.kakao.user)
}