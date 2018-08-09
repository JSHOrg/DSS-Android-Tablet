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

    RadioGroup rgCondiciones, rgCaracteristicas,rgTenencia,rgCasa,rgMuros,rgEscusado,rgTecho;
    Switch sw1Tiene,sw2Tiene,sw3Tiene,sw4Tiene,sw5Tiene,sw6Tiene,
            sw7Tiene,sw8Tiene,sw9Tiene,sw10Tiene,sw11Tiene,sw12Tiene
            ,sw13Tiene,sw14Tiene,sw15Tiene;
    Switch sw1Sirve,sw2Sirve,sw3Sirve,sw4Sirve,sw5Sirve,sw6Sirve,
            sw7Sirve,sw8Sirve,sw9Sirve,sw10Sirve,sw11Sirve,sw12Sirve
            ,sw13Sirve,sw14Sirve,sw15Sirve;
    Spinner spNoCuartos, spCuartos;

    RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10,rb11,rb12,rb13,rb14,rb15,rb16,rb17,rb18,rb19,rb20
            ,rb21,rb22,rb23,rb24,rb25,rb26,rb27,rb28,rb29,rb30,rb31,rb32,rb33,rb34,rb35,rb36;


    public static ArrayList<RadioButton> rbTenencia = new ArrayList<>();
    public static ArrayList<RadioButton> rbCasa = new ArrayList<>();
    public static ArrayList<RadioButton> rbMuros = new ArrayList<>();
    public static ArrayList<RadioButton> rbEscusado = new ArrayList<>();
    public static ArrayList<RadioButton> rbTecho = new ArrayList<>();
    public static ArrayList<RadioButton> rbCondiciones = new ArrayList<>();
    public static ArrayList<RadioButton> rbcaracteristicas= new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se_iv, container, false);




        rgTenencia = (RadioGroup) view.findViewById(R.id.rg_tenencia);
        Utils.setRadioExclusiveClick(rgTenencia);

        rgCasa = (RadioGroup) view.findViewById(R.id.rg_casa);
        Utils.setRadioExclusiveClick(rgCasa);

        rgMuros = (RadioGroup) view.findViewById(R.id.rg_muros);
        Utils.setRadioExclusiveClick(rgMuros);

        rgEscusado = (RadioGroup) view.findViewById(R.id.rg_escusado);
        Utils.setRadioExclusiveClick(rgEscusado);

        rgTecho = (RadioGroup) view.findViewById(R.id.rg_techo);
        Utils.setRadioExclusiveClick(rgTecho);




        rb1 = (RadioButton) view.findViewById(R.id.rb1);
        rb2 = (RadioButton) view.findViewById(R.id.rb2);
        rb3 = (RadioButton) view.findViewById(R.id.rb3);
        rb4 = (RadioButton) view.findViewById(R.id.rb4);
        rb5 = (RadioButton) view.findViewById(R.id.rb5);
        rb6 = (RadioButton) view.findViewById(R.id.rb6);

        rbTenencia.add(rb1);
        rbTenencia.add(rb2);
        rbTenencia.add(rb3);
        rbTenencia.add(rb4);
        rbTenencia.add(rb5);
        rbTenencia.add(rb6);


        rb7 = (RadioButton) view.findViewById(R.id.rb7);
        rb8= (RadioButton) view.findViewById(R.id.rb8);
        rb9= (RadioButton) view.findViewById(R.id.rb9);
        rb10 = (RadioButton) view.findViewById(R.id.rb10);
        rb11 = (RadioButton) view.findViewById(R.id.rb11);
        rb12 = (RadioButton) view.findViewById(R.id.rb12);
        rb13 = (RadioButton) view.findViewById(R.id.rb13);
        rbCasa.add(rb7);
        rbCasa.add(rb8);
        rbCasa.add(rb9);
        rbCasa.add(rb10);
        rbCasa.add(rb11);
        rbCasa.add(rb12);
        rbCasa.add(rb13);


        rb14 = (RadioButton) view.findViewById(R.id.rb14);
        rb15 = (RadioButton) view.findViewById(R.id.rb15);
        rb16= (RadioButton) view.findViewById(R.id.rb16);
        rb17 = (RadioButton) view.findViewById(R.id.rb17);
        rb18 = (RadioButton) view.findViewById(R.id.rb18);
        rb19 = (RadioButton) view.findViewById(R.id.rb19);
        rb20 = (RadioButton) view.findViewById(R.id.rb20);
        rbMuros.add(rb14);
        rbMuros.add(rb15);
        rbMuros.add(rb16);
        rbMuros.add(rb17);
        rbMuros.add(rb18);
        rbMuros.add(rb19);
        rbMuros.add(rb20);


        rb21 = (RadioButton) view.findViewById(R.id.rb21);
        rb22 = (RadioButton) view.findViewById(R.id.rb22);
        rb23 = (RadioButton) view.findViewById(R.id.rb23);
        rb24 = (RadioButton) view.findViewById(R.id.rb24);
        rb25 = (RadioButton) view.findViewById(R.id.rb25);
        rbEscusado.add(rb21);
        rbEscusado.add(rb22);
        rbEscusado.add(rb23);
        rbEscusado.add(rb24);
        rbEscusado.add(rb25);

        rb26 = (RadioButton) view.findViewById(R.id.rb26);
        rb27 = (RadioButton) view.findViewById(R.id.rb27);
        rb28 = (RadioButton) view.findViewById(R.id.rb28);
        rb29 = (RadioButton) view.findViewById(R.id.rb29);
        rb30 = (RadioButton) view.findViewById(R.id.rb30);
        rb31 = (RadioButton) view.findViewById(R.id.rb31);
        rbTecho.add(rb26);
        rbTecho.add(rb27);
        rbTecho.add(rb28);
        rbTecho.add(rb29);
        rbTecho.add(rb30);
        rbTecho.add(rb31);

        rb32 = (RadioButton) view.findViewById(R.id.rb32);
        rb33 = (RadioButton) view.findViewById(R.id.rb33);
        rb34 = (RadioButton) view.findViewById(R.id.rb34);
        rbCondiciones.add(rb32);
        rbCondiciones.add(rb33);
        rbCondiciones.add(rb34);

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

            String selectedtext ="";
            String selectedtextTipoCasa ="";
            String selectedtextMuros ="";
            String selectedtextEscusado ="" ;
            String selectedtextTecho ="";
            String selectedtextCondiciones ="";
            String selectedtextCaracteristicas = "";


            for (RadioButton radio:rbTenencia) {
                if (radio.isChecked())
                    selectedtext = radio.getText().toString();

            }
            for (RadioButton radio:rbCasa) {
                if (radio.isChecked())
                    selectedtextTipoCasa = radio.getText().toString();

            }
            for (RadioButton radio:rbMuros) {
                if (radio.isChecked())
                    selectedtextMuros = radio.getText().toString();

            }
            for (RadioButton radio:rbEscusado) {
                if (radio.isChecked())
                    selectedtextEscusado = radio.getText().toString();

            }
            for (RadioButton radio:rbTecho) {
                if (radio.isChecked())
                    selectedtextTecho = radio.getText().toString();

            }

            for (RadioButton radio:rbCondiciones) {
                if (radio.isChecked())
                    selectedtextCondiciones = radio.getText().toString();

            }

            for (RadioButton radio:rbcaracteristicas) {
                if (radio.isChecked())
                    selectedtextCaracteristicas = radio.getText().toString();

            }




            JSONArray jsonArray;
            JSONObject jsonObject,jsonBody  = new JSONObject();
            jsonBody.put("Tenencia",selectedtext );
            jsonBody.put("Tipo de casa",selectedtextTipoCasa );
            jsonBody.put("Mayor parte de muros",selectedtextMuros );
            jsonBody.put("Baño / escusado",selectedtextEscusado );
            jsonBody.put("Mayor parte de techo",selectedtextTecho );
            jsonBody.put("Características",selectedtextCaracteristicas );
            jsonBody.put("Condiciones",selectedtextCondiciones );

            jsonBody.put("No. de cuartos ",spNoCuartos.getSelectedItem().toString() );
            jsonBody.put("Cuartos para dormir ",spCuartos.getSelectedItem().toString() );

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
            jsonBody.put("Radio",jsonArray);

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
            jsonBody.put("Automóvil",jsonArray);

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
            jsonBody.put("Teléfono",jsonArray);



            Utils.jsonEncuesta.put("Infraestructura de vivienda",jsonBody);
        }catch (JSONException ex)
        {}


    }

}