package com.example.imdbapp.presentation.ui.web


import android.content.pm.ActivityInfo
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.viewinterop.AndroidView
import com.example.imdbapp.presentation.ui.util.LockScreenOrientation


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WebViewLandScape(url : String?){
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    if (url != null) {
        AndroidView(factory = {
            val apply = android.webkit.WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                webViewClient = WebViewClient()

                loadUrl(url)
            }
            apply
        }, update = {
            it.settings.javaScriptEnabled = true
            it.loadUrl(url)
        })
    }
}