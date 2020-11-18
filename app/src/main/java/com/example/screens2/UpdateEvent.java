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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateEvent extends AppCompatActivity {
    String msg="";
    int time_Value=0;
    int date_Value=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

        final ListView lv = findViewById(R.id.membersList);
        final Button addItem = findViewById(R.id.addMemberBtn);

        final EditText membersInput=findViewById(R.id.membersInput);
        String[] members=new String[]{"0546605845"};//user phones list

        final List<String> members_list=new ArrayList<String>(Arrays.asList(members));
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(UpdateEvent.this,android.R.layout.simple_list_item_1,members_list);

        lv.setAdapter(arrayAdapter);

        //add item to listView
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(membersInput.getText()!=null)
                {
                    String item=String.valueOf(membersInput.getText());
                    members_list.add(item);
                    arrayAdapter.notifyDataSetChanged();
                    membersInput.setText(null);

                }
            }
        });

        //delete item from listView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(UpdateEvent.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete this item?");

                final int positionToRemove=position;

                adb.setNegativeButton("Cancel",null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        members_list.remove(positionToRemove);
                        arrayAdapter.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });
    }

    public void onClickBack(View view) {
        Intent i= new Intent(this, UserEvents.class);
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
}
