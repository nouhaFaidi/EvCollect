package com.example.evcollect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.evcollect.AppDB.mydb;


public class Login extends Activity implements View.OnClickListener{
    HttpURLConnection conn,conn1;
    ProgressDialog prgDialog;
    TextView errorMsg;
    Button cnx;
    EditText adrs,login,pass;
    String username,password;
    URL url,u;
    CookieHandler cookie;
    String aa;
    static JSONObject jsonParam;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.login);
        login=(EditText)findViewById( R.id.user);
        pass=(EditText)findViewById( R.id.pwd);
        cnx = (Button) findViewById( R.id.b1);
        cnx.setOnClickListener(this);
        prgDialog=new ProgressDialog(this);
        prgDialog.setMessage("please wait...");
        prgDialog.setCancelable(false);
        mydb=new DBhelper( this );



      // toolbar=findViewById( R.id.toolbar );
      //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         //   setActionBar( toolbar );
        }

  /*  public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
               // DO EDIT
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
*/
    public void loginUser(){

         username=login.getText().toString();
         password=pass.getText().toString();


        URL ur = null;




        JSONObject js=new JSONObject();


        try {
            js.put("username",username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            js.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {

            ur = new URL("https://demo12.evouala.com/auth/login");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new webs(ur,js).execute();


        Intent i=new Intent(this,Getwebjson.class);
        startActivity(i);



    }




    @Override
    public void onClick(View v) {
        loginUser();

        mydb.insertApp( "2020-02-11 19:56+0100","2020-02-11 22:49+0100","","confirmation","1421","datasource ","https://demo12.evouala.com/api/resource/describe?id=99&active=True");
        mydb.insertApp( "2019-06-11T20:18:21.777849 + 00: 00","2019-06-22T22:31:09.828483+00:00","","Carto basemap","99","datasource ","https://demo12.evouala.com/api/resource/describe?id=99&active=True");
        mydb.insertApp( "2019-06-11T20: 18: 21.777849 + 00: 00","2019-06-11T20:18:21.777849 + 00: 00","","BING map","92","datasource ","https://demo12.evouala.com/api/resource /describe?id=92&active=True");
        mydb.insertApp( "2019-06-22T22:16:34.030864+00:00","2019-06-22T22:16:34.030864+00: 00","","ESRI World Ocean","151","datasource ","https://demo12.evouala.com/api/resource/describe?id=151&active=True");
        mydb.insertApp( "2019-06-11 21:18+0100","2019-06-22 23:24+0100","","Carte Gouvernement du Québec","111","source de données ","https://demo12.evouala.com/api/resource/describe?id=111&active=True");

       /* Intent i=new Intent(this,AppDB.class);
        startActivity(i);*/
    }


                }
