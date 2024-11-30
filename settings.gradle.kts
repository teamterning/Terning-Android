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

include(":feature")
include(":data")
include(":domain")
include(":core")
include(":feature:calendar")
include(":feature:dialog")
include(":feature:filtering")
include(":feature:home")
include(":feature:intern")
include(":feature:main")
include(":feature:mypage")
include(":feature:onboarding")
include(":feature:search")
include(":domain:auth")
include(":domain:calendar")
include(":domain:filtering")
include(":domain:home")
include(":domain:intern")
include(":domain:mypage")
include(":domain:onboarding")
include(":domain:search")
include(":domain:tokenreissue")
include(":domain:token")
include(":domain:scrap")
include(":data:auth")
include(":data:calendar")
include(":data:filtering")
include(":data:home")
include(":data:intern")
include(":data:mypage")
include(":data:search")
include(":data:tokenreissue")
include(":data:token")
include(":data:scrap")
include(":core:analytics")
include(":core:designsystem")
include(":core:navigation")
include(":core:network")
include(":core:local")
