package com.example.screens2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    String msg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickRegister(View view) {
        Intent i= new Intent(this, Register.class);
        msg=i.getStringExtra("Login Screen");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickLogin(View view) {
        Intent i= new Intent(this, Main.class);
        msg=i.getStringExtra("Main Screen");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}
