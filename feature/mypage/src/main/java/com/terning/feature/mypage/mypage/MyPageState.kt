package com.terning.feature.mypage.mypage

import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.type.AlarmType.DISABLED

data class MyPageState(
    val isGetSuccess: UiState<Boolean> = UiState.Loading,
    val name: String = "",
    val profileImage: String = "",
    val authType: String = "",
    val showNotice: Boolean = false,
    val showOpinion: Boolean = false,
    val showService: Boolean = false,
    val showPersonal: Boolean = false,
    val showLogoutBottomSheet: Boolean = false,
    val showQuitBottomSheet: Boolean = false,
    val showAlarmDialog: Boolean = false,
    val alarmStatus: String = DISABLED.value,
)
