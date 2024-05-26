package com.example.finalapp;

public class TestItem {
    private int studentId;
    private String sex;
    private int grade;
    private String ItemName;
    private String ItemRecord;
    private int ItemScore;
    public TestItem() {
        super();

    }
    public TestItem(int studentId,String sex,int grade,String ItemName,String ItemRecord,int ItemScore) {
        super();
        this.studentId = studentId;
        this.sex = sex;
        this.grade = grade;
        this.ItemName = ItemName;
        this.ItemRecord = ItemRecord;
        this.ItemScore = ItemScore;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getGrade() { return grade; }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }
    public String getItemRecord() { return ItemRecord; }
    public void setItemRecord(String ItemRecord) {
        this.ItemRecord = ItemRecord;
    }
    public int getItemScore() { return ItemScore; }
    public void setItemScore(int ItemScore) {
        this.ItemScore = ItemScore;
    }
}
