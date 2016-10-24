package com.example.karthickramjee.androidlab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class FirstActivity extends Activity {


    Button db,volley,shared,login,signup,logout,view,web,cam,listView;
    private final String PREF="value";
    private final String NAME="name";
    private final String PASSWORD="password";
    SensorManager sm=null;

    SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float res=event.values[0];
            Toast.makeText(FirstActivity.this, res+" ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        db=(Button)findViewById(R.id.db);
        volley=(Button)findViewById(R.id.volley);
        shared=(Button)findViewById(R.id.shared);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        logout=(Button)findViewById(R.id.logout);
        view=(Button)findViewById(R.id.view);
        web=(Button)findViewById(R.id.web);
        cam=(Button)findViewById(R.id.cam);
        listView=(Button)findViewById(R.id.list);



        sm=(SensorManager)getApplicationContext().getSystemService(SENSOR_SERVICE);
        List<Sensor> list=sm.getSensorList(Sensor.TYPE_LIGHT);
        /*String res="";
        for(int i=0;i<list.size();i++)
        {
            res+=list.get(i).getName()+"\n";

        }*/
        //Toast.makeText(FirstActivity.this, res +" ", Toast.LENGTH_LONG).show();
        if(list.size()>0)
        {
            sm.registerListener(sensorEventListener,(Sensor) list.get(0),SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
        {
            //No accelerometer
        }


        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(FirstActivity.this,VolleyUsage.class);
                startActivity(intent);
            }
        });
        shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(FirstActivity.this,MusicPlay.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(PREF,1);
                String name=sharedPreferences.getString(NAME,null);
                String password=sharedPreferences.getString(PASSWORD,null);
                if(name==null && password==null) {
                    Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog.Builder alert=new AlertDialog.Builder(FirstActivity.this);
                    alert.setTitle("Info!!!");
                    LinearLayout linearLayout=new LinearLayout(getApplicationContext());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    TextView textView=new TextView(getApplicationContext());
                    textView.setText("Already Logged in...");
                    linearLayout.addView(textView);
                    alert.setView(linearLayout);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(FirstActivity.this,"Already Logged in!!!",Toast.LENGTH_SHORT).show();

                        }
                    });

                    alert.show();

                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(FirstActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(PREF,1);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,ListUsers.class);
                startActivity(intent);
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,WebActivity.class);
                startActivity(intent);
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,CameraActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,ListApps.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sm.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sm.unregisterListener(sensorEventListener);
    }
}