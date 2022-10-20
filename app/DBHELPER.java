package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHELPER extends SQLiteOpenHelper {
    public static final String dbname = "login.db";
    public  static final  String tablename3="emails";
    public  static final  String emaildb="email";
    public  static final  String passworddb="password";
    public  static final  String namedb="name";
    public  static final  String adressdb="adress";
    public  static final  String phonedb="phone";
    public  static final  int version=2;

    public DBHELPER(@Nullable Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ tablename3 +"("+ emaildb +" TEXT primary key,"+ passworddb +"TEXT,"+namedb+" TEXT,"+ adressdb +" TEXT,"+ phonedb +"TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists emails");
    }

    public Boolean insert(String email, String password,String name,String adresss,String phone) {
        SQLiteDatabase sq = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(emaildb, email);
        values.put(passworddb, password);
        values.put(namedb,name);
        values.put(adressdb,adresss);
        values.put(phonedb,phone);

        long db = sq.insert(tablename3, null, values);
        if (db == -1) {
            return false;
        } else {
            return true;
        }

    }
public  boolean check(String email){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
       Cursor cursor= sqLiteDatabase.rawQuery("select * from "+ tablename3 +" where "+emaildb +"=?",new String[]{email});
       if(cursor.getCount()>0)
           return true;
       else
           return false;
}
    public  boolean checkemailpassword(String email,String password){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from "+tablename3+" where "+emaildb+"=? and "+passworddb+"=?",new String[]{email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Cursor getdata(){
        SQLiteDatabase db=getWritableDatabase();
        String qry="select * from emails order by name ";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }
}
