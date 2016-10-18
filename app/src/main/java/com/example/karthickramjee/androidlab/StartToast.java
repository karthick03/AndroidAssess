package com.example.karthickramjee.androidlab;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by karthickramjee on 16/10/16.
 */

public class StartToast extends Service {
    private Handler handler;
    @Override
    public int onStartCommand(Intent intent,int flags,int start)
    {
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(getApplicationContext(),"Toast",Toast.LENGTH_SHORT).show();
            }
        };
        Thread thread=new Thread(){
            @Override
            public void run() {
                Message msg=new Message();
                msg.obj="Toast";
                handler.sendMessage(msg);
            }
        };
        thread.start();
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
