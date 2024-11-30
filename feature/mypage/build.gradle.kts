import com.terning.build_logic.setNamespace

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
    implementation(project(":domain:mypage"))
    implementation(project(":domain:token"))

    implementation(libs.kakao.user)
}