package com.example.screens2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class Time extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
    }

    public void onClickCancel(View view) {
        finish();
    }

    public void onClickOk(View view) {
        Intent data=new Intent();
        String time;
        TimePicker timePicker=findViewById(R.id.timePickerInput);

        int hour=timePicker.getHour();
        int minute=timePicker.getMinute();
        if(minute>9) {
            time = String.valueOf(hour) + ':' + String.valueOf(minute);
        }
        else {
            time = String.valueOf(hour) + ':' +'0'+ String.valueOf(minute);
        }
        data.setData(Uri.parse(time));
        setResult(RESULT_OK,data);

        finish();
    }
}
