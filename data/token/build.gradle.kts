import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.token")
}

dependencies {
    // core
    implementation(project(":core:local"))

    //domain
    implementation(project(":domain:token"))
}