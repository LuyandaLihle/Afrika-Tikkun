package com.example.afrika_tikkun

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.afrika_tikkun.databinding.ActivitySelectionPageBinding

class SelectionPage : AppCompatActivity() {
    private lateinit var binding: ActivitySelectionPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_page)

        binding = ActivitySelectionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStudentLogin.setOnClickListener {
            val intent = Intent(this, StudentSignUp::class.java)
            startActivity(intent)
        }
        binding.btnTeacherLogin.setOnClickListener {
            val intent = Intent(this, TeacherSignUpPage::class.java)
            startActivity(intent)
        }
        binding.btnDonorLogin.setOnClickListener {
            val intent = Intent(this, DonorSignUpPage::class.java)
            startActivity(intent)
        }
        binding.btnVolunteerLogin.setOnClickListener {
            val intent = Intent(this, VolunteerSignUpPage::class.java)
            startActivity(intent)
        }
        binding.btnHelpLogin.setOnClickListener {
            val intent = Intent(this, PsychologicalHelpSignUpPage::class.java)
            startActivity(intent)
        }
    }

}