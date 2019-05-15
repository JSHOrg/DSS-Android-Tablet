package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.apache.commons.lang3.StringUtils;
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
    etMunicipioNacimiento, etEstadoNacimiento,et_id_doc_identidad;
    Spinner spinner,sp_identidad;
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
        et_id_doc_identidad= (TextInputEditText) view.findViewById(R.id.et_id_doc_identidad) ;

        spinner= (Spinner) view.findViewById(R.id.spinner);
        sp_identidad = (Spinner) view.findViewById(R.id.sp_identidad);
        new Utils(getActivity()).mostrarCatalogo(spinner,"Escolaridad");
        new Utils(getActivity()).mostrarCatalogo(sp_identidad,"DocIdentidad");

        rg= (RadioGroup) view.findViewById(R.id.rg);


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
            jsonBody.put(StringUtils.stripAccents("Comunidad"), etComunidad.getText());
            jsonBody.put(StringUtils.stripAccents("Nombre del titular"), etTitular.getText());

            jsonBody.put(StringUtils.stripAccents("idDocumentoIdentidad"), et_id_doc_identidad.getText());
            jsonBody.put(StringUtils.stripAccents("tipoidentidad"), sp_identidad.getSelectedItem().toString());

            jsonBody.put(StringUtils.stripAccents("Calle"), etCalle.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Colonia"), etColonia.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Municipio"), etMunicipio.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Número exterior"), etNumExt.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Número interior"), etNumInt.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Municipio"), etMunicipioNacimiento.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Estado"), etEstadoNacimiento.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Escolaridad"), spinner.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Estatus Escolaridad"), selectedtext);

            Utils.jsonEncuesta.put("Representante ", jsonBody);

        }catch (JSONException ex)
        {}
    }

}