package com.example.karthickramjee.androidlab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MusicPlay extends AppCompatActivity {

    private String PREF="key";
    EditText sharedpref;
    Button save,clear;
    TextView value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);

        sharedpref=(EditText)findViewById(R.id.sharedpref);
        save=(Button)findViewById(R.id.save);
        clear=(Button)findViewById(R.id.clear);
        value=(TextView)findViewById(R.id.value);
        value.setText(getValue());
        startService(new Intent(this,StartToast.class));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences;
                SharedPreferences.Editor editor;
                sharedPreferences=getApplicationContext().getSharedPreferences(PREF,2);
                String getvalue=sharedpref.getText().toString();
                if(getvalue.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter something",Toast.LENGTH_SHORT).show();
                    return;
                }
                editor=sharedPreferences.edit();
                editor.putString(PREF,getvalue);
                editor.commit();
                value.setText(getValue());
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences;
                SharedPreferences.Editor editor;
                sharedPreferences=getApplicationContext().getSharedPreferences(PREF,2);
                editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                value.setText(getValue());
            }
        });
    }

    public String getValue() {
        String result="Shared Preference value:";
        SharedPreferences spref=getApplicationContext().getSharedPreferences(PREF,0);
        result+=spref.getString(PREF,null);
        return result;
    }
}
