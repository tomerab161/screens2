package com.example.screens2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        msg=i.getStringExtra("Login Screen");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}
