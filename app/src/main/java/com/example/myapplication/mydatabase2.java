package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class mydatabase2 extends SQLiteOpenHelper {
    public  static final  String dbname="Foode";
    public  static final  String tablename1="foodn";
    public  static final  String idname="id";
    public  static final  String imagename="image";
    public  static final  String pricename="price";
    public  static final  String name="name";
    public  static final  int version=1;
    public mydatabase2(Context context){
        super(context,dbname,null,version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table "+tablename1 +"("+idname +" Integer Primary key ," + ""+imagename +" Integer,"+pricename +" REAL,"+name +" TEXT unique)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table IF Exists foodn");
        onCreate(db);

    }
    public boolean inseartfood(loveclass lovefood){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(imagename,lovefood.getImg());
        contentValues.put(pricename,lovefood.getPrice());
        contentValues.put(name,lovefood.getName());
        long result=  db.insert(tablename1,null,contentValues);
        if(result==-1){
            return false;
        }
        else
            return true;
    }
    public boolean upatecar(loveclass lovefood){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(imagename,lovefood.getImg());
        contentValues.put(pricename,lovefood.getPrice());
        contentValues.put(name,lovefood.getName());
        String args[]=new String[]{lovefood.getCardnumber()+""};
        int result=  db.update(tablename1,contentValues,"id=?",args);
        if(result==0){
            return false;
        }
        else
            return true;
    }
    public long getfoodcount(){
        SQLiteDatabase db=getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,tablename1);
    }
    public Cursor getdata(){
        SQLiteDatabase db=getWritableDatabase();
        String qry="select * from foodn order by name ";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }
    public boolean deletefood(String name){
        SQLiteDatabase db=getWritableDatabase();

        String args[]={name};
        int result=  db.delete(tablename1,"name=?",args);
        return result>0;
    }
    public ArrayList<loveclass>getallfood(){
        ArrayList<loveclass>loveclassArrayList=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from "+tablename1+"",null);
        if( cursor.moveToFirst()){
            do {
                int image = cursor.getInt(1);
                double price = cursor.getDouble(2);
                String name = cursor.getString(3);
                loveclass f=new loveclass(image,price,name);
                loveclassArrayList.add(f);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }

        return  loveclassArrayList;
    }

    public  void delete_all_data(){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(tablename1,null,null);
        db.execSQL("delete from "+tablename1);
        db.close();
    }
    public Cursor search(String name){
        SQLiteDatabase db=getWritableDatabase();
        String qry="select * from foodn Where name like name";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }
}
