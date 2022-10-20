package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

public class DBHELPER extends SQLiteOpenHelper {
    public static final String dbname = "login.db";
    public  static final  String tablename3="emails";
    public  final static String emaildb="email";
    public  static final  String passworddb="password";
    public final static String namedb="name";
    public final static String adressdb="adress";
    public  final static String phonedb="phone";
    public  final static String image="imagedb";

    public  static final  int version=3;

    public DBHELPER(@Nullable Context context) {

        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table "+tablename3 +"("+emaildb +" TEXT Primary key ," + ""+passworddb +" TEXT,"+namedb +" TEXT,"+adressdb +" TEXT,"+phonedb +","+image+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists  "+ tablename3 +"");
        onCreate(db);
    }
    public String getTableAsString(SQLiteDatabase db, String tablename3) {
        Log.d(TAG, "getTableAsString called");
        String tableString = String.format("Table %s:\n", tablename3);
         Cursor allRows  = db.rawQuery("SELECT * FROM " + tablename3, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndexOrThrow(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }

        return tableString;
    }

    public Boolean insert(String email, String password,String name,String adresss,String phone,String img) {
        SQLiteDatabase sq = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(emaildb, email);
        values.put(passworddb, password);
        values.put(namedb,name);
        values.put(adressdb,adresss);
        values.put(phonedb,phone);
        values.put(image,img);

        long db = sq.insert(tablename3, null, values);
        if (db == -1) {
            return false;
        } else {
            return true;
        }

    } public Boolean insertimg(String uri) {
        SQLiteDatabase sq = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(image, uri);

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
    public Cursor getdata(String email,String password){
        SQLiteDatabase db=getWritableDatabase();

        String qry= "SELECT * FROM " + tablename3
                + " WHERE " + emaildb + " =\""+email+"\"" ;
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }

    public long getemailscount(){
        SQLiteDatabase db=getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,tablename3);
    }
    public  void delete_all_data(){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(tablename3,null,null);
        db.execSQL("delete from "+tablename3);
        db.close();
    }
    public Boolean updatedata(String email,String name,String adresss,String phone) {
        SQLiteDatabase sq = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(emaildb, email);
        values.put(namedb, name);
        values.put(adressdb, adresss);
        values.put(phonedb, phone);


        long db = sq.update(tablename3,values,emaildb+ " =\""+email+"\"",null);
        if (db == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean updatedata2(String img,String email) {
        SQLiteDatabase sq = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(image, img);


        long db = sq.update(tablename3,values,emaildb+ " =\""+email+"\"",null);
        if (db == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean deleteimage(Uri uri){
        SQLiteDatabase db=getWritableDatabase();

        String args[]={String.valueOf(uri)};
        int result=  db.delete(tablename3,"name=?",args);
        return result>0;
    }
    public Boolean updatedataimage(String uri) {
        SQLiteDatabase sq = getWritableDatabase();
        ContentValues values = new ContentValues();
       values.put(image,uri);
        long db = sq.update(tablename3,values,emaildb+ " =\""+uri+"\"",null);
        if (db == -1) {
            return false;
        } else {
            return true;
        }
    }

}
