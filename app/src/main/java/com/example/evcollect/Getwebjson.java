package com.example.evcollect;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.android.volley.VolleyLog.TAG;
import static com.example.evcollect.AppDB.mydb;

public class Getwebjson extends Activity implements View.OnClickListener {
    RequestQueue rq;
    EditText edtSearch;
    ListView listView;
    //SimpleArcLoader simpleArcLoader;


    public static List<App> lista = new ArrayList<>();
    //CountryModel countryModel;
    //myCustomAdapter customerAdapter;
    App app;
    ArrayList<ArrayList<String>> lst1 = new ArrayList<ArrayList<String>>();
    ArrayList<App> lstApp = new ArrayList<App>();
    ArrayList<String> slst1 = new ArrayList<String>();
    ArrayList<ArrayList<String>> lst1_1 = new ArrayList<ArrayList<String>>();
    ArrayList<String> slst1_1 = new ArrayList<String>();
    static StringBuilder a;
    public static Pattern pattern;
    public static Matcher matcher;
    public Button b, loadBtn;
    public String[] apps, apps1, apps2;
    public String[] tableauApp;
    public String[] tableau1;
    public String[] tableau2;
    public String[] tab;
    public String[] tableau;
    int i, j, k, l;
    String name;
    String Description;
    String[][] tabf;
/*
    public void fetchData() {
        String url = "https://demo12.evouala.com/api/resource/list";
        StringRequest rq = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray( response );
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject( i );
                        name = jsonObject.getString( "nom" );
                        Description = jsonObject.getString( "description" );
                        String aa = jsonObject.getString( "resourcetype" );
                        JSONObject object = jsonObject.getJSONObject( "data" );


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }*/

        /*   @Override
           public void onResponse(JSONObject response) {
               try {
                   name = response.getString( "nom" );
                   Description = response.getString( "description" );
                   Log.i( "voici le nom",name);
               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
         });

        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( rq );
    }

*/
    @Override
    public void onClick(View v) {
        if(v==b){

            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }
        if(v==loadBtn){
          //  fetchData();
            Load();
        }
    }


    private class MyTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL( "https://demo12.evouala.com/api/resource/list" );
                URLConnection uc = url.openConnection();

                uc.setDoInput( true );
                //L'envoie de cookie
                //uc.setRequestProperty("Cookie",config.cookies);
                uc.addRequestProperty( "Cookie", config.cookies );
                //String j = (String) uc.getContent();
//récuperer la réponse du json avec BufferedReader
                BufferedReader in = new BufferedReader( new InputStreamReader( uc.getInputStream() ) );
                String inputLine;
                a = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                    a.append( inputLine );
                in.close();
                Log.i( "+++++++++", a.toString() );
                //System.out.println( "longueur de resultat" + a.length() );


                Pattern pattern3 = Pattern.compile( "[\":\"][\":]" );
                String aa = a.toString().replaceAll( String.valueOf( pattern3 ), "*" );
                System.out.println( "voici le chanement" + aa );
                Pattern p = Pattern.compile( "\\}," );
                String texte = aa;

                tableau = p.split( texte );
                System.out.println( "****************   " + tableau.length + "   *************" );


                for (int i = 0; i < tableau.length; i++) {
                    //System.out.println( "mini appliction" + tableau[i] );
                    slst1 = new ArrayList<String>();
                    Pattern p1 = Pattern.compile( "[\",\"][\",]" );
                    String Ti = tableau[i].toString();
                    tableau1 = p1.split( Ti );
                    System.out.println( "----------------   " + i + " " + tableau1.length + "   -----------" );
                    for (int j = 0; j < tableau1.length; j++) {
                        System.out.println( "--  " + i + " : " + j + " "+  tableau1[j]+ "   --" );
                        if (tableau1[j] != null ) slst1.add( tableau1[j] );
                    }
                    System.out.println( "nombre element du tab +++++++ " +" i "+ slst1.size() );
                    //System.out.println( "element de liste"+slst1.get( 0 ) );
                    lst1.add( i, slst1 );

                    //slst1.clear();
                    System.out.println( "nombre element du tab lst1  " +" i "+ lst1.get(i).size() );
                }

                System.out.println( "+*+*+*+++****+*+*++*+*+**+*++*+*+**+*+*++*+**+ "+lst1.size());
                Pattern p2 = Pattern.compile( "\\*" );
                lstApp = new ArrayList<App>();
                for (int i = 0;i<lst1.size();i++) {
                    lstApp.set( i, new App() );
                    for (int j = 0; j < lst1.get( i ).size(); j++) {
                        System.out.println( "element de liste : " + i + "-" + j + " : " + lst1.get( i ).get( j ) );
                        String test = "";
                        switch (lst1.get( i ).get( j )) {
                       /*     case lst1.get(i).get( j ).contains( "date_modified" ):

                              //  app.setDate_created();


                            case test == "date_modified":

                              //  app.setDate_modified();


                            case test == "description":

                              //  app.setDescription();


                            case test == "id":

                                //


                            case test == "nom":

                                //app.setName();
                        }*/


                        }}


                }
                return a.toString();
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText( getApplication(), result, Toast.LENGTH_LONG ).show();


        }

    }



    @Override
            protected void onCreate (Bundle savedInstanceState){
                super.onCreate( savedInstanceState );
                setContentView( R.layout.main );
                mydb = new DBhelper( this );
                b = (Button) findViewById( R.id.afficheList );
                b.setOnClickListener( this );
                loadBtn = (Button) this.findViewById( R.id.btn_load );
                rq = Volley.newRequestQueue( this );

                loadBtn.setOnClickListener( this );
                //mydb = new DBhelper(this);


            }

            public void Load () {

                //Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
                MyTask taskLoad = new MyTask();
                taskLoad.execute( "https://demo12.evouala.com/api/resource/list/JSON.php?day=Monday" );


            }

        }