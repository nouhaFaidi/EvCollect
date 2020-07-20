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
import android.widget.TextView;
import android.widget.Toast;

public class DisplayApp extends Activity {
    int from_Where_I_Am_Coming = 0;
    private DBhelper mydb ;

    TextView name;
    TextView DateCreated;
    TextView DateModified;
    TextView Description;
    TextView ResourceType;
    TextView DescribeUrl,id;

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
        setContentView( R.layout.displayapp);

        name = (EditText) findViewById( R.id.editTextName);
        id=(EditText) findViewById( R.id.editTextId );
       DateCreated = (EditText) findViewById( R.id.editTextDateCreated);
        DateModified = (EditText) findViewById( R.id.editTextDateModified);
       Description = (EditText) findViewById( R.id.editTextDescription);
        ResourceType = (EditText) findViewById( R.id.editTextResourceType);
       DescribeUrl = (EditText) findViewById( R.id.editTextDescribeurl);
        mydb = new DBhelper(this);
           Bundle extras = getIntent().getExtras();

        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
//means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();



                String dt =
                        rs.getString(rs.getColumnIndex( DBhelper.APPS_COLUMN_DateCreated));
                String dm =
                        rs.getString(rs.getColumnIndex( DBhelper.APPS_COLUMN_DateModified));
                String nam =
                        rs.getString(rs.getColumnIndex( DBhelper.APPS_COLUMN_Name));
                String desc =
                        rs.getString(rs.getColumnIndex( DBhelper.APPS_COLUMN_Description));
                String Rt =
                        rs.getString(rs.getColumnIndex( DBhelper.APPS_COLUMN_ResourceType));
                String Du =
                        rs.getString(rs.getColumnIndex( DBhelper.APPS_COLUMN_DescribeUrl));
                if (!rs.isClosed())
                {
                    rs.close();
                }
                Button b = (Button)findViewById( R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setText((CharSequence)nam);
                name.setFocusable(false);
                name.setClickable(false);
                DateCreated.setText((CharSequence)dt);
               DateCreated.setFocusable(false);
               DateCreated.setClickable(false);
                DateModified.setText((CharSequence)dm);
               DateModified.setFocusable(false);
                DateModified.setClickable(false);
                Description.setText((CharSequence)desc);
                Description.setFocusable(false);
                Description.setClickable(false);
                ResourceType.setText((CharSequence)Rt);
                ResourceType.setFocusable(false);
                ResourceType.setClickable(false);
                DescribeUrl.setText((CharSequence)Du);
                DescribeUrl.setFocusable(false);
                DescribeUrl.setClickable(false);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case 100:
                Button b = (Button)findViewById( R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);
                DateCreated.setEnabled(true);
                DateCreated.setFocusableInTouchMode(true);
                DateCreated.setClickable(true);
                DateModified.setEnabled(true);
                DateModified.setFocusableInTouchMode(true);
                DateModified.setClickable(true);
                Description.setEnabled(true);
                Description.setFocusableInTouchMode(true);
                Description.setClickable(true);
                ResourceType.setEnabled(true);
                ResourceType.setFocusableInTouchMode(true);
                ResourceType.setClickable(true);
                DescribeUrl.setEnabled(true);
                DescribeUrl.setFocusableInTouchMode(true);
                DescribeUrl.setClickable(true);
                return true;



            case 200:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage( R.string.deleteApp)
                        .setPositiveButton( R.string.yes, new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mydb.deleteApp(id_To_Update);
                                        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new
                                                        Intent(getApplicationContext(),com.example.evcollect.AppDB.class);
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
    public void run(View view)
    {
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateApp(id_To_Update,
                        DateCreated.getText().toString(), DateModified.getText().toString(),name.getText().toString(),
                        Description.getText().toString(), id.getText().toString(),ResourceType.getText().toString(),DescribeUrl.getText().toString())){



                    Toast.makeText(getApplicationContext(), "Updated",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new
                            Intent(getApplicationContext(),com.example.evcollect.AppDB.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "not Updated",
                            Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if (mydb.insertApp(DateCreated.getText().toString(), DateModified.getText().toString(),name.getText().toString(),Description.getText().toString(), id.getText().toString(),ResourceType.getText().toString(), DescribeUrl.getText().toString() )) {
                            Toast.makeText(getApplicationContext(), " done",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new
                        Intent(getApplicationContext(),com.example.evcollect.AppDB.class);
                startActivity(intent);
            }
        }
    }
}

