import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.calendar")
}

dependencies {
    //domain
    implementation(project(":domain:calendar"))

    // feature
    implementation(project(":feature:dialog"))
}