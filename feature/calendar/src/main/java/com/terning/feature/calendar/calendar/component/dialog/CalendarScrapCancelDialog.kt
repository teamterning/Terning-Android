package com.terning.feature.calendar.calendar.component.dialog

import androidx.compose.runtime.Composable
import com.terning.feature.dialog.cancel.ScrapCancelDialog

/**
 * 달력 스크랩 취소 다이얼로그
 *
 * @param scrapVisibility 스크랩 취소 다이얼로그 가시 여부
 * @param internshipAnnouncementId 스크랩 취소를 진행할 공고 ID
 * @param onDismissCancelDialog 스크랩 취소 다이얼로그 끄기
 */

@Composable
internal fun CalendarScrapCancelDialog(
    scrapVisibility: Boolean,
    internshipAnnouncementId: Long?,
    onDismissCancelDialog: (Boolean) -> Unit,
) {
    if (scrapVisibility) {
        internshipAnnouncementId?.run {
            ScrapCancelDialog(
                internshipAnnouncementId = this,
                onDismissRequest = onDismissCancelDialog
            )
        }
    }
}
