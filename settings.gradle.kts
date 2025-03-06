enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        // KakaoSDK repository
        maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}

rootProject.name = "Terning-Android"

include(":app")

// baseline profile
include(":baselineprofile")

// core
include(
    ":core:analytics",
    ":core:designsystem",
    ":core:navigation",
    ":core:network",
    ":core:local",
    ":core:testing"
)

// data
include(
    ":data:auth",
    ":data:calendar",
    ":data:filtering",
    ":data:home",
    ":data:intern",
    ":data:mypage",
    ":data:search",
    ":data:tokenreissue",
    ":data:token",
    ":data:scrap"
)

// domain
include(
    ":domain:auth",
    ":domain:calendar",
    ":domain:filtering",
    ":domain:home",
    ":domain:intern",
    ":domain:mypage",
    ":domain:search",
    ":domain:tokenreissue",
    ":domain:token",
    ":domain:scrap"
)

// feature
include(
    ":feature:calendar",
    ":feature:dialog",
    ":feature:filtering",
    ":feature:home",
    ":feature:intern",
    ":feature:main",
    ":feature:mypage",
    ":feature:onboarding",
    ":feature:search"
)