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
    // core
    implementation(projects.core.local)

    // domain
    implementation(projects.domain.mypage)
    implementation(projects.domain.token)

    // kakao
    implementation(libs.kakao.user)

    // firebase
    implementation(libs.permissions)
}