package com.example.afrika_tikkun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.afrika_tikkun.databinding.ActivitySelectionPageBinding
import com.example.afrika_tikkun.databinding.ActivitySelectionPageLoginBinding
import  com.example.afrika_tikkun.databinding.ActivityLoginRegisterBinding

class SelectionPageLogin : AppCompatActivity() {
    private lateinit var binding: ActivitySelectionPageLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_page_login)

        binding = ActivitySelectionPageLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStudentLogin.setOnClickListener {
            val intent = Intent(this, StudentLogin::class.java)
            startActivity(intent)
        }
        binding.btnTeacherLogin.setOnClickListener {
            val intent = Intent(this, TeacherLoginPage::class.java)
            startActivity(intent)
        }
        binding.btnDonorLogin.setOnClickListener {
            val intent = Intent(this, DonorLoginPage::class.java)
            startActivity(intent)
        }
        binding.btnVolunteerLogin.setOnClickListener {
            val intent = Intent(this, VolunteerLoginPage::class.java)
            startActivity(intent)
        }
        binding.btnHelpLogin.setOnClickListener {
            val intent = Intent(this, PsychologicalHelpLoginPage::class.java)
            startActivity(intent)
        }
    }

}