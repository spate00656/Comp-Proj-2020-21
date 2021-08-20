package com.woa.dataClasses;

public class Reminder {

    String alarmId, date, description, time, title;

    public Reminder(){}

    public Reminder(String alarmId, String date, String description, String time, String title) {
        this.alarmId = alarmId;
        this.date = date;
        this.description = description;
        this.time = time;
        this.title = title;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }
}
