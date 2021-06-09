package com.example.screens2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Dal extends SQLiteAssetHelper {
    public Dal(Context context) {
        super(context, "tracker.db", null, 1);
    }

    public boolean addUser(String username, String password, String phone_number)
    {
        SQLiteDatabase db = getWritableDatabase();

        String st = "select * from Users where Name=\"" + username + "\"";
        Cursor cursor = db.rawQuery(st, null);
        // No other user with the same username exists

        if(cursor.getCount() == 0)
        {
            String sql_INSERT = "insert into Users (username, password, phone_number) values(?,?,?)";
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

        String st = "select * from Users where username=\"" + username + "\" and password=\"" + password +"\"";
        Cursor cursor = db.rawQuery(st, null);

        return cursor.getCount() != 0; // Check if user exists
    }
}
