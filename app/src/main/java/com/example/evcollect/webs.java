package com.example.evcollect;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import org.json.JSONObject;

import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class webs extends AsyncTask<String, Void, String> {

    HttpURLConnection conn;
    String headerField1;
    URL url;
    CookieManager cm;
    JSONObject jsonParam = Login.jsonParam;
    WebView webView;

    public webs(URL url, JSONObject jsonParam){

        super();
        this.url = url;
        this.jsonParam=jsonParam;


    }









    @SuppressLint("WrongThread")
    @Override
    protected String doInBackground(String... strings) {

    try{
           url = new URL("https://demo12.evouala.com/auth/login");
        //conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        //conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");


            config.cookies=conn.getHeaderField("set-cookie");



        } catch (Exception e) {
            e.printStackTrace();
        }
        return config.cookies;

    }

    protected void onPostExecute(String results){
        if (results!=null){    //Add some actions here if successful
           Log.i("RESULT", results);
        }
            }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
