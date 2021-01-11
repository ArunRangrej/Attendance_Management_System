package com.example.attendance_management_system;

public class Subjects {
    String teacher_id;
    String subject;


    public Subjects(String tid, String sub) {

        this.teacher_id = tid;


        this.subject = sub;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
