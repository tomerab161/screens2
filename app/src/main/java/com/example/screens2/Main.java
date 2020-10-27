package com.example.screens2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    String msg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

   }

    public void onClickUpdateUser(View view) {
        Intent i= new Intent(this, UserDetails.class);
        msg=i.getStringExtra("Update User Screen");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickEvents(View view) {
        Intent i= new Intent(this, Events.class);
        msg=i.getStringExtra("Events Screen");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickLogout(View view) {
        Intent i= new Intent(this, Login.class);
        msg=i.getStringExtra("Login Screen");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}
