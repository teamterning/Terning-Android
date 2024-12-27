import com.terning.build_logic.extension.configureComposeMetrics
import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.filtering")
    configureComposeMetrics()
}

dependencies {
    // domain
    implementation(projects.domain.filtering)
    implementation(projects.domain.token)
}