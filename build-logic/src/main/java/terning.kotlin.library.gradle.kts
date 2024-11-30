import com.terning.build_logic.configureKotlin

plugins {
    kotlin("jvm")
}

kotlin {
    jvmToolchain(8)
}

configureKotlin()