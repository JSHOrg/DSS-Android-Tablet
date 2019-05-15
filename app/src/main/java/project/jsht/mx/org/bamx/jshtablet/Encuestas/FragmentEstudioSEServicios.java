package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.RadioGridGroup;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 17/07/2018.
 */

public class FragmentEstudioSEServicios extends Fragment
{
    Spinner sp_luz,sp_drenaje,sp_escusado,sp_combustible,sp_agua;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se_servicios, container, false);

        sp_luz= (Spinner) view.findViewById(R.id.sp_luz);
        sp_drenaje= (Spinner) view.findViewById(R.id.sp_drenaje);
        sp_escusado= (Spinner) view.findViewById(R.id.sp_escusado);
        sp_combustible= (Spinner) view.findViewById(R.id.sp_combustible);
        sp_agua= (Spinner) view.findViewById(R.id.sp_agua);

        new Utils(getActivity()).mostrarCatalogo(sp_luz,"ServiciosLuz");
        new Utils(getActivity()).mostrarCatalogo(sp_drenaje,"ServiciosSanitarios");
        new Utils(getActivity()).mostrarCatalogo(sp_escusado,"BanoExcusado");
        new Utils(getActivity()).mostrarCatalogo(sp_combustible,"ServiciosGas");
        new Utils(getActivity()).mostrarCatalogo(sp_agua,"ServiciosAgua");


        return view;
    }


    public void guardar()
    {
        try {


            JSONObject jsonBody = new JSONObject();
            jsonBody.put(StringUtils.stripAccents("Luz"),sp_luz.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Drenaje"), sp_drenaje.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Escusado"), sp_escusado.getSelectedItem().toString()) ;
            jsonBody.put(StringUtils.stripAccents("Combustible"), sp_combustible.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Agua"),sp_agua.getSelectedItem().toString()) ;



            Utils.jsonEncuesta.put("Servicios",jsonBody);
        }catch (JSONException ex)
        {}


    }


}