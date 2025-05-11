package com.terning.core.designsystem.util

import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class KakaoUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val templateId = 118600L

    fun shareToKakaoTalk(templateArgs: Map<String, String>) {
        if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
            ShareClient.instance.shareCustom(
                context,
                templateId,
                templateArgs
            ) { sharingResult, error ->
                if (error != null) {
                    Timber.e("카카오톡 공유 실패: ${error.message}")
                } else if (sharingResult != null) {
                    context.startActivity(sharingResult.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            }
        } else {
            val sharerUrl = WebSharerClient.instance.makeCustomUrl(templateId, templateArgs)
            try {
                val intent = CustomTabsIntent.Builder().build().intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.data = sharerUrl
                context.startActivity(intent)
            } catch (e: Exception) {
                Timber.e("웹 공유 실패: ${e.message}")
            }
        }
    }
}
