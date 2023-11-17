package com.example.afrika_tikkun

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.afrika_tikkun.databinding.ActivityLoginPageStudentBinding
import com.google.firebase.auth.FirebaseAuth

class StudentLogin : AppCompatActivity () {
    private lateinit var binding: ActivityLoginPageStudentBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginPageStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            val email = binding.EmailTxt.text.toString().trim()
            val password = binding.passwordTxt.text.toString()

            if (isEmailValid(email) && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, StudentMainPage::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
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

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@afrikatikkun.edu.za")
    }
}