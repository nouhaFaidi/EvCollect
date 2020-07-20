package com.example.evcollect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class Auth extends Activity implements View.OnClickListener{
    public




















    static EditText e1,e2;
    public static Button b1,b2;
    private FirebaseAuth mAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.athentification);
        e1=(EditText)findViewById( R.id.edtEmail );
        e2=(EditText)findViewById( R.id.edtPassword );
        b1=(Button)findViewById( R.id.btnSignIn );
        b2=(Button)findViewById( R.id.btnSignUp );
        b1.setOnClickListener( this );
b2.setOnClickListener( this );
        mAuth = FirebaseAuth.getInstance();
        }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){

        //updateUI(currentUser);
            }
    }
    public void SignUp(){
        mAuth.createUserWithEmailAndPassword( e1.getText().toString(),e2.getText().toString() ).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText( Auth.this, "Compte est crée avec succès.",
                            Toast.LENGTH_SHORT ).show();
                    Intent i=new Intent( Auth.this, Formdb.class );
                    startActivity( i );
                    // Sign in success, update UI with the signed-in user's information
                    Log.d( TAG, "createUserWithEmail:success" );
                    FirebaseUser user = mAuth.getCurrentUser();

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w( TAG, "createUserWithEmail:failure", task.getException() );
                    Toast.makeText( Auth.this, "Compte n'est pas crée veuillez ressayer.",
                            Toast.LENGTH_SHORT ).show();

                }


            }
        } )  ;
    }
    public void SignIn(){
        mAuth.signInWithEmailAndPassword( e1.getText().toString(),e2.getText().toString() ).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText( Auth.this, "vous etes connecté.",
                            Toast.LENGTH_SHORT ).show();
                    Intent i=new Intent( Auth.this, Formdb.class );
                    startActivity( i );
                }
                else {
                    Toast.makeText( Auth.this, "verifier vos données.",
                            Toast.LENGTH_SHORT ).show();
                }

            }
        } );
    }
    @Override
    public void onClick(View v) {
if(v==b1){
    SignIn();
}
        if(v==b2){
        SignUp();
        }
    }


}
