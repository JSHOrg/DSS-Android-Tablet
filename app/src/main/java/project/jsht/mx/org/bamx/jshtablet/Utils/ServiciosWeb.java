package project.jsht.mx.org.bamx.jshtablet.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import project.jsht.mx.org.bamx.jshtablet.ContactsAdapter.ContactsAdapter;
import project.jsht.mx.org.bamx.jshtablet.ContactsAdapter.ContactsDatos;
import project.jsht.mx.org.bamx.jshtablet.MainActivity;
import project.jsht.mx.org.bamx.jshtablet.NetWorking.Metodos;
import project.jsht.mx.org.bamx.jshtablet.NetWorking.NetServicesJSONObject;
import project.jsht.mx.org.bamx.jshtablet.NetWorking.SetHeaderes;
import project.jsht.mx.org.bamx.jshtablet.NetWorking.onTaskCompleted;
import project.jsht.mx.org.bamx.jshtablet.R;

import static android.content.ContentValues.TAG;

/**
 * Created by PC on 20/06/2018.
 */

public class ServiciosWeb implements onTaskCompleted {


    JSONArray jsonArray = null;
    private Activity context;
    private NombreServicioWeb nombreServicioWeb;
    private RecyclerView recyclerView;
    private View view;
    private Spinner spinner;
    private RecyclerView.Adapter adapter;
    public enum NombreServicioWeb {
        Login,
        GetBancosAlimentos,
        GetCentrosComunitarios,
        setEncuesta,
        getCatalogo
    }

    public ServiciosWeb() {

    }


    //-----------------Basico-------------//
    public ServiciosWeb(Activity context, NombreServicioWeb nombreServicioWeb) {

        this.context = context;
        this.nombreServicioWeb = nombreServicioWeb;
        Utils.nombreServicioWebActual = nombreServicioWeb;
    }
    //-----------------simple-------------//
    public ServiciosWeb(Activity context, View view, NombreServicioWeb nombreServicioWeb) {
        this.context = context;
        this.nombreServicioWeb = nombreServicioWeb;
        this.view = view;
        Utils.nombreServicioWebActual = nombreServicioWeb;
    }

    //-----------------Para Usarlo Con recycler--------------//
    public ServiciosWeb(Activity context, NombreServicioWeb nombreServicioWeb,
                        RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        this.context = context;
        this.nombreServicioWeb = nombreServicioWeb;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        Utils.nombreServicioWebActual = nombreServicioWeb;
    }

    public ServiciosWeb(Activity context, NombreServicioWeb nombreServicioWeb, Spinner spinner) {

        this.context = context;
        this.nombreServicioWeb = nombreServicioWeb;
        Utils.nombreServicioWebActual = nombreServicioWeb;
        this.spinner = spinner;
    }

    public void LogIn(String txtUsuario, String txtContrasena) {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.add("grant_type");
        Constants.KEY_NAME.add("username");
        Constants.KEY_NAME.add("password");
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Verificando Credenciales").execute("post", Metodos.oauth + "/token","password" , txtUsuario, txtContrasena);
    }

    public void GetBancosAlimentos()
    {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.bancoAlimentos);

    }

    public void GetCentrosComunitarios()
    {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.comunitarios);

    }

    public void setEncuesta(String jsonObject)
    {

        Constants.JSON_PARAMS = new ArrayList<>();
        //Constants.JSON_PARAMS.add("NombreEstudio");
        Constants.JSON_PARAMS.add("json");
        //Constants.JSON_PARAMS.add("date");
        //Constants.JSON_PARAMS.add("beneficiario");
        String id="";
        try {
            JSONObject obj = new JSONObject(jsonObject);
             id = obj.getString("beneficiarioId");
        }catch (JSONException ex)
        {
            Log.i(TAG, ex.toString());
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String date = df.format(Calendar.getInstance().getTime());


        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("post", Metodos.api + "/" + Metodos.estudioseconomicos ,jsonObject);

    }

    @Override
    public void onTaskCompleted(String response) {
        if (response != null && !response.equals("")) {
            try {
                switch (nombreServicioWeb) {
                    case Login:
                        if (new JSONObject(response).isNull("error")) {
                            if (SetHeaderes.TokenServicios == null) {
                                SetHeaderes.TokenServicios = new JSONObject(response).getString("access_token");
                            }
                            Utils.sendSharedPreference(context);
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);

                        } else {

                            Utils.snackbar(view,  new JSONObject(response).getString("error_description") ,
                                    context.getResources().getColor(R.color.colorAccent),
                                    context.getResources().getColor(R.color.colorPrimary)).show();

                        }
                        break;
                    case GetCentrosComunitarios:
                    case GetBancosAlimentos:
                        if (new JSONObject(response).isNull("error")) {

                            adapter = new ContactsAdapter (context, ContactsDatos.Data(response,nombreServicioWeb));
                            recyclerView.setAdapter(adapter);

                        } else {

                            Utils.snackbar(view,  new JSONObject(response).getString("error_description") ,
                                    context.getResources().getColor(R.color.colorAccent),
                                    context.getResources().getColor(R.color.colorPrimary)).show();

                        }

                        break;
                    case setEncuesta:
                        if (!new JSONArray (response).getJSONObject(0).has("FolioFamiliar")) {
                            Utils.snackbar(view,  "Error al enviar" ,
                                    context.getResources().getColor(R.color.colorAccent),
                                    context.getResources().getColor(R.color.colorPrimary)).show();
                        }
                        else {                            Utils.snackbar(view,  "Enviado con exito" ,
                                context.getResources().getColor(R.color.colorPrimary),
                                context.getResources().getColor(R.color.colorAccent)).show();
                        }

                        break;

                }
            } catch (Exception ex) {
                Log.e("ErrorAlIngresar", ex.toString());
            }
        }
    }
}
