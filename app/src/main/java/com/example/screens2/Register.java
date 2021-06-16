package com.example.screens2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    String msg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
    }

    public void onClickBack(View view) {
        Intent i= new Intent(this, Login.class);
        msg="Login Screen";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickRegister(View view) {
        Dal dal = new Dal(this);
        EditText username = findViewById(R.id.usernameInput);
        EditText password = findViewById(R.id.passwordInput);
        EditText phone_number = findViewById(R.id.phoneNumberInput);
        if(username.getText().toString() != null && password.getText().toString() != null && phone_number.getText().toString() != null) {
            if (dal.addUser(username.getText().toString(), password.getText().toString(), phone_number.getText().toString())) {
                Intent i = new Intent(this, Main.class);
                msg = "Register Succesful";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                startActivity(i);
            } else {
                msg = "Register failed";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            msg = "param can't be null";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
