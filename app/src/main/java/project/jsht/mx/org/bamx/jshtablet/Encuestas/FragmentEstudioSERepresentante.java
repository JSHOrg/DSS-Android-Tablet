package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 17/07/2018.
 */

public class FragmentEstudioSERepresentante extends Fragment
{
    TextInputEditText etComunidad,etTitular,etCalle,etColonia,etMunicipio,etNumExt,etNumInt,
    etMunicipioNacimiento, etEstadoNacimiento;
    Spinner spinner;
    RadioGroup rg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se_representante, container, false);
        etComunidad = (TextInputEditText) view.findViewById(R.id.et_comunidad) ;
        etTitular= (TextInputEditText) view.findViewById(R.id.et_titular);
        etCalle= (TextInputEditText) view.findViewById(R.id.et_calle);
        etColonia= (TextInputEditText) view.findViewById(R.id.et_colonia) ;
        etMunicipio= (TextInputEditText) view.findViewById(R.id.et_municipio) ;
        etNumExt= (TextInputEditText) view.findViewById(R.id.et_num_ext);
        etNumInt= (TextInputEditText) view.findViewById(R.id.et_num_int);
        etMunicipioNacimiento= (TextInputEditText) view.findViewById(R.id.et_municipio_nacimiento);
        etEstadoNacimiento= (TextInputEditText) view.findViewById(R.id.et_estado_nacimiento) ;

        spinner= (Spinner) view.findViewById(R.id.spinner);

        rg= (RadioGroup) view.findViewById(R.id.rg) ;



        return view;
    }

    public void guardar()
    {
        try {

            int radioButtonID = rg.getCheckedRadioButtonId();
            View radioButton = rg.findViewById(radioButtonID);
            int idx = rg.indexOfChild(radioButton);
            RadioButton r = (RadioButton)  rg.getChildAt(idx);
            String selectedtext = r.getText().toString();

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Comunidad", etComunidad.getText());
            jsonBody.put("Nombre del titular", etTitular.getText());
            jsonBody.put("Calle", etCalle.getText().toString());
            jsonBody.put("Colonia", etColonia.getText().toString());
            jsonBody.put("Municipio", etMunicipio.getText().toString());
            jsonBody.put("Número exterior", etNumExt.getText().toString());
            jsonBody.put("Número interior", etNumInt.getText().toString());
            jsonBody.put("Municipio", etMunicipioNacimiento.getText().toString());
            jsonBody.put("Estado", etEstadoNacimiento.getText().toString());
            jsonBody.put("Escolaridad", spinner.getSelectedItem().toString());
            jsonBody.put("Estatus Escolaridad", selectedtext);

            Utils.jsonEncuesta.put("Representante ", jsonBody);

        }catch (JSONException ex)
        {}
    }

}