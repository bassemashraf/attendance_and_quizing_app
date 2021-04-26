package com.example.attendancenquizsystem.model;

public class Lecture {
    String title;
    String note;
    long attendance;
    String QR;
    String pdf;
    String Quiz;
    public  Lecture(){

    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public Lecture(String title, String note, long attendance) {
        this.title = title;
        this.note = note;
        this.attendance = attendance;
    }

    public String getQR() {
        return QR;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getAttendance() {
        return attendance;
    }

    public void setAttendance(long attendance) {
        this.attendance = attendance;
    }
}
