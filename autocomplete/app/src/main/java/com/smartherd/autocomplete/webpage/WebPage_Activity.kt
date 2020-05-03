package com.smartherd.autocomplete.webpage

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.smartherd.autocomplete.R


class WebPage_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_layout)

        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = MyWebViewClient(this)
        webView.loadUrl("https://www.indiatoday.in/news.html")
    }

    class MyWebViewClient constructor(private val activity: Activity) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.CUR_DEVELOPMENT)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
            Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }

    }
}