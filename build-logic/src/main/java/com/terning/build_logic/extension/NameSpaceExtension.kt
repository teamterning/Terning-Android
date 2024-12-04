package com.terning.build_logic.extension

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    extensions.findByType(BaseExtension::class.java)?.apply {
        namespace = "com.terning.$name"
    }
}