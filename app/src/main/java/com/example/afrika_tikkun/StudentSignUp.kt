package com.example.afrika_tikkun

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.afrika_tikkun.databinding.ActivitySignUpPageStudentBinding
import com.google.firebase.auth.FirebaseAuth

class StudentSignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpPageStudentBinding
    private lateinit var studentFirebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpPageStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentFirebaseAuth = FirebaseAuth.getInstance()

        binding.home.setOnClickListener {
            val intent = Intent(this, LoadScreen::class.java)
            startActivity(intent)
        }

        binding.textAcc.setOnClickListener {
            val intent = Intent(this, StudentLogin::class.java)
            startActivity(intent)
        }

        binding.signupButton.setOnClickListener {
            val email = binding.Email.text.toString().trim()
            val password = binding.PasswordText.text.toString()
            val confirmPassword = binding.ConPassword.text.toString()

            if (email.isNotEmpty()&& password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    studentFirebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(this, StudentLogin::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this,
                                    "Registration failed: ${task.exception?.message}",
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
                    "Invalid email format. Use example@afrikatikkun.edu.za",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // ...
}