package com.fleato.asadullah.event;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;


public class GetDateTimeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_date_time);

        Button b=(Button)findViewById(R.id.savetime);
        final GetDateTimeActivity e=this;
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent eintent=new Intent(e,DateActivity.class);




                    startActivityForResult(eintent, 1);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_date_time, menu);
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
        TimePicker tp=(TimePicker)findViewById(R.id.timePicker);
        int hour=tp.getCurrentHour();
        int minute=tp.getCurrentMinute();
        Bundle b=data.getExtras();
        b.putInt("hour",hour);
        b.putInt("minute",minute);

        Intent timeintent=new Intent();
        timeintent.putExtras(b);
        setResult(RESULT_OK,timeintent);
        finish();


    }
}
