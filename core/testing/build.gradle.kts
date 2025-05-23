import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.library)
}

android {
    setNamespace("testing")
}

dependencies {
    api(libs.kotlinx.coroutines.test)
    api(libs.hilt.android.testing)
    api(libs.mockito.kotlin)
    api(libs.coroutines.test)

    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)

    testImplementation(libs.bundles.androidx.compose.ui.test)
}