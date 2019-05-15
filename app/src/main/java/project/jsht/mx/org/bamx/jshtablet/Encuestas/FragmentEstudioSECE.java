package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Switch;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 17/07/2018.
 */

public class FragmentEstudioSECE extends Fragment
{
    TextInputEditText tvVivienda,tvAlimentacion,tvLuz,tvGas,tvAgua,tvTelefono,tvCelular,
            tvMedica,tvEducacion,tvTransporte,tvCondicionesOtros,tvCondicionesTotal;

    TextInputEditText tvPadre,tvMadre,tvHijos,tvProspera,tvAdultos,tvBecas,tvPension
            ,tvAportacionOtros, tvAportacionTotal;

    TextInputEditText tvTipoApoyo;
    Spinner spFrecuenciaRemesas,spProporciona,spFrecuencia;;
    Switch swResp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se_ce, container, false);

        tvVivienda = (TextInputEditText) view.findViewById(R.id.tv_vivienda);
        tvAlimentacion = (TextInputEditText) view.findViewById(R.id.tv_alimentacion);
        tvLuz = (TextInputEditText) view.findViewById(R.id.tv_luz);
        tvGas = (TextInputEditText) view.findViewById(R.id.tv_gas);
        tvAgua = (TextInputEditText) view.findViewById(R.id.tv_agua);
        tvTelefono = (TextInputEditText) view.findViewById(R.id.tv_telefono);
        tvCelular = (TextInputEditText) view.findViewById(R.id.tv_celular);
        tvMedica = (TextInputEditText) view.findViewById(R.id.tv_medica);
        tvEducacion = (TextInputEditText) view.findViewById(R.id.tv_educacion);
        tvTransporte = (TextInputEditText) view.findViewById(R.id.tv_transporte);
        tvCondicionesOtros = (TextInputEditText) view.findViewById(R.id.tv_condiciones_otros);
        tvCondicionesTotal = (TextInputEditText) view.findViewById(R.id.tv_condiciones_total);

        tvPadre = (TextInputEditText) view.findViewById(R.id.tv_padre);
        tvMadre = (TextInputEditText) view.findViewById(R.id.tv_madre);
        tvHijos = (TextInputEditText) view.findViewById(R.id.tv_hijos);
        tvProspera = (TextInputEditText) view.findViewById(R.id.tv_prospera);
        tvAdultos = (TextInputEditText) view.findViewById(R.id.tv_adultos);
        tvBecas = (TextInputEditText) view.findViewById(R.id.tv_becas);
        tvPension = (TextInputEditText) view.findViewById(R.id.tv_pension);
        tvAportacionOtros = (TextInputEditText) view.findViewById(R.id.tv_aportacion_otros);
        tvAportacionTotal = (TextInputEditText) view.findViewById(R.id.tv_aportacion_total);

        tvTipoApoyo = (TextInputEditText) view.findViewById(R.id.tv_tipo_apoyo);

        spProporciona = (Spinner) view.findViewById(R.id.sp_proporciona);
        spFrecuencia = (Spinner) view.findViewById(R.id.sp_frecuencia);
        spFrecuenciaRemesas = (Spinner) view.findViewById(R.id.sp_frecuencia_remesas);

        new Utils(getActivity()).mostrarCatalogo(spProporciona,"MotivoDerechoHabiencia");
        new Utils(getActivity()).mostrarCatalogo(spFrecuencia,"Frecuencia");
        new Utils(getActivity()).mostrarCatalogo(spFrecuenciaRemesas,"Frecuencia");

        swResp = (Switch) view.findViewById(R.id.sw_resp);

        return view;
    }

    public void guardar()
    {
        try {

            JSONObject jsonBody = new JSONObject();
            jsonBody.put(StringUtils.stripAccents("Vivienda"), String.valueOf(tvVivienda.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Alimentación"), String.valueOf(tvAlimentacion.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Luz"), String.valueOf(tvLuz.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Gas"), String.valueOf(tvGas.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Agua"), String.valueOf(tvAgua.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Teléfono"), String.valueOf(tvTelefono.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Celular"), String.valueOf(tvCelular.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Atención médica"), String.valueOf(tvMedica.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Educación"), String.valueOf(tvEducacion.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Transporte"), String.valueOf(tvTransporte.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Condiciones económicas Otros"), String.valueOf(tvCondicionesOtros.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Condiciones económicas TOTAL"), String.valueOf(tvCondicionesTotal.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Padre"), String.valueOf(tvPadre.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Madre"), String.valueOf(tvMadre.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Hijos"), String.valueOf(tvHijos.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("PROSPERA"), String.valueOf(tvProspera.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Adultos mayores "), String.valueOf(tvAdultos.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Becas"), String.valueOf(tvBecas.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Pensión"), String.valueOf(tvPension.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Aportación semanal Otros"), String.valueOf(tvAportacionOtros.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Aportación semanal TOTAL"), String.valueOf(tvAportacionTotal.getText().toString()));
            jsonBody.put(StringUtils.stripAccents("Tipo de apoyo"), tvTipoApoyo.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Quien lo proporciona"),spProporciona.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Frecuencia del apoyo"), spFrecuencia.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("¿Alguien en el hogar recibe dinero proveniente de otros países?"), spFrecuenciaRemesas.getSelectedItem().toString());


            Utils.jsonEncuesta.put(StringUtils.stripAccents("Condiciones económicas"),jsonBody);
        }catch (JSONException ex)
        {}


    }

}