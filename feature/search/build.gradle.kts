import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}
android {
    setNamespace("feature.search")
}

dependencies {
    // domain
    implementation(project(":domain:search"))

    // feature
    implementation(project(":feature:dialog"))
}