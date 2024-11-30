import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.mypage")
}

dependencies {
    // domain
    implementation(project(":domain:mypage"))
}