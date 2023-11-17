package com.example.afrika_tikkun

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebsiteDisplayActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_website_display)

        webView = findViewById(R.id.webView)

        // Enable JavaScript
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Set a WebViewClient to open links within the WebView
        webView.webViewClient = WebViewClient()

        // Retrieve the website URL from Intent
        val websiteUrl = intent.getStringExtra("WEBSITE_URL")

        // Load the website URL
        if (websiteUrl != null) {
            webView.loadUrl(websiteUrl)
        }
    }
}