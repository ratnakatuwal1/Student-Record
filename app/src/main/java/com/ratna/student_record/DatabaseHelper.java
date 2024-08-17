package com.ratna.student_record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ratna.student_record.dtos.Student;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Student.db";
    public static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "STUDENT_TABLE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_STUDENT_NAME = "FULLNAME";
    private static final String COLUMN_GENDER = "GENDER";
    private static final String COLUMN_DOB = "DOB";
    private static final String COLUMN_BATCH = "BATCH";
    private static final String COLUMN_FACULTY = "FACULTY";
    private static final String COLUMN_ADDRESS = "ADDRESS";
    private static final String COLUMN_CONTACT = "CONTACT";
    private static final String COLUMN_EMAIL = "EMAIL";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String stmt = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STUDENT_NAME + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_DOB + " TEXT, " +
                COLUMN_BATCH + " TEXT, " +
                COLUMN_FACULTY + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_CONTACT + " TEXT, " +
                COLUMN_EMAIL + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(stmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertOne(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STUDENT_NAME, student.getFullName());
        cv.put(COLUMN_GENDER, student.getGender());
        cv.put(COLUMN_DOB, student.getDob());
        cv.put(COLUMN_BATCH, student.getBatch());
        cv.put(COLUMN_FACULTY, student.getFaculty());
        cv.put(COLUMN_ADDRESS, student.getAddress());
        cv.put(COLUMN_CONTACT, student.getContact());
        cv.put(COLUMN_EMAIL, student.getEmail());

        long res = db.insert(TABLE_NAME, null, cv);

        db.close();

        if (res == -1) {
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<Student> readAll() {
        ArrayList<Student> students = new ArrayList<Student>();
        String sql =  "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int studentId = cursor.getInt(0);
                String studentName = cursor.getString(1);
                String studentGender = cursor.getString(2);
                String studentDob = cursor.getString(3);
                String studentBatch = cursor.getString(4);
                String studentFaculty = cursor.getString(5);
                String studentAddress = cursor.getString(6);
                String studentContact = cursor.getString(7);
                String studentEmail = cursor.getString(8);

                Student student = new Student(studentId, studentName, studentGender, studentDob, studentBatch, studentFaculty, studentAddress, studentContact, studentEmail);
                students.add(student);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return students;
    }

    public boolean deleteOne(int id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_NAME, COLUMN_ID + " = " + id, null);

        db.close();

        if (res == 1) {
            return true;
        }else {
            return false;
        }
    }

    public boolean updateOne(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STUDENT_NAME, student.getFullName());
        cv.put(COLUMN_EMAIL, student.getEmail());
        cv.put(COLUMN_CONTACT, student.getContact());
        cv.put(COLUMN_ADDRESS, student.getAddress());
        cv.put(COLUMN_FACULTY, student.getFaculty());
        cv.put(COLUMN_DOB, student.getDob());
        cv.put(COLUMN_BATCH, student.getBatch());
        cv.put(COLUMN_GENDER, student.getGender());

        int res = db.update(TABLE_NAME, cv, COLUMN_ID + " = " + student.getId(), null);
        db.close();

        if(res == 1) {
            return true;
        }else {
            return false;
        }
    }
}