import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.home")
}

dependencies {
    // domain
    implementation(project(":domain:home"))
    implementation(project(":domain:mypage"))

    // feature
    implementation(project(":feature:dialog"))

    // paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
}