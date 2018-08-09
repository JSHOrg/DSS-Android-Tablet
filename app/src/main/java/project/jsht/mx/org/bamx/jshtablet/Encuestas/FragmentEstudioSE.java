package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONException;
import org.json.JSONObject;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 19/06/2018.
 */

public class FragmentEstudioSE extends Fragment
{
    TextInputEditText tvRespOficio,tvRespActividad,tvRespTrabajo,tvRespOficioOtro;
    RadioButton  rbRelacionado, rbNinguno, rbActOtro, rbConst, rbCampo, rbCarrera
            ,rbNingunOficio,rbAlgunOficio,rbOtroOficio;
    RadioGroup rgConocimiento,rgActividadProductiva;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se, container, false);


        rgConocimiento = (RadioGroup) view.findViewById(R.id.rg_conocimiento);
        rgActividadProductiva = (RadioGroup) view.findViewById(R.id.rg_actividad_productiva);

        ////////Pregunta 1
        tvRespTrabajo = (TextInputEditText) view.findViewById(R.id.tv_res_trabajo);

       ////////Pregunta 2
        rbRelacionado = (RadioButton) view.findViewById(R.id.rb_relacionado);
        rbNinguno = (RadioButton) view.findViewById(R.id.rb_ninguno);
        rbActOtro = (RadioButton) view.findViewById(R.id.rb_act_otro);
        tvRespActividad = (TextInputEditText) view.findViewById(R.id.tv_resp_actividad);


        ///////Pregunta 3
        rbConst = (RadioButton) view.findViewById(R.id.rb_const);
        rbCampo = (RadioButton) view.findViewById(R.id.rb_campo);
        rbCarrera = (RadioButton) view.findViewById(R.id.rb_carrera);
        rbNingunOficio = (RadioButton) view.findViewById(R.id.rb_nungun_oficio);
        rbAlgunOficio = (RadioButton) view.findViewById(R.id.rb_algun_oficio);
        tvRespOficio = (TextInputEditText) view.findViewById(R.id.tv_resp_oficio);
        rbOtroOficio = (RadioButton) view.findViewById(R.id.rb_otro_oficio);
        tvRespOficioOtro = (TextInputEditText) view.findViewById(R.id.tv_resp_oficio_otro);



        return view;
    }

    public void guardar()
    {
        try {
            int radioButtonConocimientoID = rgConocimiento.getCheckedRadioButtonId();
            View radioButtonConocimiento = rgConocimiento.findViewById(radioButtonConocimientoID);
            int idx = rgConocimiento.indexOfChild(radioButtonConocimiento);
            RadioButton rConocimiento = (RadioButton)  rgConocimiento.getChildAt(idx);
            String selectedtext = rConocimiento.getText().toString();

            int radioButtonActividadProductivaID = rgActividadProductiva.getCheckedRadioButtonId();
            View radioButtonActividadProductiva= rgActividadProductiva.findViewById(radioButtonActividadProductivaID);
            int idxActividadProductiva = rgActividadProductiva.indexOfChild(radioButtonActividadProductiva);
            RadioButton rActividadProductiva = (RadioButton)  rgActividadProductiva.getChildAt(idxActividadProductiva);
            String selectedtextActividadProductiva = rActividadProductiva.getText().toString();


            JSONObject jsonBody = new JSONObject();
            jsonBody.put("¿A qué se dedican las personas de su familia en edad productiva?", tvRespTrabajo.getText().toString());
            jsonBody.put("Si pudiera especializarse en alguna actividad productiva ¿Qué le gustaría realizar?",
                    selectedtext != "Otro." ? selectedtext : tvRespActividad);
            jsonBody.put("¿Con qué conocimientos cuentan las personas de su familia económicamente activas?",
                    selectedtextActividadProductiva == "Algún oficio." ? tvRespOficio.getText() :
                            selectedtextActividadProductiva == "Otro." ? tvRespOficioOtro.getText() : selectedtextActividadProductiva);

            Utils.jsonEncuesta.put("Control",jsonBody);
        }catch (JSONException ex)
        {}


    }

}