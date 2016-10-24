package com.example.karthickramjee.androidlab;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ListUsers extends AppCompatActivity {

    ListView listView;
    DBHelper databaseHelper;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        listView=(ListView)findViewById(R.id.listview);
        databaseHelper=new DBHelper(this);
        UserAdapter userAdapter=new UserAdapter(this,databaseHelper.getAllUsers());
        listView.setAdapter(userAdapter);



        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent=new Intent("android.media.action.DISPLAY_NOTIFICATION");
        intent.addCategory("android.intent.category.DEFAULT");
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Date date=new Date(2016,10,15,10,10,10);
        Date date1=new Date(2016,10,15,10,10,20);
        long diff=date1.getTime()-date.getTime();
        long sec=(diff/1000)%60;
        Log.d("TIME", String.valueOf(diff)+" "+String.valueOf(sec));
        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.SECOND, (int) sec);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

        //Reading from Assets folder
        StringBuilder buf=new StringBuilder();
        try {
            InputStream is=getAssets().open("/newfile.txt");
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            String str="";

            while((str=reader.readLine())!=null)
            {
                buf.append(str);
            }
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
