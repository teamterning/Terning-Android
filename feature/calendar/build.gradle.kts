import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.calendar")
}

dependencies {
    // feature
    implementation(project(":feature:dialog"))

    //domain
    implementation(project(":domain:calendar"))
}