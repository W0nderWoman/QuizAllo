package com.example.gupta.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="quizallo.db";
    public static final String TABLE_NAME="sportsquiz_table";
    public static final String COL_1="SNo";
    public static final String COL_2="Ques";
    public static final String COL_3="option1";
    public static final String COL_4="option2";
    public static final String COL_5="option3";
    public static final String COL_6="option4";
    public static final String COL_7="right_option";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"("
                +COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_2+" VARCHAR(100),"
                +COL_3+" VARCHAR(50),"
                +COL_4+" VARCHAR(50),"
                +COL_5+" VARCHAR(50),"
                +COL_6+" VARCHAR(50),"
                +COL_7+" VARCHAR(50)"
                +");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String ques,String o1,String o2,String o3,String o4,String righto){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_2,ques);
        values.put(COL_3,o1);
        values.put(COL_4,o2);
        values.put(COL_5,o3);
        values.put(COL_6,o4);
        values.put(COL_7,righto);
        long result=db.insert(TABLE_NAME,null,values);
        if(result==-1) return false;
        return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String ques,String o1,String o2,String o3,String o4,String righto){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_1,id);
        values.put(COL_2,ques);
        values.put(COL_3,o1);
        values.put(COL_4,o2);
        values.put(COL_5,o3);
        values.put(COL_6,o4);
        values.put(COL_7,righto);
        db.update(TABLE_NAME,values,COL_1+" = ? ",new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,COL_1+" = ?",new String[]{id});
    }
}
