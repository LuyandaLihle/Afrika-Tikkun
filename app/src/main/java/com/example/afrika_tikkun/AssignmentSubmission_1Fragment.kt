package com.example.afrika_tikkun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class AssignmentSubmission_1Fragment : Fragment() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var submitButton: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_assignment_submission_1, container, false)

        firstNameEditText = view.findViewById(R.id.etName)
        lastNameEditText = view.findViewById(R.id.etSurname)
        submitButton = view.findViewById(R.id.button)

        submitButton.setOnClickListener {
            // Get user input
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()

            // Create a data map
            val data = hashMapOf(
                "firstName" to firstName,
                "lastName" to lastName,
                "submitted" to true
            )

            // Add data to Firebase Firestore
            db.collection("assignments")
                .add(data)
                .addOnSuccessListener {
                    // Data added successfully
                    firstNameEditText.text.clear()
                    lastNameEditText.text.clear()
                }
                .addOnFailureListener {
                    // Handle failure
                }
        }

        return view
    }
}
