package com.example.screens2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewEvent extends AppCompatActivity {
    String msg="";
    int date_Value=0;
    int time_Value=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
    }

    public void onClickBack(View view) {
        Intent i= new Intent(this, Events.class);
        msg="Events Screen";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickTimeInput(View view) {
        Intent i= new Intent(this, Time.class);
        msg="Choose Time";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivityForResult(i,time_Value);
    }

    public void onClickDateInput(View view) {
        Intent i = new Intent(this, Date.class);

        i.putExtra("activity", "first");
        msg = "Choose Date";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        startActivityForResult(i,date_Value);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==date_Value)
            if(resultCode==RESULT_OK)
            {
                EditText date=findViewById(R.id.dateInput);
                try {
                    if(data.getData()!=null) {
                        if (data.getData().toString().charAt(1) == '/' || data.getData().toString().charAt(2) == '/' || data.getData().toString().charAt(3) == '/') {
                            date.setText(data.getData().toString());
                        }
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        if(requestCode==time_Value)
            if(resultCode==RESULT_OK)
            {
                EditText time=findViewById(R.id.timeInput);
                try {
                    if (data.getData()!=null) {
                        if(data.getData().toString().charAt(1)==':' || data.getData().toString().charAt(2)==':' || data.getData().toString().charAt(3)==':') {
                            time.setText(data.getData().toString());
                        }
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
                }
            }
    }
}
