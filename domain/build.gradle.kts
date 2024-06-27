plugins {
    id("java-library")
    id("kotlin-kapt")
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