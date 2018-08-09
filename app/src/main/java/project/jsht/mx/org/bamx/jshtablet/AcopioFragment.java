package project.jsht.mx.org.bamx.jshtablet;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import project.jsht.mx.org.bamx.jshtablet.Utils.Dialog;
import project.jsht.mx.org.bamx.jshtablet.Utils.ServiciosWeb;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

import static android.content.ContentValues.TAG;

/**
 * Created by PC on 13/06/2018.
 */

public class AcopioFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener
{
    public static Boolean seguir = false;
    NavigationView navigationView , navigationViewSub;
    CrossFadeSlidingPaneLayout layoutSlide;
    LinearLayout MainsContainer;
    int id,subID;
    MenuInflater menuSE, menuSN,menuComtrol;
    Menu MenuSE, MenuSN,MenuComtrol;
    boolean[] menus = {false,false,false,false,false,false,false,false,false};
    int[] ID =  {1,2};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_acopio, container, false);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        final TextView tvTitulo = (TextView) view.findViewById(R.id.tv_titulo);
        tvTitulo.setText(Utils.tituloEncuesta);
        fab.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v ) {
                menus[Utils.posMenu] = true;
                if (navigationViewSub.getMenu().size() !=1){
                CompoundButton switchView = (CompoundButton) MenuItemCompat.getActionView(navigationViewSub.getMenu().findItem(navigationViewSub.getMenu().getItem(Utils.posMenu).getItemId()));
                switchView.setChecked(true);}
                else { CompoundButton switchView = (CompoundButton) MenuItemCompat.getActionView(navigationViewSub.getMenu().findItem(navigationViewSub.getMenu().getItem(0).getItemId()));
                    switchView.setChecked(true);}

                if (Utils.posMenu + 1 <= 8) {
                    navigationViewSub.setCheckedItem(navigationViewSub.getMenu().getItem(0).getItemId());
                    getChildFragmentManager().beginTransaction().replace(R.id.ly_encuestas, Utils.mostrarEncuesta(Utils.posMenu + 1)).commit();
                    if (Utils.posMenu == 7) agustarMenuNutricio();
                    else if (Utils.posMenu == 8) agustarMenuControl();

                    if (Utils.posMenu  < 7){
                        navigationViewSub.setCheckedItem(navigationViewSub.getMenu().getItem(Utils.posMenu).getItemId());
                    }
                    tvTitulo.setText(Utils.tituloEncuesta);
                }
                else {
                    createSimpleDialog().show();
                }
            }
        });

        layoutSlide = (CrossFadeSlidingPaneLayout) view.findViewById(R.id.sliding_pane_layout);
        layoutSlide.setSliderFadeColor(Color.TRANSPARENT);

        navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.menu_acopio);

        navigationViewSub = (NavigationView) view.findViewById(R.id.nav_view_sub);

        MainsContainer = (LinearLayout) view.findViewById(R.id.mains_container);


        agustarMenuEconomico();
        navigationViewSub.setCheckedItem(navigationViewSub.getMenu().getItem(0).getItemId());
        navigationView.setCheckedItem(navigationView.getMenu().getItem(0).getItemId());
        id = navigationView.getMenu().getItem(0).getItemId();


        getChildFragmentManager().beginTransaction().replace(R.id.ly_encuestas, Utils.mostrarEncuesta(0)).commit();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (id == item.getItemId())
                    return false;
                else{
                id = item.getItemId();
                if (id == R.id.nav_economico)
                {

                    agustarMenuEconomico();
                }
                else if (id == R.id.nav_nutricio)
                {
                    agustarMenuNutricio();

                }
                else if (id == R.id.nav_control)
                {
                    agustarMenuControl();

                }
                AjustarTamaños();}
                return true;
            }
        });

         navigationViewSub.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                subID = item.getItemId();
                try{


                    getChildFragmentManager().beginTransaction().replace(R.id.ly_encuestas, Utils.mostrarEncuesta(item,navigationViewSub.getMenu())).commit();
                    tvTitulo.setText(Utils.tituloEncuesta);
                    layoutSlide.closePane();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getActivity(), "Solo Puedes cambiar a una encuesta terminada", Toast.LENGTH_LONG).show();

                }

                return true;
            }
        });

        layoutSlide.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
        AjustarTamaños();


        return view;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager)  getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public void agustarMenuEconomico()
    {
        navigationViewSub.getMenu().clear();
        navigationViewSub.inflateMenu(R.menu.menu_socio_economico);
        AjustarTamaños();
        navigationView.setCheckedItem(navigationView.getMenu().getItem(0).getItemId());
        if (Utils.posMenu <= 6)
            navigationView.setCheckedItem(navigationView.getMenu().getItem(Utils.posMenu).getItemId());
        navigationView.setTag(1);

        setChecks();

    }
    public void agustarMenuNutricio()
    {
        navigationViewSub.getMenu().clear();
        navigationViewSub.inflateMenu(R.menu.menu_socio_nutricio);
        AjustarTamaños();
        navigationViewSub.setCheckedItem(navigationViewSub.getMenu().getItem(0).getItemId());
        navigationView.setCheckedItem(navigationView.getMenu().getItem(1).getItemId());
        navigationView.setTag("2");
        setChecks();

    }
    public void agustarMenuControl()
    {
        navigationViewSub.getMenu().clear();
        navigationViewSub.inflateMenu(R.menu.menu_control);
        AjustarTamaños();
        navigationViewSub.setCheckedItem(navigationViewSub.getMenu().getItem(0).getItemId());
        navigationView.setCheckedItem(navigationView.getMenu().getItem(2).getItemId());
        navigationView.setTag("3");
        setChecks();

    }


    private void AjustarTamaños()
    {
        if (navigationViewSub.getMenu().size() == 0)
        {
            navigationViewSub.setVisibility(View.GONE);
        }
        else{
            navigationViewSub.setVisibility(View.VISIBLE);
            //MainsContainer.setLayoutParams(new SlidingPaneLayout.LayoutParams(503,ViewGroup.LayoutParams.MATCH_PARENT));

        }
        MainsContainer.setLayoutParams(new SlidingPaneLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));

    }

    private void setChecks()
    {

            if (navigationViewSub.getMenu().size() !=1){
                for(int i =0; i < menus.length -2 ;i++) {
                    CompoundButton switchView = (CompoundButton) MenuItemCompat.getActionView(navigationViewSub.getMenu().findItem(navigationViewSub.getMenu().getItem(i).getItemId()));
                    switchView.setChecked(menus[i]);
                }
            }
            else
            {
                if (navigationView.getTag().toString() == "2"){
                    CompoundButton switchView = (CompoundButton) MenuItemCompat.getActionView(navigationViewSub.getMenu().findItem(navigationViewSub.getMenu().getItem(0).getItemId()));
                    switchView.setChecked(menus[7]);
                }
                else if ( navigationView.getTag().toString() == "3"){
                    CompoundButton switchView = (CompoundButton) MenuItemCompat.getActionView(navigationViewSub.getMenu().findItem(navigationViewSub.getMenu().getItem(0).getItemId()));
                    switchView.setChecked(menus[8]);
                }
            }


    }

    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss:ssss");
        String strDate =  mdformat.format(calendar.getTime());
        return strDate.substring(6).replace(":","");
    }

    public void enviarEncuestas()
    {

        Toast.makeText(getContext(), " Encuesta Terminada", Toast.LENGTH_SHORT);
        for (Fragment fragment : Utils.Fragments) {
            if (fragment != null) {

                try {
                    fragment.getClass().getDeclaredMethod("guardar").invoke(fragment);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                getChildFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }

        try {
            Utils.jsonEncuestaFinal.put("beneficiarioId", Integer.valueOf(getCurrentTime()));
            Utils.jsonEncuestaFinal.put("_embedded", Utils.jsonEncuesta);

        } catch (JSONException ex) {
        }
        if (isOnline()) {
            new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.setEncuesta).setEncuesta(Utils.jsonEncuestaFinal.toString());
        } else
            Utils.setSharedPreference(getContext(), Utils.jsonEncuestaFinal.toString());
        //Utils.jsonEncuesta = new JSONObject();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Encuesta Terminada")
                .setMessage("Estas a punto de enviar las encuestas,no se podra modificar la informacion.\n ¿Deseas continuar?")
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                enviarEncuestas();
                                return;
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AcopioFragment.seguir = false;
                                return;
                            }
                        });
        return builder.create();
    }
}