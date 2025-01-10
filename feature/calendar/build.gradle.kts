import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.calendar")
}

dependencies {
    //domain
    implementation(projects.domain.calendar)

    // feature
    implementation(projects.feature.dialog)
}