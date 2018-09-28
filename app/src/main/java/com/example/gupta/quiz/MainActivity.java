package com.example.gupta.quiz;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new DBHelper(this);
        //addData();
        //viewData();
        //updateData();
        //deleteData();
    }

    public void addData(){
        boolean isInserted=mydb.insertData("question","option 1","option 2","option 3","option 4","correct answer");
        if(isInserted==true)
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Error occured",Toast.LENGTH_LONG).show();
    }

    public void viewData(){
        Cursor c=mydb.getAllData();
        if(c.getCount()==0){
            showMsg("Error","No recocrds found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (c.moveToNext()){
            buffer.append(c.getString(0)+"\n");
            buffer.append(c.getString(1)+"\n");
            buffer.append(c.getString(2)+"\n");
            buffer.append(c.getString(3)+"\n");
            buffer.append(c.getString(4)+"\n");
            buffer.append(c.getString(5)+"\n");
            buffer.append(c.getString(6)+"\n\n");
        }
        showMsg("Data",buffer.toString());
    }

    public void showMsg(String title,String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

    public void updateData(){
        boolean isUpdated=mydb.updateData("1","updatedques","uo1","uo2","uo3","uo4","c");
        if(isUpdated==true){
            viewData();
        }
    }

    public void deleteData(){
        Integer deletedrows=mydb.deleteData("1");
        if(deletedrows>0) viewData();
    }
}
