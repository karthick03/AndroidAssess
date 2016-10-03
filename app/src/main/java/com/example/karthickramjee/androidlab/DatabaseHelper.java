package com.example.karthickramjee.androidlab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by karthickramjee on 03/10/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="AndroidLab";
    private static final String TABLE_NAME="Special";
    private static final String KEY_NAME="name";
    private static final String KEY_NUM="number";
    private static final String KEY_MAIL="mail";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+KEY_NUM+" INTEGER PRIMARY KEY, "+ KEY_NAME+" TEXT, "+KEY_MAIL+" TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    void addData(DataRes dr)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,dr.getName());
        contentValues.put(KEY_NUM,String.valueOf(dr.getNumber()));
        contentValues.put(KEY_MAIL,dr.getMail());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }
    public DataRes getData(int num)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{KEY_NUM,KEY_NAME,KEY_MAIL},KEY_NUM + " = ?",new String[]{String.valueOf(num)},null,null,null,null);
        if(!cursor.moveToFirst())
        {
            return null;
        }
        //cursor.moveToFirst();
        DataRes res=new DataRes(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return res;
    }
    public ArrayList<DataRes> getAllData()
    {
        ArrayList<DataRes> arrayList=new ArrayList<DataRes>();
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                DataRes dataRes=new DataRes();
                dataRes.setNumber(Integer.parseInt(cursor.getString(0)));
                dataRes.setName(cursor.getString(1));
                dataRes.setMail(cursor.getString(2));
                arrayList.add(dataRes);

            }while(cursor.moveToNext());
        }
        return arrayList;
    }
    public void deleteData(int num)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_NUM+" = ?",new String[]{String.valueOf(num)});
        db.close();
    }
    public int getCount()
    {
        String countQuery="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }
}
