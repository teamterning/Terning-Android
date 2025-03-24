import com.terning.build_logic.extension.setNamespace


plugins {
    alias(libs.plugins.terning.library)
}

android {
    setNamespace("core.firebase")
}

dependencies {
    implementation(libs.timber)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.config)
}