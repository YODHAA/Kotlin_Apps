package com.smartherd.autocomplete.progressbar

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.smartherd.autocomplete.R
import com.smartherd.autocomplete.service_class.toast
import kotlinx.android.synthetic.main.progress_bar_layout.*


class ProgressBar1 : AppCompatActivity() {

    private val url = "https://en.savefrom.net/"
    private val PermissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermission

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progress_bar_layout);

        // Initialize a list of required permissions to request runtime
        val list = listOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        // Initialize a new instance of ManagePermissions class
        managePermissions = ManagePermission(this, list, PermissionsRequestCode)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) managePermissions.checkPermissions()

        // Get the web view settings instance
        val settings = web_view.settings

        // Enable java script in web view
        settings.javaScriptEnabled = true

        // Enable and setup web view cache
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCachePath(cacheDir.path)

        // Enable zooming in web view
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true


        // Zoom web view text
        settings.textZoom = 125


        // Enable disable images in web view
        settings.blockNetworkImage = false
        // Whether the WebView should load image resources
        settings.loadsImagesAutomatically = true


        // More web view settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }
        //settings.pluginState = WebSettings.PluginState.ON
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false


        // More optional settings, you can enable it by yourself
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccess = true

        // WebView settings
        web_view.fitsSystemWindows = true


        /*
            if SDK version is greater of 19 then activate hardware acceleration
            otherwise activate software acceleration
        */
        web_view.setLayerType(View.LAYER_TYPE_HARDWARE, null)


        // Set web view client
        web_view.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
                toast("Page loading.")
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                // Display the loaded page title in a toast message
                toast("Page loaded: ${view.title}")
            }
        }


        // Set web view chrome client
        web_view.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progress_bar.progress = newProgress
            }
        }


        // Set web view download listener

        web_view.setDownloadListener(DownloadListener { url,
                                                        userAgent,
                                                        contentDescription,
                                                        mimetype,
                                                        contentLength ->

            // Initialize download request
            val request = DownloadManager.Request(Uri.parse(url))

            // Get the cookie
            val cookies = CookieManager.getInstance().getCookie(url)

            // Add the download request header
            request.addRequestHeader("Cookie", cookies)
            request.addRequestHeader("User-Agent", userAgent)

            // Set download request description
            request.setDescription("Downloading requested file....")

            // Set download request mime tytpe
            request.setMimeType(mimetype)

            // Allow scanning
            request.allowScanningByMediaScanner()

            // Download request notification setting
            request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )

            // Guess the file name
            val fileName = URLUtil.guessFileName(url, contentDescription, mimetype)

            // Set a destination storage for downloaded file
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

            // Set request title
            request.setTitle(URLUtil.guessFileName(url, contentDescription, mimetype));


            // DownloadManager request more settings
            request.setAllowedOverMetered(true)
            request.setAllowedOverRoaming(false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                request.setRequiresCharging(false)
                request.setRequiresDeviceIdle(false)
            }
            request.setVisibleInDownloadsUi(true)


            // Get the system download service
            val dManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            // Finally, request the download to system download service
            dManager.enqueue(request)
        })


        // Load button click listener
        button_load.setOnClickListener {
            // Load url in a web view
            web_view.loadUrl(url)
        }

    }


    // Receive the permissions request result
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PermissionsRequestCode -> {
                val isPermissionsGranted = managePermissions
                    .processPermissionsResult(requestCode, permissions, grantResults)

                if (isPermissionsGranted) {
                    // Do the task now
                    toast("Permission(s) granted.")
                } else {
                    toast("Permission(s) denied.")
                }
                return
            }
        }
    }


    // Handle back button press in web view
    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            // If web view have back history, then go to the web view back history
            web_view.goBack()
            toast("Going to back history")
        } else {
            super.onBackPressed()
        }
    }
}


// Extension function to show toast message
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}





