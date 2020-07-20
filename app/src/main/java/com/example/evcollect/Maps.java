package com.example.evcollect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.evcollect.ui.maps.MapsFragment;

public class Maps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.maps_fragment );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, MapsFragment.newInstance() )
                    .commitNow();
        }
    }
}
