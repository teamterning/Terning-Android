package com.terning.core.designsystem.extension

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.net.toUri
import timber.log.Timber

fun Context.toast(@StringRes message: Int) {
    Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
}

fun Context.stringToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(@StringRes message: Int) {
    Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
}

fun Context.getVersionName(): String? = runCatching {
    packageManager.getPackageInfo(packageName, 0)
}.fold(
    onSuccess = { it.versionName },
    onFailure = {
        Timber.e(it)
        null
    }
)

fun Context.launchPlayStore() = runCatching {
    val terningPlayStoreUri = "https://play.google.com/store/apps/details?id=com.terning.point".toUri()
    Intent(Intent.ACTION_VIEW).apply { data = terningPlayStoreUri }
}.onSuccess { playStoreIntent ->
    startActivity(playStoreIntent)
}.onFailure {
    Timber.e(it)
}