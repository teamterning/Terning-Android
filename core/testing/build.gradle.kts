plugins {
    alias(libs.plugins.terning.library)
}

android {
    namespace = "com.watcha.testing"
}

dependencies {
    api(libs.kotlinx.coroutines.test)

    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)
}