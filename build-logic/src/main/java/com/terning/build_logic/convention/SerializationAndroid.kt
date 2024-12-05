package com.terning.build_logic.convention

import com.terning.build_logic.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureSerializationAndroid() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.plugin.serialization")
    }

    val libs = extensions.libs
    dependencies {
        add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
    }
}