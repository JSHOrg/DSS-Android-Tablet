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
import android.widget.Switch;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 17/07/2018.
 */

public class FragmentEstudioSEAlimentacion  extends Fragment
{
    Switch swRes2,swRes3,swRes4,swRes5,swRes6,swRes7,swRes8,swRes10,swRes11,swRes12,swRes13;
    RadioButton  rbEstatusRechazado,rbEstatusEspera,rbEstatusAprobado,
            rbTipoCouta,rbTipoBeca,rbTipoMediaBeca,
            rbFrecuenciaSemanal,rbFrecuenciaQuincenal,rbFrecuenciaMensual;
    RadioGroup rgEstatus, rgTipo;
    Spinner spFrecuencia;
    TextInputEditText tvMeses;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se_alimentacion, container, false);

        swRes2 = (Switch) view.findViewById(R.id.sw_resp_2);
        swRes3 = (Switch) view.findViewById(R.id.sw_resp_3);
        swRes4 = (Switch) view.findViewById(R.id.sw_resp_4);
        swRes5 = (Switch) view.findViewById(R.id.sw_resp_5);
        swRes6 = (Switch) view.findViewById(R.id.sw_resp_6);
        swRes7 = (Switch) view.findViewById(R.id.sw_resp_7);
        swRes8 = (Switch) view.findViewById(R.id.sw_resp_8);
        swRes10 = (Switch) view.findViewById(R.id.sw_resp_10);
        swRes11 = (Switch) view.findViewById(R.id.sw_resp_11);
        swRes12 = (Switch) view.findViewById(R.id.sw_resp_12);
        swRes13 = (Switch) view.findViewById(R.id.sw_resp_13);

        rbEstatusRechazado = (RadioButton) view.findViewById(R.id.rb_estatus_rechazado);
        rbEstatusEspera = (RadioButton) view.findViewById(R.id.rb_estatus_espera);
        rbEstatusAprobado = (RadioButton) view.findViewById(R.id.rb_estatus_aprobado);

        rbTipoCouta = (RadioButton) view.findViewById(R.id.rb_tipo_couta);
        rbTipoBeca = (RadioButton) view.findViewById(R.id.rb_tipo_beca);
        rbTipoMediaBeca = (RadioButton) view.findViewById(R.id.rb_tipo_media_beca);


        rgEstatus = (RadioGroup) view.findViewById(R.id.rg_Estatus);
        rgTipo = (RadioGroup) view.findViewById(R.id.rg_tipo);

        spFrecuencia = (Spinner) view.findViewById(R.id.sp_frecuencia);
        new Utils(getActivity()).mostrarCatalogo(spFrecuencia,"Frecuencia");

        tvMeses = (TextInputEditText) view.findViewById(R.id.tv_meses);

        return view;
    }

    public void guardar()
    {
        try {
            int radioButtonEstatusID = rgEstatus.getCheckedRadioButtonId();
            View radioButtonEstatus = rgEstatus.findViewById(radioButtonEstatusID);
            int idxEstatus = rgEstatus.indexOfChild(radioButtonEstatus);
            RadioButton rEstatus = (RadioButton)  rgEstatus.getChildAt(idxEstatus);
            String selectedtextEstatus = rEstatus.getText().toString();

            int radioButtonTipoID = rgTipo.getCheckedRadioButtonId();
            View radioButtonTipo= rgTipo.findViewById(radioButtonTipoID);
            int idxTipo = rgTipo.indexOfChild(radioButtonTipo);
            RadioButton rTipo= (RadioButton)  rgTipo.getChildAt(idxTipo);
            String selectedtextTipo = rTipo.getText().toString();

            JSONObject jsonBody = new JSONObject();
            JSONArray jsonArrayPreg = new JSONArray();


            JSONObject jsonObjectP2 = new JSONObject();
            JSONObject jsonObjectP3 = new JSONObject();
            JSONObject jsonObjectP4 = new JSONObject();
            JSONObject jsonObjectP5 = new JSONObject();
            JSONObject jsonObjectP6 = new JSONObject();
            JSONObject jsonObjectP7 = new JSONObject();
            JSONObject jsonObjectP8 = new JSONObject();
            JSONObject jsonObjectP10 = new JSONObject();
            JSONObject jsonObjectP11 = new JSONObject();
            JSONObject jsonObjectP12 = new JSONObject();
            JSONObject jsonObjectP13 = new JSONObject();


            jsonObjectP2.put("Pregunta","¿Alguna vez usted o algún adulto de su hogar comió menos de lo que usted piensa debía comer?");
            jsonObjectP2.put("Respuesta",  swRes2.isChecked());

            jsonObjectP3.put("Pregunta","¿Alguna vez usted o algún adulto de su hogar dejo de desayunar, comer o cenar? ");
            jsonObjectP3.put("Respuesta",swRes3.isChecked());

            jsonObjectP4.put("Pregunta","¿Alguna vez se quedaron sin comida?");
            jsonObjectP4.put("Respuesta",swRes4.isChecked());

            jsonObjectP5.put("Pregunta","¿Alguna vez usted o algún adulto en su hogar sintió hambre pero no comió?");
            jsonObjectP5.put("Respuesta",swRes5.isChecked());

            jsonObjectP6.put("Pregunta","¿Alguna vez usted o algún adulto en su hogar solo comió una vez al día o dejo de comer durante todo el día?");
            jsonObjectP6.put("Respuesta",swRes6.isChecked());

            jsonObjectP7.put("Pregunta","¿Alguna vez algún menor de 18 años en su hogar tuvo una alimentacion basada en poca variedad de alimentos?");
            jsonObjectP7.put("Respuesta",swRes7.isChecked());

            jsonObjectP8.put("Pregunta","¿Alguna vez algún menor de 18 años en su hogar comió menos de lo que debía?");
            jsonObjectP8.put("Respuesta",swRes8.isChecked());


            jsonObjectP10.put("Pregunta","¿Alguna vez en su hogar tuvieron que disminuir la cantidad servidad en la comida a algun menor de 18 años?");
            jsonObjectP10.put("Respuesta", swRes10.isChecked());

            jsonObjectP11.put("Pregunta","¿Alguna vez algún menor de 18 años en su hogar sintió hambre pero no comió?");
            jsonObjectP11.put("Respuesta",swRes11.isChecked());

            jsonObjectP12.put("Pregunta","¿Algún menor de 18 años se acostó con hambre?");
            jsonObjectP12.put("Respuesta", swRes12.isChecked());

            jsonObjectP13.put("Pregunta","¿Alguna vez algún menor de 18 años en su hogar comió una vez al día o dejo de comer durante todo un día?");
            jsonObjectP13.put("Respuesta", swRes13.isChecked());

            jsonArrayPreg.put(jsonObjectP2);
            jsonArrayPreg.put(jsonObjectP3);
            jsonArrayPreg.put(jsonObjectP4);
            jsonArrayPreg.put(jsonObjectP5);
            jsonArrayPreg.put(jsonObjectP6);
            jsonArrayPreg.put(jsonObjectP7);
            jsonArrayPreg.put(jsonObjectP8);
            jsonArrayPreg.put(jsonObjectP10);
            jsonArrayPreg.put(jsonObjectP11);
            jsonArrayPreg.put(jsonObjectP12);
            jsonArrayPreg.put(jsonObjectP13);

            jsonBody.put(StringUtils.stripAccents("Estatus"),selectedtextEstatus);
            jsonBody.put(StringUtils.stripAccents("Tipo"),selectedtextTipo);
            jsonBody.put(StringUtils.stripAccents("Frecuencia"),spFrecuencia.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Meses"),tvMeses.getText());
            jsonBody.put(StringUtils.stripAccents("Preguntas"),jsonArrayPreg);


            Utils.jsonEncuesta.put(StringUtils.stripAccents("Alimentacion"),jsonBody);
        }catch (JSONException ex)
        {}


    }

}