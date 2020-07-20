package com.example.evcollect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.evcollect.ui.modesombre.ModeSombreFragment;

public class ModeSombre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.mode_sombre_fragment );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, ModeSombreFragment.newInstance() )
                    .commitNow();
        }
    }
}
