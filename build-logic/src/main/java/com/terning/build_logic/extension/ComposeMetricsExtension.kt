package com.terning.build_logic.extension

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun Project.configureComposeMetrics() {
    extensions.configure<BaseExtension> {
        (this as ExtensionAware).extensions.configure<KotlinJvmOptions> {
            freeCompilerArgs += listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${
                    projectDir.resolve("compose-reports").absolutePath
                }",
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${
                    projectDir.resolve("compose-reports").absolutePath
                }"
            )
        }
    }
}
