import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}
android {
    setNamespace("data.home")
}

dependencies {
    // domain
    implementation(project(":domain:home"))
}