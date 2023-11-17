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
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class VolunteerMainPage : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var fullNameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var emailAddressEditText: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var genderSpinner: Spinner
    private lateinit var altContactEditText: EditText
    private lateinit var availabilitySpinner: Spinner
    private lateinit var skillsEditText: EditText
    private lateinit var submitButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer_main_page)

        db = FirebaseFirestore.getInstance()

        // Initialize form elements
        fullNameEditText = findViewById(R.id.etFullName)
        addressEditText = findViewById(R.id.etAddress)
        phoneNumberEditText = findViewById(R.id.etPhoneNumber)
        emailAddressEditText = findViewById(R.id.etEmailAddress)
        datePicker = findViewById(R.id.datePicker)
        genderSpinner = findViewById(R.id.spinnerGender)
        altContactEditText = findViewById(R.id.etAltContact)
        availabilitySpinner = findViewById(R.id.spinnerAvailability)
        skillsEditText = findViewById(R.id.etSkills)
        submitButton = findViewById(R.id.btnSubmit)

        // Use Calendar to handle date selection
        val currentDate = Calendar.getInstance()
        currentDate.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)

        // Format birthDate using SimpleDateFormat
        val birthDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(currentDate.time)


        // Set up gender spinner
        val genderOptions = arrayOf("Male", "Female", "Other")
        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genderOptions)
        genderSpinner.adapter = genderAdapter

        // Set up availability spinner
        val availabilityOptions = arrayOf("Full-time", "Part-time", "Flexible")
        val availabilityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, availabilityOptions)
        availabilitySpinner.adapter = availabilityAdapter

        // Set up click listener for the submit button
        submitButton.setOnClickListener {
            // Call a function to handle form submission
            submitForm()
        }
    }

    private fun submitForm() {
        // Retrieve data from the form
        val fullName = fullNameEditText.text.toString()
        val address = addressEditText.text.toString()
        val phoneNumber = phoneNumberEditText.text.toString()
        val emailAddress = emailAddressEditText.text.toString()
        val birthDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date(datePicker.year - 1900, datePicker.month, datePicker.dayOfMonth))
        val gender = genderSpinner.selectedItem.toString()
        val altContact = altContactEditText.text.toString()
        val availability = availabilitySpinner.selectedItem.toString()
        val skills = skillsEditText.text.toString()

        // Create a Firebase database reference
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("volunteerForms")

        // Generate a unique key for each form submission
        val key = myRef.push().key

        // Create a data object to store in the database
        val formData = VolunteerFormData(
            key,
            fullName,
            address,
            phoneNumber,
            emailAddress,
            birthDate,
            gender,
            altContact,
            availability,
            skills
        )

        // Store the data in the database
        key?.let {
            myRef.child(it).setValue(formData)
                .addOnCompleteListener {
                    // Clear the form
                    clearForm()

                    // Display a success message
                    Toast.makeText(this, "Your form has been received", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // Display an error message
                    Toast.makeText(this, "Form submission failed", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun clearForm() {
        // Clear all the EditText fields
        fullNameEditText.text.clear()
        addressEditText.text.clear()
        phoneNumberEditText.text.clear()
        emailAddressEditText.text.clear()
        altContactEditText.text.clear()
        skillsEditText.text.clear()

        // Reset the spinners to the default selection
        genderSpinner.setSelection(0)
        availabilitySpinner.setSelection(0)

        // Set the date picker to the current date
        val currentDate = Calendar.getInstance()
        datePicker.updateDate(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH))
    }
}