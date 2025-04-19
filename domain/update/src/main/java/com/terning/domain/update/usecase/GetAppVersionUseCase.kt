package com.terning.domain.update.usecase

import com.terning.domain.update.entity.UpdateState
import com.terning.domain.update.repository.UpdateRepository
import net.swiftzer.semver.SemVer
import javax.inject.Inject

class GetUpdateStateUseCase @Inject constructor(
    private val updateRepository: UpdateRepository
) {
    suspend operator fun invoke(
        version: String?,
        callback: (info: UpdateState) -> Unit
    ) {
        updateRepository.fetchLatestAppVersion { updateMessage ->
            val currentAppVersion = SemVer.parse(version ?: DEFAULT_APP_VERSION)
            val latestAppVersion = SemVer.parse(updateMessage.version)

            val updateState: UpdateState =
                if (currentAppVersion >= latestAppVersion) {
                    UpdateState.NoUpdateAvailable
                } else {
                    if (currentAppVersion.major < latestAppVersion.major) {
                        UpdateState.MajorUpdateAvailable(
                            title = updateMessage.majorUpdateTitle,
                            content = updateMessage.majorUpdateBody
                        )
                    } else if (currentAppVersion.minor < latestAppVersion.minor) {
                        UpdateState.MajorUpdateAvailable(
                            title = updateMessage.majorUpdateTitle,
                            content = updateMessage.majorUpdateBody
                        )
                    } else {
                        UpdateState.PatchUpdateAvailable(
                            title = updateMessage.patchUpdateTitle,
                            content = updateMessage.patchUpdateBody,
                        )
                    }
                }

            callback(updateState)
        }
    }


    companion object {
        private const val DEFAULT_APP_VERSION = "9.9.9"
    }
}