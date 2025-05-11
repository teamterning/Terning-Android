import com.terning.build_logic.extension.setNamespace
import java.util.Properties

plugins {
    alias(libs.plugins.terning.feature)
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    buildFeatures {
        buildConfig = true
    }

    setNamespace("feature.main")
    
    defaultConfig {
        buildConfigField(
            "String",
            "NATIVE_APP_KEY",
            properties.getProperty("native.app.key"),
        )
    }
}

dependencies {
    // core
    implementation(projects.core.navigator)
    implementation(projects.core.firebase)
    implementation(projects.core.designsystem)

    // feature
    implementation(projects.feature.calendar)
    implementation(projects.feature.dialog)
    implementation(projects.feature.filtering)
    implementation(projects.feature.home)
    implementation(projects.feature.intern)
    implementation(projects.feature.mypage)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.search)
    implementation(projects.feature.splash)
}