import com.terning.build_logic.setNamespace

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
}