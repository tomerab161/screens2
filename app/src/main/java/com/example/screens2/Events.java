package com.example.screens2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Events extends AppCompatActivity {
    String msg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }

    public void onClickNewEvent(View view) {
        Intent i= new Intent(this, NewEvent.class);
        msg=i.getStringExtra("New Event Screen");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickUpdateEvent(View view) {
        Intent i= new Intent(this, UpdateEvent.class);
        msg=i.getStringExtra("Update Event Screen");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickBack(View view) {
        Intent i= new Intent(this, Main.class);
        msg=i.getStringExtra("Main Screen");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}
