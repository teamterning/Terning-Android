import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.update")
}

dependencies {
    // core
    implementation(projects.core.firebase)

    // domain
    implementation(projects.domain.update)

}