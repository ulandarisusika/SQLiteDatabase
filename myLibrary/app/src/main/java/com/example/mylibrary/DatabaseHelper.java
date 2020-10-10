package com.example.mylibrary;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.security.Key;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME="student_database";
    private static final int DATABASE_VERSION=1;
    private static final String Table_Student="students";
    private static final String Key_Id="id";
    private static final String Key_Firstname="name";
    private static final String Create_Table_Students="CREATE TABLE "+Table_Student+" ("+Key_Id+" " +
            "INTEGER PRIMARY KEY AUTOINCREMENT, "+Key_Firstname+" TEXT);";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

        Log.d("table",Create_Table_Students);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table_Students);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '"+ Table_Student +"'");
        onCreate(db);
    }

    public long addStudentDetail(String student) {
        SQLiteDatabase db = this.getWritableDatabase();
//         Creating content values
         ContentValues values = new ContentValues();
         values.put(Key_Firstname, student);
//         insert row in students table
         long insert = db.insert(Table_Student,
         null, values);

         return insert;
    }

    public ArrayList<String> getAllStudentsList() {
        ArrayList<String> studentsArrayList = new ArrayList<String>();
        String name="";
        String selectQuery = "SELECT  * FROM " + Table_Student;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
         if (c.moveToFirst()) {
            do {
                 name = c.getString(c.getColumnIndex(Key_Firstname));
                 //adding to Students list
                 studentsArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array", studentsArrayList.toString());
         }
         return studentsArrayList;
         }
    }



