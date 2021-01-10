package com.example.attendance_management_system;


public class Teacher {
    String tname;
    String tid;
    String department;
    String tpass;

  /*  public Teacher(String tname, String tid, EditText subject, Spinner classes){

    }*/

    public Teacher(String tname, String tid, String dept, String tpass) {
        this.tname = tname;
        this.tid = tid;
        this.department = dept;

        this.tpass = tpass;
    }

    public String getTname() {
        return tname;
    }

    public String getTid() {
        return tid;
    }

    public String getdept() {
        return department;
    }


    public String gettpass() {
        return tpass;
    }

}
