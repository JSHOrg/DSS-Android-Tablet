package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 17/07/2018.
 */

public class FragmentEstudioSEIV extends Fragment
{

    RadioGroup rgCaracteristicas;
    Switch sw1Tiene,sw2Tiene,sw3Tiene,sw4Tiene,sw5Tiene,sw6Tiene,
            sw7Tiene,sw8Tiene,sw9Tiene,sw10Tiene,sw11Tiene,sw12Tiene
            ,sw13Tiene,sw14Tiene,sw15Tiene;
    Switch sw1Sirve,sw2Sirve,sw3Sirve,sw4Sirve,sw5Sirve,sw6Sirve,
            sw7Sirve,sw8Sirve,sw9Sirve,sw10Sirve,sw11Sirve,sw12Sirve
            ,sw13Sirve,sw14Sirve,sw15Sirve;
    Spinner spNoCuartos, spCuartos,sp_tenencia,sp_casa,sp_muros,sp_piso,sp_techo,sp_condiciones;


    RadioButton rb35,rb36;

    public static ArrayList<RadioButton> rbcaracteristicas= new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se_iv, container, false);




        sp_tenencia = (Spinner) view.findViewById(R.id.sp_tenencia);

        sp_casa = (Spinner) view.findViewById(R.id.sp_casa);

        sp_muros = (Spinner) view.findViewById(R.id.sp_muros);

        sp_piso = (Spinner) view.findViewById(R.id.sp_piso);

        sp_techo = (Spinner) view.findViewById(R.id.sp_techo);

        sp_condiciones = (Spinner) view.findViewById(R.id.sp_condiciones);

        new Utils(getActivity()).mostrarCatalogo(sp_tenencia,"Tenencia");
        new Utils(getActivity()).mostrarCatalogo(sp_casa,"TipoVivienda");
        new Utils(getActivity()).mostrarCatalogo(sp_muros,"Paredes");
        new Utils(getActivity()).mostrarCatalogo(sp_piso,"TipoPisos");
        new Utils(getActivity()).mostrarCatalogo(sp_techo,"Techos");
        new Utils(getActivity()).mostrarCatalogo(sp_condiciones,"CondicionesVivienda");



        rb35 = (RadioButton) view.findViewById(R.id.rb35);
        rb36 = (RadioButton) view.findViewById(R.id.rb36);
        rbcaracteristicas.add(rb35);
        rbcaracteristicas.add(rb36);

        spNoCuartos = (Spinner) view.findViewById(R.id.sp_no_cuartos);
        spCuartos = (Spinner) view.findViewById(R.id.sp_cuartos);

        sw1Tiene = (Switch) view.findViewById(R.id.sw1_tiene);
        sw1Sirve = (Switch) view.findViewById(R.id.sw1_sirve);

        sw2Tiene = (Switch) view.findViewById(R.id.sw2_tiene);
        sw2Sirve = (Switch) view.findViewById(R.id.sw2_sirve);

        sw3Tiene = (Switch) view.findViewById(R.id.sw3_tiene);
        sw3Sirve = (Switch) view.findViewById(R.id.sw3_sirve);

        sw4Tiene = (Switch) view.findViewById(R.id.sw4_tiene);
        sw4Sirve = (Switch) view.findViewById(R.id.sw4_sirve);

        sw5Tiene = (Switch) view.findViewById(R.id.sw5_tiene);
        sw5Sirve = (Switch) view.findViewById(R.id.sw5_sirve);

        sw6Tiene = (Switch) view.findViewById(R.id.sw6_tiene);
        sw6Sirve = (Switch) view.findViewById(R.id.sw6_sirve);

        sw7Tiene = (Switch) view.findViewById(R.id.sw7_tiene);
        sw7Sirve = (Switch) view.findViewById(R.id.sw7_sirve);

        sw8Tiene = (Switch) view.findViewById(R.id.sw8_tiene);
        sw8Sirve = (Switch) view.findViewById(R.id.sw8_sirve);

        sw9Tiene = (Switch) view.findViewById(R.id.sw9_tiene);
        sw9Sirve = (Switch) view.findViewById(R.id.sw9_sirve);

        sw10Tiene = (Switch) view.findViewById(R.id.sw10_tiene);
        sw10Sirve = (Switch) view.findViewById(R.id.sw10_sirve);

        sw11Tiene = (Switch) view.findViewById(R.id.sw11_tiene);
        sw11Sirve = (Switch) view.findViewById(R.id.sw11_sirve);

        sw12Tiene = (Switch) view.findViewById(R.id.sw12_tiene);
        sw12Sirve = (Switch) view.findViewById(R.id.sw12_sirve);

        sw13Tiene = (Switch) view.findViewById(R.id.sw13_tiene);
        sw13Sirve = (Switch) view.findViewById(R.id.sw13_sirve);

        sw14Tiene = (Switch) view.findViewById(R.id.sw14_tiene);
        sw14Sirve = (Switch) view.findViewById(R.id.sw14_sirve);

        sw15Tiene = (Switch) view.findViewById(R.id.sw15_tiene);
        sw15Sirve = (Switch) view.findViewById(R.id.sw15_sirve);





        return view;
    }

    public void guardar()
    {
        try {

            String selectedtextCaracteristicas = "";


            for (RadioButton radio:rbcaracteristicas) {
                if (radio.isChecked())
                    selectedtextCaracteristicas = radio.getText().toString();

            }




            JSONArray jsonArray;
            JSONObject jsonObject,jsonBody  = new JSONObject();
            jsonBody.put(StringUtils.stripAccents("Tenencia"),sp_tenencia.getSelectedItem().toString() );
            jsonBody.put(StringUtils.stripAccents("Tipo de casa"),sp_casa.getSelectedItem().toString() );
            jsonBody.put(StringUtils.stripAccents("Mayor parte de muros"),sp_muros.getSelectedItem().toString() );
            jsonBody.put(StringUtils.stripAccents("Mayor parte de piso"),sp_piso.getSelectedItem().toString() );
            jsonBody.put(StringUtils.stripAccents("Mayor parte de techo"),sp_techo.getSelectedItem().toString() );
            jsonBody.put(StringUtils.stripAccents("Características"),selectedtextCaracteristicas );
            jsonBody.put(StringUtils.stripAccents("Condiciones"),sp_condiciones.getSelectedItem().toString() );

            jsonBody.put(StringUtils.stripAccents("No. de cuartos "),spNoCuartos.getSelectedItem().toString() );
            jsonBody.put(StringUtils.stripAccents("Cuartos para dormir "),spCuartos.getSelectedItem().toString() );

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw1Tiene.isChecked());
            jsonObject.put("Sirve",sw1Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Refrigerador",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw2Tiene.isChecked());
            jsonObject.put("Sirve",sw2Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Estufa",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw3Tiene.isChecked());
            jsonObject.put("Sirve",sw3Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("VHS, DVD, Blu Ray",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw4Tiene.isChecked());
            jsonObject.put("Sirve",sw4Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Lavadora",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw5Tiene.isChecked());
            jsonObject.put("Sirve",sw5Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Licuadora",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw6Tiene.isChecked());
            jsonObject.put("Sirve",sw6Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put(StringUtils.stripAccents("Radio"),jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw7Tiene.isChecked());
            jsonObject.put("Sirve",sw7Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Sala",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw8Tiene.isChecked());
            jsonObject.put("Sirve",sw8Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Comedor",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw9Tiene.isChecked());
            jsonObject.put("Sirve",sw9Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put(StringUtils.stripAccents("Automóvil"),jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw10Tiene.isChecked());
            jsonObject.put("Sirve",sw10Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Cama",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw11Tiene.isChecked());
            jsonObject.put("Sirve",sw11Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Celular",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw12Tiene.isChecked());
            jsonObject.put("Sirve",sw12Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Motocicleta",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw13Tiene.isChecked());
            jsonObject.put("Sirve",sw13Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Computadora",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw14Tiene.isChecked());
            jsonObject.put("Sirve",sw14Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put("Horno",jsonArray);

            jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("Tiene",sw15Tiene.isChecked());
            jsonObject.put("Sirve",sw15Sirve.isChecked());
            jsonArray.put(jsonObject);
            jsonBody.put(StringUtils.stripAccents("Teléfono"),jsonArray);



            Utils.jsonEncuesta.put("Infraestructura de vivienda",jsonBody);
        }catch (JSONException ex)
        {}


    }

}