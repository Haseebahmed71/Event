package com.fleato.asadullah.event;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.TextView;


public class Event_Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_activity);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        Intent i=getIntent();
        String id=i.getStringExtra("id");
        Node e=db.getEvent(id);
        TextView name=(TextView)findViewById(R.id.name);
        name.setText(e.name);
        TextView description=(TextView)findViewById(R.id.description);
        description.setText(e.description);
        TextView ac=(TextView)findViewById(R.id.time);
        String t=""+e.hour+" : "+e.minute;
        ac.setText(t);
        TextView date=(TextView)findViewById(R.id.Date);
        String d=""+e.year+" : "+e.month+" : "+e.day;
        date.setText(d);

        Button b=(Button)findViewById(R.id.map);
        b.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent I=new Intent(Event_activity.this,GetLocationMapsActivity.class);
                startActivity(I);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_activity, menu);
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
