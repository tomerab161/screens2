package com.example.screens2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Events extends AppCompatActivity {
    String msg="";
    Intent data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        data = getIntent();
    }

    public void onClickNewEvent(View view) {
        Intent i= new Intent(this, NewEvent.class);
        i.putExtra("username",data.getStringExtra("username"));
        msg="New Event Screen";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickMyEvent(View view) {
        Intent i= new Intent(this, UserEvents.class);
        i.putExtra("username", data.getStringExtra("username"));
        msg="User Events Screen";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickBack(View view) {
        Intent i= new Intent(this, Main.class);
        i.putExtra("username", data.getStringExtra("username"));
        msg="Main Screen";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}
