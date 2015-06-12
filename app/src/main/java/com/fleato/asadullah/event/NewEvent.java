package com.fleato.asadullah.event;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NewEvent extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Button b=(Button)findViewById(R.id.button);
        final NewEvent e=this;
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent eintent=new Intent(e,GetDateTimeActivity.class);
                EditText name=(EditText)findViewById(R.id.etFirstName);
                EditText description=(EditText)findViewById(R.id.description);


                if(!name.getText().equals("") && !description.getText().equals("")) {
                    startActivityForResult(eintent, 1);
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onActivityResult(int requestCode,int responseCode,Intent data){
        Bundle b=data.getExtras();
        EditText name=(EditText)findViewById(R.id.etFirstName);
        EditText description=(EditText)findViewById(R.id.description);
        Event e=new Event();
        e.name=name.getText().toString();
        e.description=description.getText().toString();
        e.hour=b.getInt("hour");
        e.minute=b.getInt("minute");
        e.day=b.getInt("day");
        e.month=b.getInt("month");
        e.year=b.getInt("year");
        try {

      //      String query = "CREATE TABLE IF NOT EXISTS  event(_id INTEGER  PRIMARY KEY AUTOINCREMENT,name VARCHAR(200),description VARCHAR(1000),thour INT, tminute INT,tday INT,tmonth INT,tyear INT);" +
        //            "CREATE TABLE IF NOT EXISTS  photo(_id INTEGER PRIMARY KEY AUTOINCREMENT,event_id INT,url VARCHAR(200));";
    //        SQLiteDatabase dbi=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
  //          dbi.execSQL(query);
//            query="insert into event values("+e.name+","+e.description+","+e.hour+","+e.minute+","+e.day+","+e.month+","+e.year+");";
            DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
            db.InsertIntoEvent(e);
            finish();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        



    }
}
