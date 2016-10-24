package com.example.karthickramjee.androidlab;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.preference.DialogPreference;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Button add,search,delete,view;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MyAdapter adap=new MyAdapter(this,genData());
        //lv.setAdapter(adap);
        lv=(ListView)findViewById(R.id.lv);
        final DatabaseHelper  db=new DatabaseHelper(this);
        add=(Button)findViewById(R.id.add);
        search=(Button)findViewById(R.id.search);
        delete=(Button)findViewById(R.id.delete);
        view=(Button)findViewById(R.id.view);


        //Send Notification

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 15);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Enter All details:");
                Context context = getApplicationContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText number = new EditText(context);
                number.setHint("Number");
                number.setTextColor(Color.BLACK);
                layout.addView(number);

                final EditText name = new EditText(context);
                name.setHint("Name");
                name.setTextColor(Color.BLACK);
                layout.addView(name);

                final EditText mail = new EditText(context);
                mail.setHint("Mail");
                mail.setTextColor(Color.BLACK);
                layout.addView(mail);

                alert.setView(layout);
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String na=name.getText().toString();
                        String nu=number.getText().toString();
                        String ma=mail.getText().toString();
                        db.addData(new DataRes(Integer.parseInt(nu),na,ma));
                    }
                }
                );
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.show();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Enter Number to Search:");
                Context context=getApplicationContext();
                LinearLayout layout=new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText num=new EditText(context);
                num.setTextColor(Color.BLACK);
                layout.addView(num);
                alert.setView(layout);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int number=Integer.parseInt(num.getText().toString());
                        DataRes res=db.getData(number);
                        if(res==null)
                        {
                            Toast.makeText(getApplicationContext(),"Nothing Found",Toast.LENGTH_LONG).show();
                            return;
                        }
                        ArrayList<DataRes> arr=new ArrayList<DataRes>();
                        arr.add(res);
                        MyAdapter adap=new MyAdapter(getApplicationContext(),arr);
                        lv.setAdapter(adap);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Enter Number to Delete:");
                Context context=getApplicationContext();
                LinearLayout layout=new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText num=new EditText(context);
                num.setTextColor(Color.BLACK);
                layout.addView(num);
                alert.setView(layout);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int number=Integer.parseInt(num.getText().toString());
                        db.deleteData(number);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.show();
            }

        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<DataRes> arr=db.getAllData();
                MyAdapter adap=new MyAdapter(getApplicationContext(),arr);
                lv.setAdapter(adap);
            }

        });
    }

    private ArrayList<DataRes> genData() {
        ArrayList<DataRes> arr=new ArrayList<DataRes>();
        arr.add(new DataRes(123,"Karthick","okok@gmail.com"));
        arr.add(new DataRes(124,"Karthi","okok1@gmail.com"));
        arr.add(new DataRes(125,"Kar","okok2@gmail.com"));
        arr.add(new DataRes(123,"Karthick","okok@gmail.com"));
        arr.add(new DataRes(124,"Karthi","okok1@gmail.com"));
        return arr;
    }
}
