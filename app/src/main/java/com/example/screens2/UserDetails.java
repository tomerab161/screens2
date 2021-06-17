package com.example.screens2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserDetails extends AppCompatActivity {
    String msg="";
    Intent data;
    EditText username;
    EditText password;
    EditText phone_number;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        data = getIntent();
        Dal dal = new Dal(this);
        User u=dal.getUser(data.getStringExtra("username"));
        username=findViewById(R.id.usernameInput);
        password=findViewById(R.id.passwordInput);
        phone_number=findViewById(R.id.phoneNumberInput);
        id=u.get_id();
        username.setText(u.getUsername());
        password.setText(u.getPassword());
        phone_number.setText(u.getPhone_number());
    }

    public void onClickBack(View view) {
        Intent i= new Intent(this, Main.class);
        i.putExtra("username",data.getStringExtra("username"));
        msg="Main Screen";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickUpdate(View view) {
        if(username.getText()!=null && password.getText()!=null && phone_number.getText()!=null){
            Dal dal = new Dal(this);
            dal.updateUser(id,username.getText().toString(),password.getText().toString(),phone_number.getText().toString());
            Intent i= new Intent(this, Main.class);
            i.putExtra("username",data.getStringExtra("username"));
            msg="Success update user";
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
        else{
            msg="Params can't be null";
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        }
    }
}
