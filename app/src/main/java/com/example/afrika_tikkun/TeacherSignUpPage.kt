package com.example.afrika_tikkun

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.afrika_tikkun.TeacherLoginPage
import com.google.firebase.auth.FirebaseAuth
import com.example.afrika_tikkun.databinding.ActivitySignUpPageTeacherBinding

class TeacherSignUpPage : AppCompatActivity(){

        private lateinit var binding: ActivitySignUpPageTeacherBinding
        private val emailPattern = "[a-zA-Z0-9._-]+@afrikatikkun.co.za".toRegex()
        private lateinit var teacherFirebaseAuth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivitySignUpPageTeacherBinding.inflate(layoutInflater)
            setContentView(binding.root)

            teacherFirebaseAuth = FirebaseAuth.getInstance()

            binding.home.setOnClickListener {
                val intent = Intent(this, LoadScreen::class.java)
                startActivity(intent)
            }

            binding.textAcc.setOnClickListener {
                val intent = Intent(this, TeacherLoginPage::class.java)
                startActivity(intent)
            }

            binding.signupButton.setOnClickListener {
                val email = binding.Email.text.toString()
                val password = binding.PasswordText.text.toString()
                val pass = binding.ConPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty() && pass.isNotEmpty()) {
                    if (password == pass) {
                        teacherFirebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val intent = Intent(this, TeacherLoginPage::class.java)
                                    startActivity(intent)
                                } else {
                                    Log.e("TeacherSignUpPage", "Signup failed: ${task.exception}")
                                    Toast.makeText(
                                        this,
                                        "Signup failed. Please try again.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else {
                        Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show()
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

