package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

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

    TextInputEditText tvTipoApoyo,tvProporciona,tvFrecuencia;
    TextInputEditText tvFrecuenciaRemesas;
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
        tvProporciona = (TextInputEditText) view.findViewById(R.id.tv_proporciona);
        tvFrecuencia = (TextInputEditText) view.findViewById(R.id.tv_frecuencia);

        tvFrecuenciaRemesas = (TextInputEditText) view.findViewById(R.id.tv_frecuencia_remesas);

        swResp = (Switch) view.findViewById(R.id.sw_resp);

        return view;
    }

    public void guardar()
    {
        try {

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Vivienda", String.valueOf(tvVivienda.getText().toString()));
            jsonBody.put("Alimentación", String.valueOf(tvAlimentacion.getText().toString()));
            jsonBody.put("Luz", String.valueOf(tvLuz.getText().toString()));
            jsonBody.put("Gas", String.valueOf(tvGas.getText().toString()));
            jsonBody.put("Agua", String.valueOf(tvAgua.getText().toString()));
            jsonBody.put("Teléfono", String.valueOf(tvTelefono.getText().toString()));
            jsonBody.put("Celular", String.valueOf(tvCelular.getText().toString()));
            jsonBody.put("Atención médica", String.valueOf(tvMedica.getText().toString()));
            jsonBody.put("Educación", String.valueOf(tvEducacion.getText().toString()));
            jsonBody.put("Transporte", String.valueOf(tvTransporte.getText().toString()));
            jsonBody.put("Condiciones económicas Otros", String.valueOf(tvCondicionesOtros.getText().toString()));
            jsonBody.put("Condiciones económicas TOTAL", String.valueOf(tvCondicionesTotal.getText().toString()));
            jsonBody.put("Padre", String.valueOf(tvPadre.getText().toString()));
            jsonBody.put("Madre", String.valueOf(tvMadre.getText().toString()));
            jsonBody.put("Hijos", String.valueOf(tvHijos.getText().toString()));
            jsonBody.put("PROSPERA", String.valueOf(tvProspera.getText().toString()));
            jsonBody.put("Adultos mayores ", String.valueOf(tvAdultos.getText().toString()));
            jsonBody.put("Becas", String.valueOf(tvBecas.getText().toString()));
            jsonBody.put("Pensión", String.valueOf(tvPension.getText().toString()));
            jsonBody.put("Aportación semanal Otros", String.valueOf(tvAportacionOtros.getText().toString()));
            jsonBody.put("Aportación semanal TOTAL", String.valueOf(tvAportacionTotal.getText().toString()));
            jsonBody.put("Tipo de apoyo", tvTipoApoyo.getText().toString());
            jsonBody.put("Quien lo proporciona",tvProporciona.getText().toString());
            jsonBody.put("Frecuencia del apoyo", tvFrecuencia.getText().toString());
            jsonBody.put("¿Alguien en el hogar recibe dinero proveniente de otros países?", tvFrecuenciaRemesas.getText().toString());
            jsonBody.put("Frecuencia del apoyo", swResp.isChecked());


            Utils.jsonEncuesta.put("Condiciones económicas",jsonBody);
        }catch (JSONException ex)
        {}


    }

}