package project.jsht.mx.org.bamx.jshtablet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import project.jsht.mx.org.bamx.jshtablet.Enumeraciones.MenuOpciones;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

import static project.jsht.mx.org.bamx.jshtablet.R.layout.activity_main;
import static project.jsht.mx.org.bamx.jshtablet.R.layout.bar;

/**
 * Created by PC on 09/05/2018.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    private GoogleMap mMap;

    View ly_back,ly_contact;
    tabsFragment tabsFragment;
    AcopioFragment acopioFragment;
    TextView tvTitulo;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Utils.opcionActual =  MenuOpciones.Contactos;

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        View view = getLayoutInflater().inflate(bar, null);
        tvTitulo = view.findViewById(R.id.tvTitulo);

        Toolbar.LayoutParams layout = new Toolbar.LayoutParams(Toolbar.LayoutParams.FILL_PARENT, Toolbar.LayoutParams.FILL_PARENT);

        getSupportActionBar().setCustomView(view, layout);


        View mFab = (View) findViewById(R.id.lyMain);
        ViewCompat.setElevation(mFab, 12);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switch (Utils.opcionActual)
        {
            case Mapa:
                navigationView.setCheckedItem(R.id.nav_mapa);
                setMaps();
                break;
            case Contactos:
                navigationView.setCheckedItem(R.id.nav_contactos);
                setContacts();
                break;
            case Acopio:
                navigationView.setCheckedItem(R.id.nav_acopio);
                setAcopio();
                break;
        }

        //navigationView.setCheckedItem(R.id.nav_contactos);
        //setContacts();
        View headerview = navigationView.getHeaderView(0);

        ly_back = (View) headerview.findViewById(R.id.navHeader);

        btnBack = (ImageView) headerview.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_salir) {
            new SalirDialog().show(getSupportFragmentManager(), "SimpleDialog");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //reload();

        for (Fragment fragment:getSupportFragmentManager().getFragments()) {
             if (fragment!=null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();

            }
        }

        if (id == R.id.nav_contactos) {
            setContacts();

        } else if (id == R.id.nav_mapa) {

            setMaps();

        } else if (id == R.id.nav_acopio) {

            setAcopio();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setContacts()
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        tabsFragment = new tabsFragment();

        Utils.opcionActual= MenuOpciones.Contactos;

        Utils.fragmentTransaction = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.add(R.id.lyMain, tabsFragment);
        ft1.commit();

        tvTitulo.setText("Contactos");
    }

    public void setMaps()
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        tabsFragment = new tabsFragment();

        Utils.opcionActual= MenuOpciones.Mapa;
        Utils.fragmentTransaction = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.add(R.id.lyMain, tabsFragment);
        ft1.commit();

        tvTitulo.setText("Mapa");
    }

    public void setAcopio()
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        acopioFragment= new AcopioFragment();
        tvTitulo.setText("Acopio");
        Utils.opcionActual= MenuOpciones.Acopio;
        Utils.fragmentTransaction = null;
        android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.add(R.id.ly_main_root, acopioFragment);
        ft1.commit();

    }

    void reload()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
