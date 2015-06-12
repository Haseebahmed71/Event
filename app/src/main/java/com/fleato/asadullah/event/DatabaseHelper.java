package com.fleato.asadullah.event;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Asadullah on 6/8/2015.
 */



 public class DatabaseHelper extends SQLiteOpenHelper {
        static final String name = "mydb";

        static final int databaseVersion = 3;
        private Context c;

        public DatabaseHelper(Context context) {
            super(context, name, null, databaseVersion);
            c = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.v("verbose","Database on create called");

            String query = "CREATE TABLE IF NOT EXISTS  event(id INTEGER  PRIMARY KEY AUTOINCREMENT,name VARCHAR(200),description VARCHAR(1000),thour INT, tminute INT,tday INT,tmonth INT,tyear INT);" +
                    "CREATE TABLE IF NOT EXISTS  photo(id INTEGER PRIMARY KEY AUTOINCREMENT,event_id INT,url VARCHAR(200));";
            try {
                db.execSQL(query);

            } catch (Exception e) {
                Log.v("verbose", "tables error");
                e.printStackTrace();
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS event" );
            db.execSQL("DROP TABLE IF EXISTS photo");
// Create tables again
            onCreate(db);
        }
    public long InsertIntoEvent(Event e){
        ContentValues values=new ContentValues();
        values.put("name",e.name);
        values.put("description",e.description);
        values.put("thour",e.hour);
        values.put("tminute",e.minute);
        values.put("tday",e.day);
        values.put("tmonth",e.month);
        values.put("tyear",e.year);
        try {


            SQLiteDatabase db = this.getWritableDatabase();
            long id = db.insert("event", "description", values);
            db.close();
            Log.v("verbose","event created");
            String s="inserted"+id;
            Toast toast = Toast.makeText(c,s, Toast.LENGTH_LONG);
            toast.show();
            return id;

        }
        catch (Exception ex){
            ex.printStackTrace();

        }
        return  0;

    }
    public long InsertIntoPhoto(int eventid,String url){
        ContentValues values=new ContentValues();
        values.put("event_id",eventid);
        values.put("url",url);
        try {


            SQLiteDatabase db = this.getWritableDatabase();
            long id = db.insert("photo", "url", values);
            db.close();
            return id;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  0;

    }

    public ArrayList<Node> getEvents(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery="select * from event";
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Node> arrayList=new ArrayList<Node>();
        try {
            if (cursor.moveToFirst()) {
                do {
                    Node n = new Node();

                    n.name = cursor.getString(cursor.getColumnIndex("name"));
                    n.description = cursor.getString(cursor.getColumnIndex("description"));
                    n.day = cursor.getInt(cursor.getColumnIndex("tday"));
                    n.month = cursor.getInt(cursor.getColumnIndex("tmonth"));
                    n.year = cursor.getInt(cursor.getColumnIndex("tyear"));
                    n.hour = cursor.getInt(cursor.getColumnIndex("thour"));
                    n.minute = cursor.getInt(cursor.getColumnIndex("tminute"));
                    n.id=cursor.getInt(cursor.getColumnIndex("id"));
                    arrayList.add(n);


                } while (cursor.moveToNext());


            }
            db.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }




        return arrayList;
    }

    public Node getEvent(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery="select * from event where id="+id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        Node n = new Node();

        n.name = cursor.getString(cursor.getColumnIndex("name"));
        n.description = cursor.getString(cursor.getColumnIndex("description"));
        n.day = cursor.getInt(cursor.getColumnIndex("tday"));
        n.month = cursor.getInt(cursor.getColumnIndex("tmonth"));
        n.year = cursor.getInt(cursor.getColumnIndex("tyear"));
        n.hour = cursor.getInt(cursor.getColumnIndex("thour"));
        n.minute = cursor.getInt(cursor.getColumnIndex("tminute"));
        n.id=cursor.getInt(cursor.getColumnIndex("id"));


    return n;
    }


}
