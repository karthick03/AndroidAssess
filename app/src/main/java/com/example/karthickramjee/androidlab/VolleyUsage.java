package com.example.karthickramjee.androidlab;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import fi.iki.elonen.NanoHTTPD;


public class VolleyUsage extends Activity {
    private static final int PORT = 8765;
    private MyServer server;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_usage);

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView textIpaddr = (TextView) findViewById(R.id.ipaddr);
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        final String formatedIpAddress = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
        textIpaddr.setText("Please access! http://" + formatedIpAddress + ":" + PORT);
        Log.d("IP",formatedIpAddress);

        try {
            server = new MyServer();
            //server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public class MyServer extends NanoHTTPD {

        public MyServer() throws IOException {
            super(PORT);
            start();
            System.out.println( "\nRunning! Point your browers to http://localhost:8080/ \n" );
        }

        @Override
        public Response serve(IHTTPSession session) {
            String msg = "<html><body><h1>Hello server</h1>\n";
            msg += "<p>We serve " + session.getUri() + " !</p>";
            String json="{\n" +
                    "  \"person\": [\n" +
                    "    {\n" +
                    "      \"id\": \"1\",\n" +
                    "      \"name\": \"Karthick\",\n" +
                    "      \"number\": \"9991122331\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": \"2\",\n" +
                    "      \"name\": \"Name2\",\n" +
                    "      \"number\": \"1122334455\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": \"1\",\n" +
                    "      \"name\": \"Name3\",\n" +
                    "      \"number\": \"6677889900\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            String filePath = Environment.getExternalStorageDirectory()+"/Documents/song/aym.mp3";
            try {
                return newChunkedResponse(Response.Status.OK, "audio/mpeg3",new FileInputStream(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return newFixedLengthResponse( Response.Status.OK, "application/json",json);
        }
    }
}