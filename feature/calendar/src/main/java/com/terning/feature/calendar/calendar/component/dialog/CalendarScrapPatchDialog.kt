package com.terning.feature.calendar.calendar.component.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.extension.getFullDateStringInKorean
import com.terning.domain.calendar.entity.CalendarScrapDetail
import com.terning.feature.dialog.detail.ScrapDialog
import java.time.LocalDate

/**
 * 달력 스크랩 디테일 다이얼로그
 *
 * @param date 선택된 날짜
 * @param dialogVisibility 스크랩 디테일 다이얼로그 가시 여부
 * @param internshipModel 스크랩 디테일
 * @param navigateToAnnouncement 공고 상세로 이동하는 이동
 * @param onDismissInternDialog 스크랩 디테일 다이얼로그 끄기
 * @param onClickChangeColor 스크랩 색상 변경 시 발생하는 이벤트
 */

@Composable
internal fun CalendarScrapPatchDialog(
    date: LocalDate,
    dialogVisibility: Boolean,
    internshipModel: CalendarScrapDetail?,
    navigateToAnnouncement: (Long) -> Unit,
    onDismissInternDialog: (Boolean) -> Unit,
    onClickChangeColor: () -> Unit,
) {
    if (dialogVisibility && internshipModel != null) {
        val scrapColor = Color(
            android.graphics.Color.parseColor(
                internshipModel.color
            )
        )
        ScrapDialog(
            title = internshipModel.title,
            scrapColor = scrapColor,
            deadline = date.getFullDateStringInKorean(),
            startYearMonth = internshipModel.startYearMonth,
            workingPeriod = internshipModel.workingPeriod,
            internshipAnnouncementId = internshipModel.internshipAnnouncementId,
            companyImage = internshipModel.companyImage,
            isScrapped = true,
            onDismissRequest = onDismissInternDialog,
            onClickChangeColor = onClickChangeColor,
            onClickNavigateButton = navigateToAnnouncement
        )
    }
}
