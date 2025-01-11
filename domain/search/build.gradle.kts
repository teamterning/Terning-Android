plugins {
    alias(libs.plugins.terning.kotlin)
}

dependencies {
    implementation(libs.paging.common)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.test)
}