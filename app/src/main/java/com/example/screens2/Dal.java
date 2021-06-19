package com.example.screens2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Dal extends SQLiteAssetHelper {
    public Dal(Context context) {
        super(context, "tracker.db", null, 1);
    }

    public boolean addUser(String username, String password, String phone_number)
    {
        SQLiteDatabase db = getWritableDatabase();

        String st = "select * from users where username=\"" + username + "\"";
        Cursor cursor = db.rawQuery(st, null);
        // No other user with the same username exists

        if(cursor.getCount() == 0)
        {
            String sql_INSERT = "insert into users (username, password, phone_number) values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql_INSERT);

            statement.bindString(1, username);
            statement.bindString(2, password);
            statement.bindString(3, phone_number);
            statement.execute();
            return true; // Register succeeded
        }
        return false; // Register failed
    }

    public boolean login(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String st = "select _id from users where username=\"" + username + "\" and password=\"" + password +"\"";
        Cursor cursor = db.rawQuery(st, null);

        return cursor.getCount() != 0; // Check if user exists
    }

    public User getUser(String username){
        SQLiteDatabase db = getWritableDatabase();

        String st = "select * from users where username=\"" + username + "\"";
        Cursor cursor = db.rawQuery(st, null);

        User user = new User();
        if(cursor.moveToFirst()) {
            user.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id"))));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            user.setPhone_number(cursor.getString(cursor.getColumnIndex("phone_number")));
            return user;
        }
        return null;
    }

    public boolean updateUser(int id, String username, String password, String phone_number){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("phone_number", phone_number);

        db.update("users", contentValues, "_id=?", new String[]{String.valueOf(id)});
        return true;
    }

    public boolean deleteUser(String username)
    {
        SQLiteDatabase db = getWritableDatabase();

        String st = "select * from users where username=\"" + username + "\"";
        Cursor cursor = db.rawQuery(st, null);
        // if user exist

        if(cursor.getCount() == 1)
        {
            String sql_DELETE = "delete from users where username=?";
            SQLiteStatement statement = db.compileStatement(sql_DELETE);

            statement.bindString(1, username);
            statement.execute();
            return true; // delete succeeded
        }
        return false; // delete failed
    }

    public boolean addEvent(int id,String eventName, String date, String time, String link, String[] members)
    {
        SQLiteDatabase db = getWritableDatabase();

        String st = "select * from events where event_name=\"" + eventName + "\" and _id="+String.valueOf(id);
        Cursor cursor = db.rawQuery(st, null);
        // No other user with the same username exists

        if(cursor.getCount() == 0)
        {
            String sql_INSERT = "insert into events (_id,event_name, date, time, link) values(?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql_INSERT);

            statement.bindLong(1, id);
            statement.bindString(2, eventName);
            statement.bindString(3, date);
            statement.bindString(4, time);
            statement.bindString(5, link);
            statement.execute();
            for(int i=0;i<members.length;i++){
                //insert all phone numbers
                ContentValues contentValues=new ContentValues();

                contentValues.put("_id", id);
                contentValues.put("event_name",eventName);
                contentValues.put("phone_number", members[i]);
                Log.i("tomer", members[i]);
                db.insert("members", null, contentValues);
            }
            return true; // Register succeeded
        }
        return false; // Register failed
    }

    public boolean updateEvent(int id, String event_name, String date, String time, String link){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("link", link);

        db.update("events", contentValues, "_id=? and event_name=?", new String[]{String.valueOf(id), event_name});
        return true;
    }

    public Event getEvent(int id, String event_name){
        SQLiteDatabase db = getWritableDatabase();

        String st = "select * from events where event_name=\"" + event_name + "\" and _id="+String.valueOf(id);
        Cursor cursor = db.rawQuery(st, null);

        Event event=new Event();
        if(cursor.moveToFirst()) {
            event.set_id(id);
            event.setEventName(event_name);
            event.setDate(cursor.getString(cursor.getColumnIndex("date")));
            event.setTime(cursor.getString(cursor.getColumnIndex("time")));
            event.setLink(cursor.getString(cursor.getColumnIndex("link")));

            cursor.close();

            st = "select * from members where event_name=\"" + event_name + "\" and _id="+String.valueOf(id);
            cursor = db.rawQuery(st, null);

            String[] phones=new String[cursor.getCount()];
            int i=0;
            while(cursor.moveToNext()){
                phones[i]=cursor.getString(cursor.getColumnIndex("phone_number"));
                i++;
            }
            event.setMembers(phones);
        }
        cursor.close();
        return event;
    }

    public String[] getEvents(int id){
        SQLiteDatabase db = getWritableDatabase();

        String st = "select * from events where _id="+String.valueOf(id);
        Cursor cursor = db.rawQuery(st, null);

        String[] events_names = new String[cursor.getCount()];
        int i=0;
        while (cursor.moveToNext()){
            Log.i("tomer", cursor.getString(cursor.getColumnIndex("event_name")));
            events_names[i]= cursor.getString(cursor.getColumnIndex("event_name"));
            i++;
        }
        return events_names;
    }

    public boolean deleteEvent(int id, String eventName)//not finished
    {
        SQLiteDatabase db = getWritableDatabase();

        String st = "select * from events where event_name=\"" + eventName + "\" and _id="+String.valueOf(id);
        Cursor cursor = db.rawQuery(st, null);
        // if user exist

        if(cursor.getCount() == 1)
        {
            String sql_DELETE = "delete from events where event_name=? and _id=?";//delete event
            SQLiteStatement statement = db.compileStatement(sql_DELETE);

            statement.bindString(1, eventName);
            statement.bindLong(2,id);
            statement.execute();
            String sql_DELETE2 = "delete from members where event_name=? and _id=?";//delete event members
            SQLiteStatement statement2 = db.compileStatement(sql_DELETE2);

            statement2.bindString(1,eventName);
            statement2.bindLong(2,id);
            statement2.execute();
            return true; // delete succeeded
        }
        return false; // delete failed
    }

    public void deletePhoneNumber(int id, String event_name, String phone_number){
        SQLiteDatabase db = getWritableDatabase();
        String sql_DELETE = "delete from members where event_name=? and _id=? and phone_number=?";//delete event members
        SQLiteStatement statement = db.compileStatement(sql_DELETE);

        statement.bindString(1,event_name);
        statement.bindLong(2,id);
        statement.bindString(3,phone_number);
        statement.execute();
    }

    public boolean addPhoneNumber(int id, String event_name, String phone_number){
        SQLiteDatabase db = getWritableDatabase();

        String st = "select * from members where event_name=\""+ event_name+"\" and phone_number=\""+phone_number+"\" and _id="+String.valueOf(id);
        Cursor cursor = db.rawQuery(st, null);
        // No other phone number exist

        if(cursor.getCount() == 0)
        {
            String sql_INSERT = "insert into members (_id,event_name,phone_number) values(?,?,?)";
            SQLiteStatement statement1 = db.compileStatement(sql_INSERT);

            statement1.bindLong(1,id);
            statement1.bindString(2,event_name);
            statement1.bindString(3,phone_number);

            statement1.execute();
            return true; // Register succeeded
        }
        return false;
    }
}
