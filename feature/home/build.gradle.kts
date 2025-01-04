import com.terning.build_logic.extension.configureComposeMetrics
import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.home")
}

dependencies {
    // domain
    implementation(projects.domain.home)
    implementation(projects.domain.mypage)

    // feature
    implementation(projects.feature.dialog)

    // paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
}