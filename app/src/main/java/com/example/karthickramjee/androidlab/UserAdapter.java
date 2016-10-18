package com.example.karthickramjee.androidlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by karthickramjee on 15/10/16.
 */

public class UserAdapter extends ArrayAdapter<SignData> {
    private  Context context;
    ArrayList<SignData> users=new ArrayList<SignData>();
    public UserAdapter(Context context,ArrayList<SignData> users) {
        super(context, R.layout.listusers, users);
        this.context=context;
        this.users=users;
    }
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=layoutInflater.inflate(R.layout.listusers,viewGroup,false);
        TextView name=(TextView)v.findViewById(R.id.name);
        TextView mail=(TextView)v.findViewById(R.id.mail);
        name.setText(users.get(position).getName());
        mail.setText(users.get(position).getEmail());
        return v;
    }
}
