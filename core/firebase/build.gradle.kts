import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.library)
}

android {
    setNamespace("core.firebase")
}

dependencies {
    //core
    implementation(projects.core.navigator)
    implementation(projects.core.designsystem)
    implementation(projects.core.analytics)

    // domain
    implementation(projects.domain.mypage)

    // timber
    implementation(libs.timber)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.config)

    // coil
    implementation(libs.coil.compose)
}