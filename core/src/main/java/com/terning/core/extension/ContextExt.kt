package com.terning.core.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.shortToast(@StringRes message: Int) {
    Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}