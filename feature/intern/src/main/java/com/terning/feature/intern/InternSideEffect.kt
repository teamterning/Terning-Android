package com.terning.feature.intern

import androidx.annotation.StringRes
import com.terning.domain.intern.entity.InternInfo

sealed class InternSideEffect {
    data class Toast(@StringRes val message: Int) :
        InternSideEffect()

    data class ShareKakaoTalk(val internInfo: InternInfo) : InternSideEffect()
}