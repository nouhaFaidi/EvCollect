package com.example.evcollect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class AppDB extends Activity implements View.OnClickListener{
    public final static String EXTRA_MESSAGE = "com.example.AddressBook.MESSAGE";
    private ListView obj;
    public static DBhelper mydb;

ImageButton ib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.appdb);
        mydb = new DBhelper(this);
ib=(ImageButton)findViewById( R.id.btn);
ib.setOnClickListener(this);
        ArrayList array_list = mydb.getAllApplications();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,array_list);
//adding it to the list view.
        obj = (ListView)findViewById( R.id.listView1);

        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
// TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new
                        Intent(getApplicationContext(),com.example.evcollect.Formdb.class);
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
        if(v==ib){
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("id", 0);
        Intent intent = new
                Intent(getApplicationContext(),com.example.evcollect.DisplayApp.class);
        intent.putExtras(dataBundle);
        startActivity(intent);
        }

    }
}
