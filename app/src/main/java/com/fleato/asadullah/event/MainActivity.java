package com.fleato.asadullah.event;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.splunk.mint.Mint;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Mint.initAndStartSession(this, "add4d256");
            //this.getstuff();


            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new feedAdapter(this));
            listView.setOnItemClickListener(this);

            FacebookSdk.sdkInitialize(getApplicationContext());
            final SharedPreferences sh = getSharedPreferences("mydata", Context.MODE_PRIVATE);
            String user = sh.getString("user", null);
            final Intent in = new Intent(this, login_main.class);
            AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);



            if (user == null) {
               startActivity(in);
               // Intent eintent=new Intent(this,NewEvent.class);
                //startActivity(eintent);

            }


        }
        catch(Exception e){
            e.printStackTrace();


        }


    }

    private void registerListOnClickListner() {
        ListView listView1=(ListView)findViewById(R.id.listView);


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
                final Intent in = new Intent(MainActivity.this, login_main.class);
                LoginManager m = LoginManager.getInstance();
                m.logOut();
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("user", null);
                editor.commit();
                startActivity(in);

                return true;
            }
            else if(id== R.id.newevent){
                Intent eintent=new Intent(this,NewEvent.class);
                startActivityForResult(eintent,0);
                return true;
            }


        }
        catch (Exception e){


        }
        if(item.getItemId()==R.id.crash){
            String s=null;
            Log.v("verbose",s);
            return  true;
        }


        return super.onOptionsItemSelected(item);

    }
    public void getstuff(){
        String s= getResources().getString(R.string.in);
        Uri sentURI = Uri.parse(s);
        String[] reqCols = new String[] { "_id", "address", "body" };
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(sentURI, reqCols, null, null, null);
        c.moveToFirst();
        while(c.moveToNext()){
            Log.v("verbose","addr="+c.getString(c.getColumnIndexOrThrow("address"))+",msg="+c.getString(c.getColumnIndexOrThrow("body")));
        }


    }
    public  void onActivityResult(int requestCode,int resultCode,Intent data){




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView c = (TextView) view.findViewById(R.id.myid);
        Intent i=new Intent(MainActivity.this,Event_Activity.class);
        i.putExtra("id",c.getText().toString());
        startActivity(i);

    }
}
