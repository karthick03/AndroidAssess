package com.example.karthickramjee.androidlab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class CameraActivity extends AppCompatActivity {

    ImageView img;
    Button clock,zoom;
    AutoCompleteTextView act;
    TextToSpeech t1 ;
    String lang[]={"a","abc","ba","ca","cd"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        img=(ImageView)findViewById(R.id.img);
        clock=(Button)findViewById(R.id.clock);
        zoom=(Button)findViewById(R.id.zoom);
        act=(AutoCompleteTextView)findViewById(R.id.act);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(CameraActivity.this,android.R.layout.select_dialog_item,lang);
        act.setThreshold(1);
        act.setAdapter(adapter);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationUtils=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.clockwise);
                img.startAnimation(animationUtils);
            }
        });
        zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationUtils=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
                img.startAnimation(animationUtils);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bp=(Bitmap)data.getExtras().get("data");
        img.setImageBitmap(bp);
        //MediaRecording
        /*MediaRecorder mr=new MediaRecorder();
        String out= Environment.getExternalStorageDirectory().getAbsolutePath()+"/file.3gp";
        mr.setOutputFile(out);
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mr.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);*/
        //TextToSpeech
    }
}
