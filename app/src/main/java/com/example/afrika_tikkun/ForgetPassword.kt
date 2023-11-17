package com.example.afrika_tikkun
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
/*New code for forget password*/
class ForgetPassword : AppCompatActivity() {
    private lateinit var etPassword: EditText
    private lateinit var btnResetPassword: Button
    /*private lateinit var auth: FirebaseAuth*/
    private lateinit var firebaseauth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        etPassword = findViewById(R.id.reset_password)
        btnResetPassword = findViewById(R.id.Submit)

        firebaseauth = FirebaseAuth.getInstance()

        btnResetPassword.setOnClickListener {
            val sPassword = etPassword.text.toString()
            firebaseauth.sendPasswordResetEmail(sPassword)
                .addOnSuccessListener {
                    Toast.makeText(this,"Please check your email",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }
        }
    }
}