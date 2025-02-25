plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
    compileOnly(libs.compose.compiler.extension)
}

tasks.named("compileTestKotlin") {
    dependsOn("clean")
}

tasks.named("processTestResources") {
    dependsOn("compileTestKotlin")
}

tasks.named("testClasses") {
    dependsOn("processTestResources")
}