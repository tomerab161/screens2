package com.example.screens2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
        msg="Register Screen";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickLogin(View view) {
        Dal dal = new Dal(this);
        EditText username = findViewById(R.id.usernameInput);
        EditText password = findViewById(R.id.passwordInput);
        if(dal.login(username.getText().toString(), password.getText().toString())) {
            Intent i = new Intent(this, Main.class);
            msg = "Login Succesful";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
        else{
            msg = "Login failed";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
