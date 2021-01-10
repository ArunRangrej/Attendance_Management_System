package com.example.attendance_management_system;

public class Student {
    String name;
    String usn;
    String password;
    String DOB;
    String phno;
    String mail;
    String sem;
    String department;

    public Student(String sname , String susn, String spass, String sdob, String sphno, String smail, String sclass, String sdept) {
        this.name = sname;
        this.usn = susn;
        this.password = spass;
        this.DOB = sdob;
        this.phno = sphno;
        this.mail = smail;
        this.sem = sclass;
        this.department = sdept;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
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

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
