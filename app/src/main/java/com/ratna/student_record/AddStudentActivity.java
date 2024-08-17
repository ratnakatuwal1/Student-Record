package com.ratna.student_record;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ratna.student_record.dtos.Student;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextGender, editTextDob, editTextBatch, editTextFaculty, editTextAddress, editTextContact, editTextEmail;
    private Button buttonSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editTextFullName = findViewById(R.id.editTextFullName);
        editTextGender = findViewById(R.id.editTextGender);
        editTextDob = findViewById(R.id.editTextDob);
        editTextBatch = findViewById(R.id.editTextBatch);
        editTextFaculty = findViewById(R.id.editTextFaculty);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextContact = findViewById(R.id.editTextContact);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
            }
        });
    }

    private void saveStudent() {
        String fullName = editTextFullName.getText().toString().trim();
        String gender = editTextGender.getText().toString().trim();
        String dob = editTextDob.getText().toString().trim();
        String batch = editTextBatch.getText().toString().trim();
        String faculty = editTextFaculty.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        if (fullName.isEmpty() || gender.isEmpty() || dob.isEmpty() || batch.isEmpty() || faculty.isEmpty() || address.isEmpty() || contact.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Student object
        Student student = new Student(0, fullName, gender, dob, batch, faculty, address, contact, email);

        DatabaseHelper db = new DatabaseHelper(AddStudentActivity.this);
        boolean success = db.insertOne(student);

        if (success) {
            Toast.makeText(AddStudentActivity.this, student.getFullName() + " Student Sucessfully Inserted", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(AddStudentActivity.this, "Failed to Insert", Toast.LENGTH_SHORT).show();
        }


        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
        finish(); // Close the activity
    }
}