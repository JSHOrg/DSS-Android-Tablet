package project.jsht.mx.org.bamx.jshtablet.Utils;


import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.MenuItemCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import project.jsht.mx.org.bamx.jshtablet.ContactsAdapter.ContactItem;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSE;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEAlimentacion;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSECE;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEDG;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEEF;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEIV;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSERepresentante;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSESSS;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEServicios;
import project.jsht.mx.org.bamx.jshtablet.Enumeraciones.AcopioOpciones;
import project.jsht.mx.org.bamx.jshtablet.Enumeraciones.MenuOpciones;
import project.jsht.mx.org.bamx.jshtablet.NetWorking.Metodos;
import project.jsht.mx.org.bamx.jshtablet.NetWorking.ProgressDialogMessage;
import project.jsht.mx.org.bamx.jshtablet.NetWorking.RequestGet;
import project.jsht.mx.org.bamx.jshtablet.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by PC on 30/04/2018.
 */

public  class Utils  {
    public static String[] integrantes = {"","","","","","","","","","","","","","",""};
    public static MenuOpciones opcionActual = MenuOpciones.Inicio;
    public static AcopioOpciones opcionActualAcopio = null;
    public static String currentTab = "";
    public static double latitud,longitud;
    public static String tiulomap;
    public static int posMenu = 0;
    private Activity context;
    private ServiciosWeb.NombreServicioWeb nombreServicioWeb;
    public static ServiciosWeb.NombreServicioWeb nombreServicioWebActual;
    public Utils(Activity context) {
        this.context = context;
    }
    public static JSONObject jsonEncuesta = new JSONObject();
    public static JSONObject jsonEncuestaFinal = new JSONObject();



    public static String tituloEncuesta="Estudio Socio Economico  -  Representante 1/8";
    public static android.support.v4.app.FragmentManager fragmentTransaction = null;

    public static ArrayList<SharedPreferences> jsonEncuestas = new ArrayList<>();


    public static ArrayList<android.support.v4.app.Fragment> Fragments = new ArrayList<>();

    public Utils(Activity context, ServiciosWeb.NombreServicioWeb nombreServicioWeb) {
        this.context = context;
        this.nombreServicioWeb = nombreServicioWeb;
    }

    public static void setSharedPreference(Context context, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Encuesta",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("json", value);  // Saving string
        editor.apply();
        jsonEncuestas.add(sharedPreferences);
    }

    public static void sendSharedPreference(Context context) {

        if (jsonEncuestas.size() > 0)
        {
            for (int i = 0;i< jsonEncuestas.size();i++ )
            new ServiciosWeb((Activity) context, ServiciosWeb.NombreServicioWeb.setEncuesta).setEncuesta(jsonEncuestas.get(i).getString("json",null));

        }
        jsonEncuestas.clear();

    }



    public boolean submitForm(String tipo, TextInputEditText editText, TextInputLayout textInputLayout) {

        switch (tipo) {
            case "Email":
                return validateEmail(editText, textInputLayout);
            case "Contraseña":
                return validatePassword(editText, textInputLayout);
            case "Default":
                return validate(editText, textInputLayout);
        }
        return false;

    }

    private boolean validate(TextInputEditText editText, TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError("Verificar informacion");
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    public static Snackbar snackbar(View view, String string, int colorText, int colorBG) {
        Snackbar snackbar = Snackbar.make(view, string, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(colorText);
        snackbarView.setBackgroundColor(colorBG);

        return snackbar;
    }

    private boolean validateEmail(TextInputEditText editText, TextInputLayout textInputLayout) {
        String email = editText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            textInputLayout.setError("Verifique el correo");
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword(TextInputEditText editText, TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError("Verifique la contraseña");
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static android.support.v4.app.Fragment mostrarEncuesta(MenuItem item, Menu menu,Context context)
    {

        ProgressDialog progress;
        progress = new ProgressDialog(context);
        progress.setTitle("Cargando información");
        progress.show();

        android.support.v4.app.Fragment fragment = null;
        CompoundButton switchView = null;
        try {
            if ( item != null) {
                int id = item.getItemId();
                if (menu.size() !=1)
                    switchView = (CompoundButton) MenuItemCompat.getActionView(menu.findItem(menu.findItem(item.getItemId()).getItemId()));
                else
                    switchView = (CompoundButton) MenuItemCompat.getActionView(menu.findItem(menu.getItem(0).getItemId()));

                if (switchView.isChecked()) {

                    if (R.id.menu_se_representante == id ) {
                        if (Constants.fragmentEstudioSERepresentante == null)
                            Constants.fragmentEstudioSERepresentante = new FragmentEstudioSERepresentante();
                        posMenu = 0;
                        fragment = Constants.fragmentEstudioSERepresentante;
                        tituloEncuesta="Estudio Socio Economico  -  Representante 1/7";}
                    else if (R.id.menu_se_datos_generales == id ) {
                        if (Constants.fragmentEstudioSEDG == null)
                            Constants.fragmentEstudioSEDG = new FragmentEstudioSEDG();
                        posMenu = 1;
                        fragment = Constants.fragmentEstudioSEDG;
                        tituloEncuesta="Estudio Socio Economico  -  Datos generales 2/7";
                    } else if (R.id.menu_se_estructura_familiar == id ) {
                        if (Constants.fragmentEstudioSEEF == null)
                            Constants.fragmentEstudioSEEF = new FragmentEstudioSEEF();
                        posMenu = 2;
                        fragment = Constants.fragmentEstudioSEEF;
                        tituloEncuesta="Estudio Socio Economico  -  Estructura familiar 3/7";
                    } else if (R.id.menu_se_seguridad_social_y_salud == id ) {
                        if (Constants.fragmentEstudioSESSS == null)
                            Constants.fragmentEstudioSESSS = new FragmentEstudioSESSS();
                        posMenu = 3;
                        fragment = Constants.fragmentEstudioSESSS;
                        tituloEncuesta="Estudio Socio Economico  -  Seguridad social y salud 4/7";
                    } else if (R.id.menu_se_servicios == id ) {
                        if (Constants.fragmentEstudioSEServicios == null)
                            Constants.fragmentEstudioSEServicios = new FragmentEstudioSEServicios();
                        posMenu = 4;
                        fragment = Constants.fragmentEstudioSEServicios;
                        tituloEncuesta="Estudio Socio Economico  -  Servicios 5/7";
                    } else if (R.id.menu_se_infraestructura_de_vivienda == id) {
                        if (Constants.fragmentEstudioSEIV == null)
                            Constants.fragmentEstudioSEIV = new FragmentEstudioSEIV();
                        posMenu = 5;
                        fragment = Constants.fragmentEstudioSEIV;
                        tituloEncuesta="Estudio Socio Economico  -  Infraestructura de vivienda 6/7";
                    } else if (R.id.menu_se_condiciones_econ_micas == id ) {
                        if (Constants.fragmentEstudioSECE== null)
                            Constants.fragmentEstudioSECE = new FragmentEstudioSECE();
                        posMenu = 6;
                        fragment = Constants.fragmentEstudioSECE;
                        tituloEncuesta="Estudio Socio Economico  -  Condiciones económicas 7/7";
                    } else if (R.id.menu_sn_alimentacion == id) {
                        if (Constants.fragmentEstudioSEAlimentacion == null)
                            Constants.fragmentEstudioSEAlimentacion = new FragmentEstudioSEAlimentacion();
                        posMenu = 7;
                        fragment = Constants.fragmentEstudioSEAlimentacion;
                        tituloEncuesta="Estudio Socio nutricio  -  Alimentación 1/1";
                    } else if (R.id.menu_control == id ) {
                        if (Constants.fragmentEstudioSE == null)
                            Constants.fragmentEstudioSE= new FragmentEstudioSE();
                        posMenu = 8;
                        fragment = Constants.fragmentEstudioSE;
                        tituloEncuesta="Control";
                    }

                }

            }

        }catch ( Exception ex)
        {}
        if (Fragments.contains(fragment))
        {
            Fragments.remove(fragment);
            Fragments.add(fragment);
        }
        else
            Fragments.add(fragment);

        progress.dismiss();
        return fragment;

    }

    public void mostrarCatalogo(Spinner spinner, String servicio)
    {

        ProgressDialog progress;
        progress = new ProgressDialog(context);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setTitle("Cargando información");
        progress.show();

        String response = null;
        JSONArray jsonArray = null;
        try {
            response = new RequestGet().requestGet(Metodos.api + "/" + Metodos.catalogos + servicio);
            Log.i("CATALOGO", "mostrarCatalogo: " + response);
            jsonArray = new JSONArray(response);

            ArrayList<String> campos = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).has("C_Valor")) {
                    campos.add(jsonArray.getJSONObject(i).getString("C_Valor"));
                }
            }

            ArrayAdapter<String> a = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, campos);
            a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(a);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            progress.dismiss();
        }
    }

    public static android.support.v4.app.Fragment mostrarEncuesta( int pos,Activity context)
    {

        ProgressDialog progress;
        progress = new ProgressDialog(context);
        progress.setTitle("Cargando información");
        progress.show();
        android.support.v4.app.Fragment fragment = null;
            if (pos == 0) {
                if (Constants.fragmentEstudioSERepresentante == null)
                    Constants.fragmentEstudioSERepresentante = new FragmentEstudioSERepresentante();
                fragment = Constants.fragmentEstudioSERepresentante;
                tituloEncuesta="Estudio Socio Economico  -  Representante 1/7";
            } else if (pos == 1) {
                if (Constants.fragmentEstudioSEDG == null)
                    Constants.fragmentEstudioSEDG = new FragmentEstudioSEDG();
                fragment = Constants.fragmentEstudioSEDG;
                tituloEncuesta="Estudio Socio Economico  -  Datos generales 2/7";
            } else if (pos == 2) {
                if (Constants.fragmentEstudioSEEF == null)
                    Constants.fragmentEstudioSEEF = new FragmentEstudioSEEF();
                fragment = Constants.fragmentEstudioSEEF;
                tituloEncuesta="Estudio Socio Economico  -  Estructura familiar 3/7";
            } else if (pos == 3) {
                if (Constants.fragmentEstudioSESSS == null)
                    Constants.fragmentEstudioSESSS = new FragmentEstudioSESSS();
                fragment = Constants.fragmentEstudioSESSS;
                tituloEncuesta="Estudio Socio Economico  -  Seguridad social y salud 4/7";
            } else if (pos == 4) {
                if (Constants.fragmentEstudioSEServicios == null)
                    Constants.fragmentEstudioSEServicios = new FragmentEstudioSEServicios();
                fragment = Constants.fragmentEstudioSEServicios;
                tituloEncuesta="Estudio Socio Economico  -  Servicios 5/7";
            } else if (pos == 5) {
                if (Constants.fragmentEstudioSEIV == null)
                    Constants.fragmentEstudioSEIV = new FragmentEstudioSEIV();
                fragment = Constants.fragmentEstudioSEIV;
                tituloEncuesta="Estudio Socio Economico  -  Infraestructura de vivienda 6/7";
            } else if (pos == 6) {
                if (Constants.fragmentEstudioSECE == null)
                    Constants.fragmentEstudioSECE = new FragmentEstudioSECE();
                fragment = Constants.fragmentEstudioSECE;
                tituloEncuesta="Estudio Socio Economico  -  Condiciones económicas 7/7";
            } else if (pos == 7) {
                if (Constants.fragmentEstudioSEAlimentacion == null)
                    Constants.fragmentEstudioSEAlimentacion = new FragmentEstudioSEAlimentacion();
                fragment = Constants.fragmentEstudioSEAlimentacion;
                tituloEncuesta="Estudio Socio nutricio  -  Alimentación 1/1";
            } else if (pos == 8) {
                if (Constants.fragmentEstudioSE == null)
                    Constants.fragmentEstudioSE = new FragmentEstudioSE();
                fragment = Constants.fragmentEstudioSE;
                tituloEncuesta="Control";
            }
            posMenu = pos;

        if (Fragments.contains(fragment))
        {
            Fragments.remove(fragment);
            Fragments.add(fragment);
        }
        else
            Fragments.add(fragment);

        progress.dismiss();
        return fragment;

    }
    public static void setRadioExclusiveClick(ViewGroup parent) {
        final List<RadioButton> radios = getRadioButtons(parent);

        for (RadioButton radio: radios) {
            radio.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    RadioButton r = (RadioButton) v;
                    r.setChecked(true);
                    for (RadioButton r2:radios) {
                        if (r2.getId() != r.getId()) {
                            r2.setChecked(false);
                        }
                    }

                }
            });
        }
    }

    private static List<RadioButton> getRadioButtons(ViewGroup parent) {
        List<RadioButton> radios = new ArrayList<RadioButton>();
        for (int i=0;i < parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            if (v instanceof RadioButton) {
                radios.add((RadioButton) v);
            } else if (v instanceof ViewGroup) {
                List<RadioButton> nestedRadios = getRadioButtons((ViewGroup) v);
                radios.addAll(nestedRadios);
            }
        }
        return radios;
    }


}