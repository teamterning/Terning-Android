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
    }
}

dependencies {
    // domain
    implementation(projects.domain.mypage)
    implementation(projects.domain.user)

    // kakao
    implementation(libs.kakao.user)

    // permission
    implementation(libs.permissions)
}