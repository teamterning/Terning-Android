plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "terning.android.hilt"
            implementationClass = "com.terning.build-logic.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "terning.kotlin.hilt"
            implementationClass = "com.terning.build-logic.HiltKotlinPlugin"
        }
    }
}