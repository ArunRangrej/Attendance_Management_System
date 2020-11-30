package com.example.attendance_management_system;

public class Faculty {
    String fname;
    String fcontact;
    String fsubject;
    String fmail;
    String fpass;

    public Faculty(String fname, String fcontact, String fsubject, String fmail, String fpass) {
        this.fname = fname;
        this.fcontact = fcontact;
        this.fsubject = fsubject;
        this.fmail = fmail;
        this.fpass = fpass;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFcontact() {
        return fcontact;
    }

    public void setFcontact(String fcontact) {
        this.fcontact = fcontact;
    }

    public String getFsubject() {
        return fsubject;
    }

    public void setFsubject(String fsubject) {
        this.fsubject = fsubject;
    }

    public String getFmail() {
        return fmail;
    }

    public void setFmail(String fmail) {
        this.fmail = fmail;
    }

    public String getFpass() {
        return fpass;
    }

    public void setFpass(String fpass) {
        this.fpass = fpass;
    }
}
