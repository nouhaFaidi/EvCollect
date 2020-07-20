package com.example.evcollect.ui.langue;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.evcollect.AppDB;
import com.example.evcollect.Form;
import com.example.evcollect.Getwebjson;
import com.example.evcollect.Main2Activity;
import com.example.evcollect.MainActivity;
import com.example.evcollect.R;

import java.text.Normalizer;

public class LangueFragment extends Fragment {

    private LangueViewModel mViewModel;

    public static LangueFragment newInstance() {
        return new LangueFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate( R.layout.langue_fragment, container, false );
        Button b1=v.findViewById( R.id .langueFr);
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(  new Intent( getContext(), Main2Activity.class ));
                Toast.makeText( getContext(),"changer la langue" ,Toast.LENGTH_SHORT).show();

            }
        } );
        Button b2=v.findViewById( R.id .langueAn);
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );
        Button b3=v.findViewById( R.id .langueAr);
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        mViewModel = ViewModelProviders.of( this ).get( LangueViewModel.class );
        // TODO: Use the ViewModel
    }

}
