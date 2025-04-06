
import androidx.annotation.StringRes

sealed class InternViewSideEffect {
    data class Toast(@StringRes val message: Int) :
        InternViewSideEffect()
}