package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    public static ArrayList<RadioButton> rbDrenaje = new ArrayList<>();
    public static ArrayList<RadioButton> rbEscusado = new ArrayList<>();
    public static ArrayList<RadioButton> rbCombustible = new ArrayList<>();
    public static ArrayList<RadioButton> rbAgua = new ArrayList<>();

    RadioGroup  rgDrenaje , rgEscusado,rgCombustible,rgAgua;

    RadioGroup rgLuz,rgLuzSub1,rgLuzSub2, rgDrenajeSub1,rgDrenajeSub2,rgEscusadoSub1,rgEscusadoSub2,
            rgCombustibleSub1,rgCombustibleSub2,rgAguaSub1,rgAguaSub2,rgAguaSub3;

    RadioButton  rb_drenaje_publico, rb_drenaje_fosa,rb_drenaje_tuberia_grieta
            ,rb_drenaje_tuberia_rio,rb_drenaje_no;

    RadioButton rb_escusado_descarga, rb_escusado_cubeta,rb_escusado_letrina,
            rb_escusado_poso,rb_escusado_no;

    RadioButton rb_combustible_gas, rb_combustible_electricidad,rb_combustible_gas_n
            ,rb_combustible_carbon,rb_combustible_carbon_chimenea,rb_combustible_otro;

    RadioButton rb_agua_domiciliaria,rb_agua_comun,rb_agua_llave,rb_agua_acarrea,
            rb_agua_pipa,rb_agua_pozo,rb_agua_no;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se_servicios, container, false);

        rgLuz= (RadioGroup) view.findViewById(R.id.rg_Luz);

        rgDrenaje= (RadioGroup) view.findViewById(R.id.rg_drenaje);
        Utils.setRadioExclusiveClick(rgDrenaje);

        rgEscusado= (RadioGroup) view.findViewById(R.id.rg_escusado);
        Utils.setRadioExclusiveClick(rgEscusado);

        rgCombustible= (RadioGroup) view.findViewById(R.id.rg_combustible);
        Utils.setRadioExclusiveClick(rgCombustible);

        rgAgua= (RadioGroup) view.findViewById(R.id.rg_agua);
        Utils.setRadioExclusiveClick(rgAgua);

        rb_drenaje_publico= (RadioButton) view.findViewById(R.id.rb_drenaje_publico);
        rb_drenaje_fosa= (RadioButton) view.findViewById(R.id.rb_drenaje_fosa);
        rb_drenaje_tuberia_grieta= (RadioButton) view.findViewById(R.id.rb_drenaje_tuberia_grieta);
        rb_drenaje_tuberia_rio= (RadioButton) view.findViewById(R.id.rb_drenaje_tuberia_rio);
        rb_drenaje_no= (RadioButton) view.findViewById(R.id.rb_drenaje_no);

        rbDrenaje.add(rb_drenaje_publico);
        rbDrenaje.add(rb_drenaje_fosa);
        rbDrenaje.add(rb_drenaje_tuberia_grieta);
        rbDrenaje.add(rb_drenaje_tuberia_rio);
        rbDrenaje.add(rb_drenaje_no);


        rb_escusado_descarga= (RadioButton) view.findViewById(R.id.rb_escusado_descarga);
        rb_escusado_cubeta= (RadioButton) view.findViewById(R.id.rb_escusado_cubeta);
        rb_escusado_letrina= (RadioButton) view.findViewById(R.id.rb_escusado_letrina);
        rb_escusado_poso= (RadioButton) view.findViewById(R.id.rb_escusado_poso);
        rb_escusado_no= (RadioButton) view.findViewById(R.id.rb_escusado_no);

        rbEscusado.add(rb_escusado_descarga);
        rbEscusado.add(rb_escusado_cubeta);
        rbEscusado.add(rb_escusado_letrina);
        rbEscusado.add(rb_escusado_poso);
        rbEscusado.add(rb_escusado_no);


        rb_combustible_gas= (RadioButton) view.findViewById(R.id.rb_combustible_gas);
        rb_combustible_electricidad= (RadioButton) view.findViewById(R.id.rb_combustible_electricidad);
        rb_combustible_gas_n= (RadioButton) view.findViewById(R.id.rb_combustible_gas_n);
        rb_combustible_carbon= (RadioButton) view.findViewById(R.id.rb_combustible_carbon);
        rb_combustible_carbon_chimenea= (RadioButton) view.findViewById(R.id.rb_combustible_carbon_chimenea);
        rb_combustible_otro= (RadioButton) view.findViewById(R.id.rb_combustible_otro);

        rbCombustible.add(rb_combustible_gas);
        rbCombustible.add(rb_combustible_electricidad);
        rbCombustible.add(rb_combustible_gas_n);
        rbCombustible.add(rb_combustible_carbon);
        rbCombustible.add(rb_combustible_carbon_chimenea);
        rbCombustible.add(rb_combustible_otro);


        rb_agua_domiciliaria= (RadioButton) view.findViewById(R.id.rb_agua_domiciliaria);
        rb_agua_comun= (RadioButton) view.findViewById(R.id.rb_agua_comun);
        rb_agua_llave= (RadioButton) view.findViewById(R.id.rb_agua_llave);
        rb_agua_acarrea= (RadioButton) view.findViewById(R.id.rb_agua_acarrea);
        rb_agua_pipa= (RadioButton) view.findViewById(R.id.rb_agua_pipa);
        rb_agua_pozo= (RadioButton) view.findViewById(R.id.rb_agua_pozo);
        rb_agua_no= (RadioButton) view.findViewById(R.id.rb_agua_no);

        rbAgua.add(rb_agua_domiciliaria);
        rbAgua.add(rb_agua_comun);
        rbAgua.add(rb_agua_llave);
        rbAgua.add(rb_agua_acarrea);
        rbAgua.add(rb_agua_pipa);
        rbAgua.add(rb_agua_pozo);
        rbAgua.add(rb_agua_no);


        return view;
    }


    public void guardar()
    {
        try {

            String drenaje="",escusado="",combustible="",agua="";

            for (RadioButton radio:rbDrenaje) {
                if (radio.isChecked())
                    drenaje = radio.getText().toString();

            }

            for (RadioButton radio:rbEscusado) {
                if (radio.isChecked())
                    escusado = radio.getText().toString();

            }

            for (RadioButton radio:rbCombustible) {
                if (radio.isChecked())
                    combustible = radio.getText().toString();

            }

            for (RadioButton radio:rbAgua) {
                if (radio.isChecked())
                    agua = radio.getText().toString();

            }

            int radioButtonTenenciaID = rgLuz.getCheckedRadioButtonId();
            View radioButtonTenencia = rgLuz.findViewById(radioButtonTenenciaID);
            int idx = rgLuz.indexOfChild(radioButtonTenencia);
            RadioButton rTenencia = (RadioButton)  rgLuz.getChildAt(idx);
            String selectedtext = rTenencia.getText().toString();

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Luz",selectedtext);
            jsonBody.put("Drenaje",drenaje);
            jsonBody.put("Escusado",escusado);
            jsonBody.put("Combustible",combustible);
            jsonBody.put("Agua",agua);



            Utils.jsonEncuesta.put("Servicios",jsonBody);
        }catch (JSONException ex)
        {}


    }


}