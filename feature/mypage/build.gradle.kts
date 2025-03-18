import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.mypage")

    defaultConfig {
        buildConfigField("String", "VERSION_NAME", "\"${libs.versions.versionName.get()}\"")
    }
    buildFeatures {
        buildConfig = true
        compose = true // TODO : delete
    }
}

dependencies {
    // domain
    implementation(projects.domain.mypage)
    implementation(projects.domain.token)

    implementation(libs.kakao.user)
}