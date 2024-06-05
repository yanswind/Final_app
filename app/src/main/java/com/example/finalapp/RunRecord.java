package com.example.finalapp;

import java.util.Date;

public class RunRecord {
    private String distance;
    private String setTime;
    private String actualTime;
    private String date;
    public RunRecord(String distance,String setTime,String actualTime,String date) {
        super();
        this.distance = distance;
        this.setTime = setTime;
        this.actualTime = actualTime;
        this.date = date;
    }
    public String getDistance() {
        return distance;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }
    public String getSetTime() {
        return setTime;
    }
    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }
    public String getActualTime() {
        return actualTime;
    }
    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
