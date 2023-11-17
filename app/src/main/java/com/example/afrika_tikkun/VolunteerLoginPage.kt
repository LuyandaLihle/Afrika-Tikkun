package com.example.afrika_tikkun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.afrika_tikkun.databinding.ActivityLoginPageVolunteerBinding
import com.google.firebase.auth.FirebaseAuth

class VolunteerLoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageVolunteerBinding
    private lateinit var firebaseauth: FirebaseAuth
    private lateinit var selectedRole: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginPageVolunteerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SignUpTxt.setOnClickListener {
            val intent = Intent(this, VolunteerSignUpPage::class.java)
            startActivity(intent)
        }
        binding.ForgetPasswordTxt.setOnClickListener {
            val intent = Intent(this, ForgetPassword::class.java)
            startActivity(intent)
        }
        firebaseauth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            val email = binding.EmailTxt.text.toString()
            val password = binding.passwordTxt.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){

                firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this,VolunteerMainPage::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}