import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.data)
}

android {
    setNamespace("data.search")
}

dependencies {
    // domain
    implementation(projects.domain.search)

    // paging
    implementation(libs.androidx.paging.common.android)
}