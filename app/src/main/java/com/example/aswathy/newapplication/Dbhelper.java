package com.example.aswathy.newapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2/4/2019.
 */
public class Dbhelper extends SQLiteOpenHelper {
    public static final String Dbname = "Mydb.db";
    public static final String TableName = "register";
    public static final String col1 = "id";
    public static final String col2 = "name";
    public static final String col3 = "email";
    public static final String col4 = "mobileno";
    public static final String col5 = "username";
    public static final String col6 = "password";



    public Dbhelper(Context context) {
        super(context,Dbname,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TableName + "(" + col1 + " integer primary key autoincrement," + col2 + " text," + col3 + " text," + col4 + " text," + col5 + " text," + col6 + " text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "drop table if exists" + TableName;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
    //insert
    public boolean insertData(String name, String email,String mobileno,String username,String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, name);
        contentValues.put(col3, email);
        contentValues.put(col4, mobileno);
        contentValues.put(col5, username);
        contentValues.put(col6, password);


        long status = sqLiteDatabase.insert(TableName, null, contentValues);
        if (status == -1) {
            return false;
        }
        else{
            return true;
        }
    }
    //retrieve
    public Cursor compare(String username)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cur=sqLiteDatabase.rawQuery("SELECT * FROM "+TableName+" WHERE "+col5+"='"+username+"'",null);
        return cur;
    }
    //delete
    public  boolean DeleteData(String id){
        SQLiteDatabase db =this.getWritableDatabase();
        long status = db.delete(TableName,col1 +"=" +id,null);
        if (status == -1) {
            return false;
        }
        else{
            return true;
        }

    }
}
