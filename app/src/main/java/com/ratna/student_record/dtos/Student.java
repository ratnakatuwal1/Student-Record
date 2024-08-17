package com.ratna.student_record.dtos;

public class Student {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    int id;
    String fullName;
    String gender;
    String dob;
    String batch;
    String faculty;
    String address;
    String contact;
    String email;

    public Student(int id, String fullName, String gender, String dob, String batch, String faculty, String address, String contact, String email) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.batch = batch;
        this.faculty = faculty;
        this.address = address;
        this.contact = contact;
        this.email = email;
    }

}
