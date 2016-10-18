package com.example.karthickramjee.androidlab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by karthickramjee on 15/10/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="Lab";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="users";

    private static final String EMAIL="email";
    private static final String NAME="name";
    private static final String PASSWORD="password";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE=String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT, %s TEXT)",TABLE_NAME,NAME,EMAIL,PASSWORD);
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void addData(SignData signData)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,signData.getName());
        contentValues.put(EMAIL,signData.getEmail());
        contentValues.put(PASSWORD,signData.getPassword());
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }
    public boolean check(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String column[]={NAME};
        String selection=String.format("%s LIKE ?",NAME);
        String args[]={name};
        Cursor cursor=db.query(TABLE_NAME,column,selection,args,null,null,null,null);
        return cursor.getCount()==1;
    }
    public boolean checkDetails(String name,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String column[]={NAME,PASSWORD};
        String selection=String.format("%s LIKE ? AND %s LIKE ?",NAME,PASSWORD);
        String args[]={name,password};
        Cursor cursor=db.query(TABLE_NAME,column,selection,args,null,null,null,null);
        return cursor.getCount()==1;
    }
    public ArrayList<SignData> getAllUsers()
    {
        ArrayList<SignData> arrayList=new ArrayList<SignData>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                SignData signData=new SignData();
                signData.setName(cursor.getString(0));
                signData.setEmail(cursor.getString(1));
                signData.setPassword(cursor.getString(2));
                arrayList.add(signData);
            }while(cursor.moveToNext());
        }
        return arrayList;
    }
}
