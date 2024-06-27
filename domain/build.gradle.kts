plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    // <1> KotlinDependencies
    implementation(libs.kotlin)
    implementation(libs.coroutines.android)
    // datetime
}