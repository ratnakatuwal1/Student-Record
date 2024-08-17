package com.ratna.student_record;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ratna.student_record.dtos.Student;
import com.ratna.student_record.dtos.StudentAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{
    private RecyclerView recyclerView;
    private ArrayList<Student> studentList;
    private StudentAdapter studentAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.studentList);
        databaseHelper = new DatabaseHelper(MainActivity.this);
        studentList = databaseHelper.readAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        studentList.add(new Student(1, "Raj Kari", "Male", "1990-01-01", "Batch 1", "Science", "123 Main St", "1234567890", "john@example.com"));
//        studentList.add(new Student(2, "Shreya Sharma", "Female", "1992-02-02", "Batch 2", "Arts", "456 Elm St", "0987654321", "jane@example.com"));
//        studentList.add(new Student(3, "Tsring Dorje", "Male", "1990-01-01", "Batch 1", "Science", "123 Main St", "1234567890", "john@example.com"));
//        studentList.add(new Student(4, "Mena Rai", "Female", "1992-02-02", "Batch 2", "Arts", "456 Elm St", "0987654321", "jane@example.com"));

        showStudents();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showStudents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.student_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addSudent:
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showStudents() {
        if (databaseHelper.readAll().size() != 0) {
            studentAdapter = new StudentAdapter(databaseHelper.readAll(), this);
            recyclerView.setAdapter(studentAdapter);
        }
    }

    @Override
    public void onEditClick(Student student) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Edit Student");
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_edit, null);
        builder.setView(dialogView);

        // Get references to EditText fields
        EditText editTextFullName = dialogView.findViewById(R.id.editTextFullName);
        EditText editTextGender = dialogView.findViewById(R.id.editTextGender);
        EditText editTextDob = dialogView.findViewById(R.id.editTextDob);
        EditText editTextBatch = dialogView.findViewById(R.id.editTextBatch);
        EditText editTextFaculty = dialogView.findViewById(R.id.editTextFaculty);
        EditText editTextAddress = dialogView.findViewById(R.id.editTextAddress);
        EditText editTextContact = dialogView.findViewById(R.id.editTextContact);
        EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);

        // Populate the EditText fields with existing student data
        editTextFullName.setText(student.getFullName());
        editTextGender.setText(student.getGender());
        editTextDob.setText(student.getDob());
        editTextBatch.setText(student.getBatch());
        editTextFaculty.setText(student.getFaculty());
        editTextAddress.setText(student.getAddress());
        editTextContact.setText(student.getContact());
        editTextEmail.setText(student.getEmail());

        // Get references to buttons
        Button buttonUpdate = dialogView.findViewById(R.id.buttonUpdate);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);

        AlertDialog alertDialog = builder.create();

        // Set up button click listeners
        buttonUpdate.setOnClickListener(v -> {
            // Get updated values from EditText fields
            String updatedFullName = editTextFullName.getText().toString();
            String updatedGender = editTextGender.getText().toString();
            String updatedDob = editTextDob.getText().toString();
            String updatedBatch = editTextBatch.getText().toString();
            String updatedFaculty = editTextFaculty.getText().toString();
            String updatedAddress = editTextAddress.getText().toString();
            String updatedContact = editTextContact.getText().toString();
            String updatedEmail = editTextEmail.getText().toString();

            // Create a new Student object with the updated values
            Student updatedStudent = new Student(student.getId(), updatedFullName, updatedGender, updatedDob, updatedBatch, updatedFaculty, updatedAddress, updatedContact, updatedEmail);

            // Find the index of the existing student in the ArrayList


            //TODO
            DatabaseHelper dao = new DatabaseHelper(MainActivity.this);
            dao.updateOne(updatedStudent);

            //HERE


            int index = studentList.indexOf(student);
            if (index != -1) {
                // Replace the existing student with the updated student

                studentList.set(index, updatedStudent);

                // Notify the adapter about the data change
                studentAdapter.notifyItemChanged(index);


            }

            // Dismiss the dialog
            alertDialog.dismiss();
            showStudents();


            // Notify the user
            Toast.makeText(MainActivity.this, "Student updated successfully", Toast.LENGTH_SHORT).show();
        });

        buttonCancel.setOnClickListener(v -> {
            // Dismiss the dialog
            alertDialog.dismiss();
        });

        alertDialog.show();
    }


    @Override
    public void onDeleteClick(Student student) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete Student");
        builder.setMessage("Are you sure you want to delete this student?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            studentList.remove(student);
            studentAdapter.notifyDataSetChanged();

            //TODO
            DatabaseHelper dao = new DatabaseHelper(MainActivity.this);
            dao.deleteOne(student.getId());
            showStudents();
            Toast.makeText(MainActivity.this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
        });


        builder.setNegativeButton("No", (dialog, which) -> {
            //TODO

            Toast.makeText(MainActivity.this, "Student deletion canceled", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}