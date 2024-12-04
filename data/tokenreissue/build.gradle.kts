import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.tokenreissue")
}

dependencies {
    //domain
    implementation(project(":domain:tokenreissue"))
}