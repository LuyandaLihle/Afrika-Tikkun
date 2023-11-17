package com.example.afrika_tikkun

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.afrika_tikkun.databinding.ActivityLoginPageTeacherBinding
import com.google.firebase.auth.FirebaseAuth

class TeacherLoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageTeacherBinding
    private lateinit var teacherFirebaseAuth: FirebaseAuth
    private val emailPattern = "[a-zA-Z0-9._-]+@afrikatikkun.co.za".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginPageTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        teacherFirebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            val email = binding.EmailTxt.text.toString()
            val password = binding.passwordTxt.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                teacherFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, TeacherDashboard::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    "Invalid email format. Use example@afrikatikkun.co.za",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}