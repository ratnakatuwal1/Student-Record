package com.ratna.student_record.dtos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ratna.student_record.OnItemClickListener;
import com.ratna.student_record.R;


import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private OnItemClickListener onItemClickListener;

    public StudentAdapter(List<Student> studentList, OnItemClickListener onItemClickListener) {
        this.studentList = studentList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.bind(student, position, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameTextView, idTextView, genderTextView, dobTextView, batchTextView, facultyTextView, addressTextView, contactTextView, emailTextView;
        Button editButton, deleteButton;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            fullNameTextView = itemView.findViewById(R.id.textViewFullName);
            idTextView = itemView.findViewById(R.id.textViewId);
            genderTextView = itemView.findViewById(R.id.textViewGender);
            dobTextView = itemView.findViewById(R.id.textViewDob);
            batchTextView = itemView.findViewById(R.id.textViewBatch);
            facultyTextView = itemView.findViewById(R.id.textViewFaculty);
            addressTextView = itemView.findViewById(R.id.textViewAddress);
            contactTextView = itemView.findViewById(R.id.textViewContact);
            emailTextView = itemView.findViewById(R.id.textViewEmail);
            editButton = itemView.findViewById(R.id.buttonEdit);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
        }

        public void bind(final Student student, final int position, final OnItemClickListener listener) {
            fullNameTextView.setText(student.getFullName());
            idTextView.setText(student.getId() + "");
            genderTextView.setText(student.getGender());
            dobTextView.setText(student.getDob());
            batchTextView.setText(student.getBatch());
            facultyTextView.setText(student.getFaculty());
            addressTextView.setText(student.getAddress());
            contactTextView.setText(student.getContact());
            emailTextView.setText(student.getEmail());

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onEditClick(student);
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onDeleteClick(student);
                    }
                }
            });
        }
    }
}
