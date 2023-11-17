package com.example.afrika_tikkun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.afrika_tikkun.databinding.ActivityLoadScreenBinding

class LoadScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLoadScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageButton.setOnClickListener {
            val intent = Intent(this, LoginOrRegister::class.java)
            startActivity(intent)
        }
    }

}