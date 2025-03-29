import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.feature)
}

android {
    setNamespace("feature.main")
}

dependencies {
    // core TODO: 의존성 넣기
    // implementation(projects.core.firebase)

    // feature
    implementation(projects.feature.calendar)
    implementation(projects.feature.dialog)
    implementation(projects.feature.filtering)
    implementation(projects.feature.home)
    implementation(projects.feature.intern)
    implementation(projects.feature.mypage)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.search)

    //domain
    implementation(projects.domain.token)
}