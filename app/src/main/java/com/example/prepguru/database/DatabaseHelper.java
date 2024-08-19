package com.example.prepguru.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;


public  class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME="Login.db";

    public DatabaseHelper( Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email Text primary key,password Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");

    }
    public Boolean insertData(String email,String password){
       SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("email",email);
        c.put("password",password);
        long result=MyDB.insert("users",null,c);
        if(result==-1)return false;
        else
            return true;
    }
    public boolean checkusername(String email){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?",new String[] {email});
        if(cursor.getCount()>0 )
            return true;
        else
            return false;
    }
    public Cursor login(String email, String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?" ,new String[] {email,password});
        return cursor;
    }

}
