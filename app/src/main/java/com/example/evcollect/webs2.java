package com.example.evcollect;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class webs2 extends AsyncTask {
    HttpURLConnection conn1;
    String headerField1;
    StringBuilder result;
    StringBuffer data = new StringBuffer();

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("https://demo12.evouala.com/api/resource/describe?id=100/JSON.php?day=Tuesday");
            conn1 = (HttpsURLConnection) url.openConnection();
            conn1.setRequestMethod("GET");
            conn1.setRequestProperty("Cookie", config.cookies);

            conn1.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn1.setRequestProperty("Accept", "application/json");
            conn1.setDoOutput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
            String text;
             result = new StringBuilder();
            while ((text = in.readLine()) != null)
                result.append(text);

            in.close();

            //storeCookies(conn1);

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//Log.i("+++",result.toString());

        //return headerField1;
        return result.toString();
    }

    protected void onPostExecute(String results){
        if (results!=null){    //Add some actions here if successful

            Log.i("RESULT", results);
        }
    }
}
