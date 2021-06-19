package com.example.screens2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class UserEvents extends AppCompatActivity {
    String msg="";
    Intent data;
    ListView lv;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);
        data = getIntent();

        lv = findViewById(R.id.eventsListView);

        Dal dal=new Dal(this);
        user=dal.getUser(data.getStringExtra("username"));
        final String[] eventsName= dal.getEvents(user.get_id());

        final List<String> members_list=new ArrayList<String>(Arrays.asList(eventsName));
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(UserEvents.this,android.R.layout.simple_list_item_1,members_list);

        lv.setAdapter(arrayAdapter);

        //delete item from listView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(UserEvents.this);
                adb.setTitle("Choose a command");
                adb.setMessage("Do you want to delete or update this event");

                final int positionToRemove=position;

                adb.setNegativeButton("Update",new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i= new Intent(UserEvents.this,UpdateEvent.class);
                        i.putExtra("username", data.getStringExtra("username"));
                        i.putExtra("event_name",eventsName[position]);
                        String m="Update event Screen";
                        Toast.makeText(UserEvents.this,msg,Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }});
                adb.setPositiveButton("Delete", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        members_list.remove(positionToRemove);
                        //deleteEvent(id,eventName);
                        arrayAdapter.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });
    }

    public void onClickBack(View view) {
        Intent i= new Intent(this, Events.class);
        i.putExtra("username", data.getStringExtra("username"));
        msg="Events Screen";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickAddEvent(View view) {
        Intent i= new Intent(this, NewEvent.class);
        i.putExtra("username", data.getStringExtra("username"));
        msg="Add event Screen";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void onClickDeleteEvents(View view) {
        boolean[] selectedEvent = new boolean[lv.getAdapter().getCount()];
        final String[] events = new String[lv.getAdapter().getCount()];
        final ArrayList<Integer> eventsList = new ArrayList<>();
        for(int i=0;i<lv.getAdapter().getCount();i++){
            events[i] = (String)lv.getAdapter().getItem(i);
        }
        //initial alert
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //alert title
        builder.setTitle("Select events to delete");
        //cancel btn
        builder.setCancelable(true);

        builder.setMultiChoiceItems(events, selectedEvent, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    eventsList.add(which);
                    Collections.sort(eventsList);
                }
                else{
                    eventsList.remove(which);
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dal dal = new Dal(UserEvents.this);
                for(int j=0;j<eventsList.size();j++){
                    dal.deleteEvent(user.get_id(), events[eventsList.get(j)]);
                }
                finish();
                startActivity(getIntent());
            }
        });

        builder.show();

    }
}
