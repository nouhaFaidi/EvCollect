
package com.example.evcollect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Form extends Activity {
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int from_Where_I_Am_Coming = 0;
    private DBhelper mydb ;

    TextView NoPermis;
    TextView Demandeur;
    TextView Nombre;
    TextView Taille;
    TextView Date;
RadioButton confirm;
RadioButton annuler;
    int id_To_Update = 0;


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,100,1,"menu1");
        menu.add(1,200,2,"menu2");
        menu.add(2,300,3,"menu3");
        return super.onCreateOptionsMenu(menu);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.form);
mydb=new DBhelper( this );
        NoPermis= (EditText) findViewById( R.id.permis);
        Demandeur = (EditText) findViewById( R.id.damandeur);
        Nombre = (EditText) findViewById( R.id.nombre);
        Taille = (EditText) findViewById( R.id.taille);
        Date = (EditText) findViewById( R.id.date);
confirm=(RadioButton)findViewById( R.id.confirmer);
annuler=findViewById( R.id.annuler );
//confirm.setOnClickListener( (View.OnClickListener) this );
        myRef = FirebaseDatabase.getInstance().getReference( "Formulaire" ) ;
firebaseAuth=FirebaseAuth.getInstance();

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
//means this is the view part not the add contact part.
                Cursor rs = mydb.getDataForm(Value);
                id_To_Update = Value;
                rs.moveToFirst();



                String np =
                        rs.getString(rs.getColumnIndex( DBhelper.Forms_COLUMN_Permis));
                String dem =
                        rs.getString(rs.getColumnIndex( DBhelper.Forms_COLUMN_Demandeur));
                String nb =
                        rs.getString(rs.getColumnIndex( DBhelper.Forms_COLUMN_Nombre));
                String tai =
                        rs.getString(rs.getColumnIndex( DBhelper.Forms_COLUMN_Taille));
                String dat =
                        rs.getString(rs.getColumnIndex( DBhelper.Forms_COLUMN_Date));

                if (!rs.isClosed())
                {
                    rs.close();
                }
                Button b = (Button)findViewById( R.id.enregistrer);
                b.setVisibility( View.VISIBLE);
                NoPermis.setText((CharSequence)np);
                NoPermis.setFocusable(false);
                NoPermis.setClickable(false);
               Demandeur.setText((CharSequence)dem);
                Demandeur.setFocusable(false);
                Demandeur.setClickable(false);
                Nombre.setText((CharSequence)nb);
                Nombre.setFocusable(false);
                Nombre.setClickable(false);
                Taille.setText((CharSequence)tai);
                Taille.setFocusable(false);
                Taille.setClickable(false);
                Date.setText((CharSequence)dat);
                Date.setFocusable(false);
                Date.setClickable(false);

            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case 100:
                Button b = (Button)findViewById( R.id.enregistrer);
                b.setVisibility(View.VISIBLE);
                NoPermis.setEnabled(true);
                NoPermis.setFocusableInTouchMode(true);
                NoPermis.setClickable(true);
                Demandeur.setEnabled(true);
                Demandeur.setFocusableInTouchMode(true);
                Demandeur.setClickable(true);
                Nombre.setEnabled(true);
                Nombre.setFocusableInTouchMode(true);
                Nombre.setClickable(true);
                Taille.setEnabled(true);
                Taille.setFocusableInTouchMode(true);
                Taille.setClickable(true);
                Date.setEnabled(true);
                Date.setFocusableInTouchMode(true);
                Date.setClickable(true);

                return true;



            case 200:
                /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteForm)
                        .setPositiveButton(R.string.yes, new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mydb.deleteForm(id_To_Update);
                                        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new
                                                Intent(getApplicationContext(),com.example.evcollect.Formdb.class);
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton(R.string.no, new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
// User cancelled the dialog
                                    }
                                });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);*/
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage( R.string.deleteForm)
                        .setPositiveButton( R.string.yes, new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mydb.deleteForm( id_To_Update );
                                        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new
                                                Intent(getApplicationContext(),com.example.evcollect.Formdb.class);
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton( R.string.no, new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
// User cancelled the dialog
                                    }
                                });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt( "id" );
            if (Value > 0) {
                if (mydb.updateForm( id_To_Update, NoPermis.getText().toString(), Demandeur.getText().toString(), Nombre.getText().toString(), Taille.getText().toString(), Date.getText().toString() )) {


                    Toast.makeText( getApplicationContext(), "Updated",
                            Toast.LENGTH_SHORT ).show();
                    Intent intent = new
                            Intent( getApplicationContext(), com.example.evcollect.Formdb.class );
                    startActivity( intent );
                } else {
                    Toast.makeText( getApplicationContext(), "not Updated",
                            Toast.LENGTH_SHORT ).show();
                }
            } else {
                if (mydb.insertForm( NoPermis.getText().toString(), Demandeur.getText().toString(), Nombre.getText().toString(), Taille.getText().toString(), Date.getText().toString() )) {


                    Toast.makeText( getApplicationContext(), " done",
                            Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT ).show();
                }
                Intent intent = new
                        Intent( getApplicationContext(), com.example.evcollect.Formdb.class );
                startActivity( intent );
            }
        }
        if (confirm.isChecked()) {
            Formulaire form = new Formulaire( NoPermis.getText().toString(), Demandeur.getText().toString(), Nombre.getText().toString(), Taille.getText().toString(), Date.getText().toString() );
            database.getInstance().getReference( "Formulaire" ).child(NoPermis.getText().toString()).setValue( form );
        }

        if (annuler.isChecked()) {
            DialogInterface i = new DialogInterface() {
                @Override
                public void cancel() {

                }

                @Override
                public void dismiss() {

                }


            };
        }

    }}