package com.example.screens2;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

class EventDay {
    String meetingName;
    String context;
    Calendar date;
    Time time;
    List<String> phone_numbers;

    public EventDay(String meetingName,String context,Calendar date,Time time,List<String> phones_number)
    {
        this.meetingName=meetingName;
        this.date=date;
        this.context=context;
        this.phone_numbers=phones_number;
        this.time=time;
    }

    public Calendar getDate() {
        return date;
    }

    public List<String> getPhone_numbers() {
        return phone_numbers;
    }

    public String getContext() {
        return context;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public Time getTime() {
        return time;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public void setPhone_numbers(List<String> phone_numbers) {
        this.phone_numbers = phone_numbers;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
