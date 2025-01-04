import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.token")
}

dependencies {
    // core
    implementation(projects.core.local)

    //domain
    implementation(projects.domain.token)
}