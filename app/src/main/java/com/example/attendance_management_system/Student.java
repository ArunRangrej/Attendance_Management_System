package com.example.attendance_management_system;

public class Student {
    String firstname;
    String lastname;
    String phno;
    String mail;
    String usn;
    String DOB;
    String address;

    public Student(String firstname, String lastname, String DOB, String address, String phno, String mail, String usn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phno = phno;
        this.mail = mail;
        this.usn = usn;
        this.DOB = DOB;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
