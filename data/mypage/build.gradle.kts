import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.mypage")
}

dependencies {
    // domain
    implementation(projects.domain.mypage)
}