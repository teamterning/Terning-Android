plugins {
    id("java-library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation(libs.kotlin)
    implementation(libs.coroutines.android)
}