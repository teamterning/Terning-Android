package com.terning.feature.mypage

import android.content.Context
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun WebViewScreen(
    navController: NavHostController,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val mUrl = "https://abundant-quiver-13f.notion.site/69109213e7db4873be6b9600f2f5163a"

    val webView = remember {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true // DOM storage 활성화
            webViewClient = CustomWebViewClient()
            loadUrl(mUrl)
        }
    }

    AndroidView(
        factory = { webView },
        update = { webView ->
            webView.loadUrl(mUrl)
        }
    )

}

class CustomWebViewClient : WebViewClient() {
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return if (url != null && url.startsWith("https://google.com")) {
            true
        } else {
            view?.loadUrl(url ?: "")
            false
        }
    }
}
