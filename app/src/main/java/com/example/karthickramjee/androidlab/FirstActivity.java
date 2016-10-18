package com.example.karthickramjee.androidlab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class FirstActivity extends Activity {


    Button db,volley,shared,login,signup,logout,view;
    private final String PREF="value";
    private final String NAME="name";
    private final String PASSWORD="password";
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
    }
}