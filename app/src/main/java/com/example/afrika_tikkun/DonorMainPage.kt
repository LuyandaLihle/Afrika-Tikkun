package com.example.afrika_tikkun

import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DonorMainPage : AppCompatActivity() {

    private lateinit var individual: Button
    private lateinit var business: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_main_page) // Set the XML layout as the content view

        individual = findViewById(R.id.individual)
        business = findViewById(R.id.business)

        individual.setOnClickListener {
            openWebsite("https://afrikatikkun.org/donation/individual/")
        }

        business.setOnClickListener {
            openWebsite("https://afrikatikkun.org/donation/business/#donation")
        }
    }

    // Move the function outside of the onCreate method
    private fun openWebsite(websiteUrl: String) {
        val intent = Intent(this, WebsiteDisplayActivity::class.java)
        intent.putExtra("WEBSITE_URL", websiteUrl)
        startActivity(intent)
    }
}
