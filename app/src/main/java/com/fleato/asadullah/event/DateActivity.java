package com.fleato.asadullah.event;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;


public class DateActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Button b=(Button)findViewById(R.id.savedate);
        final DateActivity e=this;
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Intent eintent=new Intent(e,GetLocationMapsActivity.class);
                //startActivityForResult(eintent, 1);
                DatePicker dp=(DatePicker)findViewById(R.id.datePicker);
                int day=dp.getDayOfMonth();
                int month=dp.getMonth();
                int year=dp.getYear();
                Bundle bn=new Bundle();
                bn.putInt("day",day);
                bn.putInt("month",month);
                bn.putInt("year",year);
                Intent date=new Intent();
                date.putExtras(bn);
                setResult(RESULT_OK,date);
                finish();


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_date, menu);
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


}
