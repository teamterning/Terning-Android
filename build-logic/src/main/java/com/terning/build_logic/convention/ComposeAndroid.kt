package com.terning.build_logic.convention

import com.android.build.gradle.BaseExtension
import com.terning.build_logic.extension.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureComposeAndroid() {

    val libs = extensions.libs

    extensions.getByType<BaseExtension>().apply {
        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion("kotlinCompilerExtensionVersion").get().requiredVersion
        }
    }

    extensions.getByType<BaseExtension>().apply {
        buildFeatures.compose = true
    }

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
}
