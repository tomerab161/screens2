package com.example.screens2;

class Event {
    int _id;
    String eventName;
    String date;
    String time;
    String link;
    String[] members;

    public int get_id() {
        return _id;
    }
    public String getDate() {
        return date;
    }
    public String getEventName() {
        return eventName;
    }
    public String getLink() {
        return link;
    }
    public String getTime() {
        return time;
    }
    public String[] getMembers() {
        return members;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setMembers(String[] members) {
        this.members = members;
    }
}
