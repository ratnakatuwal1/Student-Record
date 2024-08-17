package com.ratna.student_record;

import com.ratna.student_record.dtos.Student;

public interface OnItemClickListener {
    void onEditClick(Student student);
    void onDeleteClick(Student student);
}
