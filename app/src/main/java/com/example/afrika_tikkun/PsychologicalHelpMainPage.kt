package com.example.afrika_tikkun

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class PsychologicalHelpMainPage : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var etName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etContactNumber: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var spinnerGender: Spinner
    private lateinit var spinnerMentalHealth: Spinner
    private lateinit var etNextOfKinName: EditText
    private lateinit var etNextOfKinContactNumber: EditText
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psychological_help_main_page)

        db = FirebaseFirestore.getInstance()

        etName = findViewById(R.id.etName)
        etSurname = findViewById(R.id.etSurname)
        etContactNumber = findViewById(R.id.etContactNumber)
        datePicker = findViewById(R.id.datePicker)
        spinnerGender = findViewById(R.id.spinnerGender)
        spinnerMentalHealth = findViewById(R.id.spinnerMentalHealth)
        etNextOfKinName = findViewById(R.id.etNextOfKinName)
        etNextOfKinContactNumber = findViewById(R.id.etNextOfKinContactNumber)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Populate Gender Spinner
        val genderAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("Female", "Male", "Other", "Prefer Not to Say")
        )
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = genderAdapter

        // Populate Mental Health Spinner
        val mentalHealthAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("Suicide", "Depression", "Anxiety", "Schizophrenia", "Prefer Not to Say")
        )
        mentalHealthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMentalHealth.adapter = mentalHealthAdapter

        btnSubmit.setOnClickListener {
            submitForm()
        }
    }

    private fun submitForm() {
        val name = etName.text.toString().trim()
        val surname = etSurname.text.toString().trim()
        val contactNumber = etContactNumber.text.toString().trim()
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year
        val gender = spinnerGender.selectedItem.toString()
        val mentalHealth = spinnerMentalHealth.selectedItem.toString()
        val nextOfKinName = etNextOfKinName.text.toString().trim()
        val nextOfKinContactNumber = etNextOfKinContactNumber.text.toString().trim()

        // Create a Calendar instance to format Date of Birth
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dob = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)

        // Validate the form (you can add more validation)
        if (name.isEmpty() || surname.isEmpty() || contactNumber.isEmpty()) {
            Toast.makeText(
                this,
                "Name, Surname, and Contact Number are required",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Create a data object
        val data = hashMapOf(
            "Name" to name,
            "Surname" to surname,
            "ContactNumber" to contactNumber,
            "DateOfBirth" to dob,
            "Gender" to gender,
            "MentalHealth" to mentalHealth,
            "NextOfKinName" to nextOfKinName,
            "NextOfKinContactNumber" to nextOfKinContactNumber
        )

        // Add data to Firestore
        db.collection("users")
            .add(data)
            .addOnSuccessListener { documentReference ->
                // Show a confirmation message to the user
                Toast.makeText(
                    this,
                    "Data added with ID: ${documentReference.id}",
                    Toast.LENGTH_SHORT
                ).show()

                // Clear the form after submission
                clearForm()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error adding document: $e", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearForm() {
        // Clear all the EditText fields
        etName.text.clear()
        etSurname.text.clear()
        etContactNumber.text.clear()
        etNextOfKinName.text.clear()
        etNextOfKinContactNumber.text.clear()

        // Reset the spinners to the default selection
        spinnerGender.setSelection(0)
        spinnerMentalHealth.setSelection(0)

        // Set the date picker to a default date (e.g., January 1, 2000)
        val defaultDate = Calendar.getInstance()
        defaultDate.set(2000, 0, 1)
        datePicker.updateDate(defaultDate.get(Calendar.YEAR), defaultDate.get(Calendar.MONTH), defaultDate.get(Calendar.DAY_OF_MONTH))
    }
}
