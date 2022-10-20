package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class mydatabase extends SQLiteOpenHelper {
    public  static final  String dbname="Food";
    public  static final  String tablename="foode";
    public  static final  String idname="id";
    public  static final  String imagename="image";
    public  static final  String pricename="price";
    public  static final  String name="name";
    public  static final  int version=1;
    public mydatabase(Context context){
        super(context,dbname,null,version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("Create Table "+tablename +"("+idname +" Integer Primary key ," + ""+imagename +" Integer,"+pricename +" REAL,"+name +" TEXT unique)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table IF Exists foode");
        onCreate(db);

    }
    public boolean inseartfood(fooddb food){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(imagename,food.getImg());
        contentValues.put(pricename,food.getPrice());
        contentValues.put(name,food.getName());
      long result=  db.insert(tablename,null,contentValues);
        if(result==-1){
            return false;
        }
        else
            return true;
    }
    public boolean upatecar(fooddb food){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(imagename,food.getImg());
        contentValues.put(pricename,food.getPrice());
        contentValues.put(name,food.getName());
        String args[]=new String[]{food.getCardnumber()+""};
        int result=  db.update(tablename,contentValues,"id=?",args);
        if(result==0){
            return false;
        }
        else
            return true;
    }
    public long getfoodcount(){
        SQLiteDatabase db=getReadableDatabase();
       return DatabaseUtils.queryNumEntries(db,tablename);
    }
    public Cursor getdata(){
        SQLiteDatabase db=getWritableDatabase();
        String qry="select * from foode order by name ";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }
    public boolean deletefood(String name){
        SQLiteDatabase db=getWritableDatabase();

        String args[]={name};
        int result=  db.delete(tablename,"name=?",args);
       return result>0;
    }
    public ArrayList<fooddb>getallfood(){
        ArrayList<fooddb>fooddbArrayList=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from "+tablename+"",null);
       if( cursor.moveToFirst()){
           do {
               int image = cursor.getInt(1);
               double price = cursor.getDouble(2);
               String name = cursor.getString(3);
               fooddb f=new fooddb(image,price,name);
               fooddbArrayList.add(f);
           }while (cursor.moveToNext());
           cursor.close();
           db.close();
       }

        return  fooddbArrayList;
    }

public  void delete_all_data(){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(tablename,null,null);
        db.execSQL("delete from "+tablename);
        db.close();
}
}
