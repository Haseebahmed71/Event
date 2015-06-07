package com.fleato.asadullah.event;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;


public class MainActivity extends ActionBarActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new feedAdapter(this));

            FacebookSdk.sdkInitialize(getApplicationContext());
            final SharedPreferences sh = getSharedPreferences("mydata", Context.MODE_PRIVATE);
            String user = sh.getString("user", null);


            final Intent in = new Intent(this, login_main.class);

            if (user == null) {
                startActivity(in);
            }


        }
        catch(Exception e){


        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        try {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        catch(Exception e){

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        try {
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.logout) {
                final SharedPreferences sh = getSharedPreferences("mydata", Context.MODE_PRIVATE);
                final Intent in = new Intent(this, login_main.class);
                LoginManager m = LoginManager.getInstance();
                m.logOut();
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("user", null);
                editor.commit();
                startActivity(in);

                return true;
            }

            return super.onOptionsItemSelected(item);
        }
        catch (Exception e){
            return super.onOptionsItemSelected(item);


        }
    }
}
