package com.example.afrika_tikkun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.afrika_tikkun.databinding.ActivitySignUpPageBinding
import com.google.firebase.auth.FirebaseAuth

class PsychologicalHelpSignUpPage : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpPageBinding
    private lateinit var firebaseauth: FirebaseAuth
    private lateinit var selectedRole: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseauth = FirebaseAuth.getInstance()
        selectedRole = intent.getStringExtra("selectedRole") ?: ""

        binding.home.setOnClickListener {
            val intent = Intent(this, LoadScreen::class.java)
            startActivity(intent)
        }

        binding.textAcc.setOnClickListener {
            val intent = Intent(this, PsychologicalHelpLoginPage::class.java)
            startActivity(intent)
        }
        binding.signupButton.setOnClickListener {
            val email = binding.Email.text.toString()
            val password = binding.PasswordText.text.toString()
            val pass = binding.ConPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && pass.isNotEmpty()){
                if (password == pass){
                    firebaseauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this,PsychologicalHelpLoginPage::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this,"Oops password doesn't match!!!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Oops Empty fields are not allowed!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}