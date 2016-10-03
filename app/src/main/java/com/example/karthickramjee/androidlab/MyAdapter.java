package com.example.karthickramjee.androidlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthickramjee on 03/10/16.
 */
public class MyAdapter extends ArrayAdapter<DataRes> {
    public Context context;
    public ArrayList<DataRes> darr;
    public MyAdapter(Context context, ArrayList<DataRes> dataArray)
    {
        super(context,R.layout.row,dataArray);
        this.context=context;
        this.darr=dataArray;
    }
    public View getView(final int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowview=inflater.inflate(R.layout.row,parent,false);
        TextView name=(TextView)rowview.findViewById(R.id.name);
        TextView num=(TextView)rowview.findViewById(R.id.num);
        TextView mail=(TextView)rowview.findViewById(R.id.mail);
        name.setText(darr.get(position).getName());
        num.setText(String.valueOf(darr.get(position).getNumber()));
        mail.setText(darr.get(position).getMail());
        return rowview;
    }
}
