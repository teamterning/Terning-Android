import com.terning.build_logic.convention.configureKotlin

plugins {
    kotlin("jvm")
}

kotlin {
    jvmToolchain(8)
}

configureKotlin()