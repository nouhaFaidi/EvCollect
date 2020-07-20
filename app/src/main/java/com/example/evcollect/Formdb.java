package com.example.evcollect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class Formdb extends Activity implements View.OnClickListener{
    public final static String EXTRA_MESSAGE = "com.example.AddressBook.MESSAGE";
    private ListView obj;
    public static DBhelper mydb;

    public Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.formdb);

        mydb=new DBhelper( this );
       b1=(Button)findViewById( R.id.Ajouter );
        b2=(Button)findViewById( R.id.Synch );
        b1.setOnClickListener( this );
        b2.setOnClickListener( this );
        mydb.insertForm( "003-20200612","Pourvoirie Les Portes du Diable","455","3","03/06/2020" );
        mydb.insertForm( "003-20200603","La zone verte","1000","4","01/07/2020" );
        ArrayList array_list =mydb.getAllFormulaires();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,array_list);
//adding it to the list view.
        obj = (ListView)findViewById( R.id.list);

        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
// TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
                Intent intent = new
                        Intent(getApplicationContext(),com.example.evcollect.Form.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }




    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }


    @Override
    public void onClick(View v) {
        if(v==b1){
            Bundle dataBundle = new Bundle();
            dataBundle.putInt("id", 0);
            Intent intent = new Intent(getApplicationContext(),com.example.evcollect.Form.class);
            intent.putExtras(dataBundle);
            startActivity(intent);
        }
        if(v==b2){
            Intent i=new Intent( this,Auth.class );
            startActivity( i );
        }
    }

}

