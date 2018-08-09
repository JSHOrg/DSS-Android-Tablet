package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import org.json.JSONException;
import org.json.JSONObject;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 17/07/2018.
 */

public class FragmentEstudioSEAlimentacion  extends Fragment
{
    Switch swRes1,swRes2,swRes3,swRes4,swRes5,swRes6,swRes7,swRes8,swRes9,swRes10,swRes11,swRes12,swRes13;
    RadioButton  rbEstatusRechazado,rbEstatusEspera,rbEstatusAprobado,
            rbTipoCouta,rbTipoBeca,rbTipoMediaBeca,
            rbFrecuenciaSemanal,rbFrecuenciaQuincenal,rbFrecuenciaMensual;
    RadioGroup rgEstatus, rgTipo,rgFrecuencia;
    TextInputEditText tvMeses;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se_alimentacion, container, false);

        swRes1 = (Switch) view.findViewById(R.id.sw_resp_1);
        swRes2 = (Switch) view.findViewById(R.id.sw_resp_2);
        swRes3 = (Switch) view.findViewById(R.id.sw_resp_3);
        swRes4 = (Switch) view.findViewById(R.id.sw_resp_4);
        swRes5 = (Switch) view.findViewById(R.id.sw_resp_5);
        swRes6 = (Switch) view.findViewById(R.id.sw_resp_6);
        swRes7 = (Switch) view.findViewById(R.id.sw_resp_7);
        swRes8 = (Switch) view.findViewById(R.id.sw_resp_8);
        swRes9 = (Switch) view.findViewById(R.id.sw_resp_9);
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

        rbFrecuenciaSemanal = (RadioButton) view.findViewById(R.id.rb_frecuencia_semanal);
        rbFrecuenciaQuincenal = (RadioButton) view.findViewById(R.id.rb_frecuencia_quincenal);
        rbFrecuenciaMensual = (RadioButton) view.findViewById(R.id.rb_frecuencia_mensual);

        rgEstatus = (RadioGroup) view.findViewById(R.id.rg_Estatus);
        rgTipo = (RadioGroup) view.findViewById(R.id.rg_tipo);
        rgFrecuencia = (RadioGroup) view.findViewById(R.id.rg_frecuencia);

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

            int radioButtonFrecuenciaID = rgFrecuencia.getCheckedRadioButtonId();
            View radioButtonFrecuencia = rgFrecuencia.findViewById(radioButtonFrecuenciaID);
            int idxFrecuencia = rgFrecuencia.indexOfChild(radioButtonFrecuencia);
            RadioButton rFrecuencia = (RadioButton)  rgFrecuencia.getChildAt(idxFrecuencia);
            String selectedtextFrecuencia = rFrecuencia.getText().toString();

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("¿Alguna vez usted o algún adulto en su hogar tuvo una alimentación basada en poca variedad de alimentos?",
                    swRes1.isChecked());
            jsonBody.put("¿Alguna vez usted o algún adulto en su hogar comió menos de lo que piensa debería comer?",
                    swRes2.isChecked());
            jsonBody.put("¿Alguna vez usted o algún adulto en su hogar dejó de desayunar, comer o cenar?",
                    swRes3.isChecked());
            jsonBody.put("¿Alguna vez se quedaron sin comida?",
                    swRes4.isChecked());
            jsonBody.put("¿Alguna vez usted o algún adulto en su hogar sintió hambre pero no comió?",
                    swRes5.isChecked());
            jsonBody.put("¿Alguna vez usted o algún adulto en su hogar sólo comió una vez al día o dejó de comer durante un día?",
                    swRes6.isChecked());
            jsonBody.put("¿Alguna vez  algún menor de 18 años en su hogar tuvo una alimentación basada en poca variedad?",
                    swRes7.isChecked());
            jsonBody.put("¿Alguna vez algún menor de 18 años en su hogar comió menos de lo que piensa debería comer?",
                    swRes8.isChecked());
            jsonBody.put("¿Alguna vez algún menor de 18 años en su hogar dejó de desayunar, comer o cenar?",
                    swRes9.isChecked());
            jsonBody.put("¿Alguna vez en su hogar tuvieron que disminuir la cantidad servida en la comida a algún menor de 18 años?",
                    swRes10.isChecked());
            jsonBody.put("¿Alguna vez algún menor de 18 años en su hogar sintió hambre pero no comió?",
                    swRes11.isChecked());
            jsonBody.put("¿Alguna vez un menor de 18 años se durmió con hambre?",
                    swRes12.isChecked());
            jsonBody.put("¿Alguna vez algún menor de 18 años en su hogar sólo comió una vez al día o dejó de comer durante un día?",
                    swRes13.isChecked());
            jsonBody.put("Estatus",selectedtextEstatus);
            jsonBody.put("Tipo",selectedtextTipo);
            jsonBody.put("Frecuencia",selectedtextFrecuencia);


            Utils.jsonEncuesta.put("Alimentacion",jsonBody);
        }catch (JSONException ex)
        {}


    }

}