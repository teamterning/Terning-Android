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

}