plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    // <1> KotlinDependencies
    // kotlin
    implementation(libs.coroutines.android)
    // datetime
}