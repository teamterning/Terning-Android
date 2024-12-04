import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.main")
}

dependencies {
    // feature
    implementation(project(":feature:calendar"))
    implementation(project(":feature:dialog"))
    implementation(project(":feature:filtering"))
    implementation(project(":feature:home"))
    implementation(project(":feature:intern"))
    implementation(project(":feature:mypage"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:search"))

    //domain
    implementation(project(":domain:token"))
}