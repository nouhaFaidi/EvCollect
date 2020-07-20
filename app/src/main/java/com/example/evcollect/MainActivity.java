package com.example.evcollect;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        final FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.nav_view );
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_collecteur, R.id.nav_slideshow,R.id.nav_Maps,R.id.nav_Langue,R.id.nav_Sombre )
                .setDrawerLayout( drawer )
                .build();
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        NavigationUI.setupActionBarWithNavController( this, navController, mAppBarConfiguration );
        NavigationUI.setupWithNavController( navigationView, navController );

        navController.addOnDestinationChangedListener( new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
               int menuid=destination.getId();
                switch (menuid){
                    case R.id.nav_Langue:
                        Toast.makeText( MainActivity.this,"changer la langue" ,Toast.LENGTH_SHORT).show();
                        startActivity(  new Intent( MainActivity.this, Main2Activity.class ));
                        fab.hide();
                        break;

                    case R.id.nav_slideshow:

                        startActivity(  new Intent( MainActivity.this, Enregistrement.class ));
                        fab.hide();
                        break;
                    case R.id.nav_collecteur:
                        Toast.makeText( MainActivity.this,"changer la langue" ,Toast.LENGTH_SHORT).show();
                        startActivity(  new Intent( MainActivity.this, MiniApplication.class ));
                        fab.hide();
                        break;
                    case R.id.nav_Maps:
                        Toast.makeText( MainActivity.this,"changer la langue" ,Toast.LENGTH_SHORT).show();
                        startActivity(  new Intent( MainActivity.this, Main3Activity.class ));
                        fab.hide();
                        break;
                    default:
                        fab.show();
                        break;

                }

            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );

        return true;



    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Gérer la sélection des éléments
        switch (item.getItemId()) {
            case R.id.connect:
                Intent i=new Intent( MainActivity.this,Auth.class );
                startActivity( i );
                return true;
            case R.id.disconnect:
                Intent in=new Intent( MainActivity.this,Form.class );
                startActivity( in );
                return true;


        }
        return super.onOptionsItemSelected( item );
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        return NavigationUI.navigateUp( navController, mAppBarConfiguration )
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
if(id == R.id.nav_Langue){
    Intent i=new Intent( this,AppDB.class );
    startActivity( i );
}
       /* if (id == R.id.nav_gallery){
            Intent i=new Intent( this,AppDB.class );
            startActivity( i );
        }
    else if (id == R.id.nav_slideshow) {
            Intent in=new Intent( this,Formdb.class );
            startActivity( in );
    }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer( GravityCompat.START);
        return true;
    }
}
