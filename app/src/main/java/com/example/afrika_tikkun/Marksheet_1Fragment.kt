package com.example.afrika_tikkun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.afrika_tikkun.databinding.FragmentMarksheet1Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Marksheet_1Fragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: FragmentMarksheet1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarksheet1Binding.inflate(inflater, container, false)
        val view = binding.root

        firebaseAuth = FirebaseAuth.getInstance()

        val editTextName = binding.editTextText3
        val editTextSurname = binding.editTextText4
        val editTextAssignment = binding.textView13
        val editTextTest = binding.textView14
        val editTextExam = binding.textView15
        val btnSubmit = binding.button2

        btnSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val surname = editTextSurname.text.toString()
            val assignment = editTextAssignment.text.toString().toInt()
            val test = editTextTest.text.toString().toInt()
            val exam = editTextExam.text.toString().toInt()

            // Create a map with the data to be stored in Firestore
            val studentData = hashMapOf(
                "name" to name,
                "surname" to surname,
                "assignment" to assignment,
                "test" to test,
                "exam" to exam
            )

            // Add data to Firestore
            FirebaseFirestore.getInstance().collection("students")
                .add(studentData)
                .addOnSuccessListener {
                    editTextName.text.clear()
                    editTextSurname.text.clear()

                    // Data added successfully
                }
                .addOnFailureListener {
                    // Handle errors here
                }
        }

        return view
    }
}
