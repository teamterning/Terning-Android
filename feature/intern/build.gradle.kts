import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.intern")
}

dependencies {
    // domain
    implementation(projects.domain.intern)

    // feature
    implementation(projects.feature.dialog)

    testImplementation(libs.bundles.androidx.compose.ui.test)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.coroutines.test)
    testImplementation(projects.core.testing)
}