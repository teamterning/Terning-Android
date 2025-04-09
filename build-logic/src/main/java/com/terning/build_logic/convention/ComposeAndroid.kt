package com.terning.build_logic.convention

import com.android.build.gradle.LibraryExtension
import com.terning.build_logic.extension.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureComposeAndroid() {
    with(plugins) {
        apply("org.jetbrains.kotlin.plugin.compose")
    }

    val libs = extensions.libs

    dependencies {
        add("implementation", libs.findLibrary("androidx.compose.material3").get())
        add("implementation", libs.findLibrary("androidx.compose.ui").get())
        add("implementation", libs.findLibrary("compose.ui.tooling").get())
        add("implementation", libs.findLibrary("hilt.navigation.compose").get())
        add("implementation", libs.findLibrary("androidx.navigation.compose").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose.android").get())
        add("implementation", libs.findLibrary("androidx.compose.saveable").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
    }

    extensions.getByType<ComposeCompilerGradlePluginExtension>().apply {
        enableStrongSkippingMode.set(true)
        includeSourceInformation.set(true)
    }

    extensions.getByType<LibraryExtension>().apply {
        buildFeatures.compose = true
    }

}
