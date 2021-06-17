package com.example.screens2;

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
        Log.i("tomer1",cursor.getString(0));
        User user=new User();

        user.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id"))));
        user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
        user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        user.setPhone_number(cursor.getString(cursor.getColumnIndex("phone_number")));
        return user;
    }

    public void updateUser(int id, String username, String password, String phone_number){
        SQLiteDatabase db = getWritableDatabase();

        String st = "update users set username=\"" + username + "\" and password=\"" + password + "\" and username=\"" + username + "\" where _id="+String.valueOf(id);
        Cursor cursor = db.rawQuery(st, null);
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

        String st = "select * from events where event_name=\"" + eventName + "\"";
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
                sql_INSERT = "insert into members (_id,event_name,phone_number) values(?,?,?)";
                SQLiteStatement statement1 = db.compileStatement(sql_INSERT);

                statement1.bindLong(1,id);
                statement1.bindString(2,eventName);
                statement1.bindString(3,members[i]);
                statement1.execute();
            }
            return true; // Register succeeded
        }
        return false; // Register failed
    }

    //updateEvent

    //getEvent

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
}
