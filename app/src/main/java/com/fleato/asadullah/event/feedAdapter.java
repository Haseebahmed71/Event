package com.fleato.asadullah.event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Asadullah on 6/6/2015.
 */


public class feedAdapter extends BaseAdapter {

    ArrayList<Node> arrayList;
    Context mycontext;
    public feedAdapter(Context c){
        mycontext=c;
        arrayList=new ArrayList<Node>();

        DatabaseHelper db = new DatabaseHelper(this.mycontext);
        arrayList=db.getEvents();





    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=(LayoutInflater)mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(R.layout.node,parent,false);
        TextView name=(TextView)row.findViewById(R.id.ename);
        TextView description=(TextView)row.findViewById(R.id.description);
        TextView id=(TextView)row.findViewById(R.id.myid);
        Node n=arrayList.get(position);
        String myid=""+n.id;
        name.setText(n.name);
        description.setText(n.description);
        id.setText(myid);


        return row;
    }
}
