import com.terning.build_logic.convention.configureHiltAndroid
import com.terning.build_logic.convention.configureSerializationAndroid
import com.terning.build_logic.extension.libs

plugins {
    id("terning.android.library")
}

android {
    packaging {
        resources {
            excludes.add("META-INF/**")
        }
    }
}

configureHiltAndroid()
configureSerializationAndroid()

dependencies {
    val libs = project.extensions.libs

    // modules
    implementation(project(":core:network"))

    // ThirdPartyDependencies
    implementation(libs.findLibrary("timber").get())
    implementation(libs.findBundle("retrofit").get())
}