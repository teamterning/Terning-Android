import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.library)
    alias(libs.plugins.terning.compose)
    alias(libs.plugins.dokka)
}

android {
    setNamespace("core.designsystem")
}

tasks.dokkaHtml.configure {
    dokkaSourceSets {
        named("main") {
            includes.from("docs.md")
            noAndroidSdkLink.set(false)
        }
    }
}

dependencies {
    implementation(libs.lottie)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}