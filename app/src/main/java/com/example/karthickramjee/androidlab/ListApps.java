package com.example.karthickramjee.androidlab;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListApps extends AppCompatActivity {

    ListView listapps;
    ArrayList<String> pkg,app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_apps);
        listapps=(ListView)findViewById(R.id.listapps);
        Intent main=new Intent(Intent.ACTION_MAIN,null);
        main.addCategory(Intent.CATEGORY_LAUNCHER);
        List list=getPackageManager().queryIntentActivities(main,0);
        pkg=new ArrayList<String>();
        app=new ArrayList<String>();
        for(Object object: list)
        {
            ResolveInfo info=(ResolveInfo)object;
            String pkgname=info.activityInfo.applicationInfo.packageName.toString();
            pkg.add(pkgname);
            String title=(String)((info!=null)?getBaseContext().getPackageManager().getApplicationLabel(info.activityInfo.applicationInfo):"???");
            app.add(title);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,app);
        listapps.setAdapter(adapter);
        listapps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String appname=pkg.get(position);
                Intent intent=getPackageManager().getLaunchIntentForPackage(appname);
                if(intent!=null)
                    startActivity(intent);
            }
        });
    }
}
