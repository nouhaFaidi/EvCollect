package com.example.evcollect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.evcollect.ui.langue.LangueFragment;

public class langue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.langue_fragment );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, LangueFragment.newInstance() )
                    .commitNow();
        }
    }
}
