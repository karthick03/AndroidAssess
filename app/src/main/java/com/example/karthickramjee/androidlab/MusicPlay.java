package com.example.karthickramjee.androidlab;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

public class MusicPlay extends AppCompatActivity {

    private String PREF="key";
    EditText sharedpref;
    Button save,clear,list;
    ListView lv;
    BluetoothAdapter ba;
    TextView value;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);

        sharedpref=(EditText)findViewById(R.id.sharedpref);
        save=(Button)findViewById(R.id.save);
        clear=(Button)findViewById(R.id.clear);
        value=(TextView)findViewById(R.id.value);
        lv=(ListView)findViewById(R.id.lv);
        list=(Button)findViewById(R.id.list);
        ba=BluetoothAdapter.getDefaultAdapter();
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        if(!ba.isEnabled())
        {
            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,0);
        }
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
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.speak("Hello world",TextToSpeech.QUEUE_FLUSH,null);
                if(!ba.isEnabled())
                {
                    Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent,0);
                }
                Set<BluetoothDevice> bluetoothAdapters=ba.getBondedDevices();
                ArrayList<SignData> list=new ArrayList<SignData>();
                for(BluetoothDevice ba:bluetoothAdapters)
                {
                    SignData signData=new SignData();
                    signData.setName(ba.getName());
                    signData.setEmail(ba.getName());
                    signData.setPassword(ba.getName());
                    list.add(signData);
                    Log.d("NAME",ba.getName());
                }
                UserAdapter userAdapter=new UserAdapter(getApplicationContext(),list);
                lv.setAdapter(userAdapter);
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
