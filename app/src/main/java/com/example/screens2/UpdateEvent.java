package com.example.screens2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateEvent extends AppCompatActivity {
    String msg="";
    Intent data;
    int time_Value=0;
    int date_Value=0;
    EditText date;
    EditText time;
    EditText link;
    User user;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);
        data =getIntent();

        TextView eventName= findViewById(R.id.eventNameTitle);
        date=findViewById(R.id.dateInput);
        time= findViewById(R.id.timeInput);
        link=findViewById(R.id.contextInput);

        final Dal dal = new Dal(this);
        user=dal.getUser(data.getStringExtra("username"));
        event=dal.getEvent(user.get_id(), data.getStringExtra("event_name"));
        String title="Event Name: "+data.getStringExtra("event_name");
        eventName.setText(title);
        date.setText(event.getDate());
        time.setText(event.getTime());
        link.setText(event.getLink());

        final ListView lv = findViewById(R.id.membersList);

        final EditText membersInput=findViewById(R.id.membersInput);
        String[] members=event.getMembers();//user phones list

        final List<String> members_list=new ArrayList<String>(Arrays.asList(members));
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(UpdateEvent.this,android.R.layout.simple_list_item_1,members_list);

        lv.setAdapter(arrayAdapter);
    }

    public void onClickBack(View view) {
        Intent i= new Intent(this, UserEvents.class);
        i.putExtra("username",data.getStringExtra("username"));
        msg="User Events Screen";
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

    public void onClickUpdate(View view) {
        if(!date.getText().toString().equals("") && !time.getText().toString().equals("") && !link.getText().toString().equals("")){
            Dal dal= new Dal(this);
            if(dal.updateEvent(event.get_id(), event.getEventName(), date.getText().toString(), time.getText().toString(), link.getText().toString())) {
                Intent i = new Intent(this, UserEvents.class);
                i.putExtra("username", data.getStringExtra("username"));
                msg = "Success update event";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }else{
                msg = "Failed update event";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            msg="Params can't be null";
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
