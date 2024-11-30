import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.intern")
}

dependencies {
    // domain
    implementation(project(":domain:intern"))

    // feature
    implementation(project(":feature:dialog"))
}