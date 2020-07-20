package com.example.evcollect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Welcome extends Activity {
   public static int Splash_time_out=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView( R.layout.welcome);
        new Handler(  ).postDelayed( new Runnable() {
            @Override
            public void run() {
                    Intent i=new Intent( Welcome.this, Login.class );
                    startActivity( i );
                    finish();
            }

            },Splash_time_out);

    }
}
