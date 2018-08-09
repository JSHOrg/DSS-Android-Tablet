package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.DatePickerFragment;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 17/07/2018.
 */

public class FragmentEstudioSEDG extends Fragment
{
    TextInputEditText tvNombreComunidad, tvGrupo,tvFechaLevantamiento,tvFechaCaptura,tvNombreVialidad,
            tvNumExt,tvNumInt,tvNombreAsentamiento,tvCp,tvLocalidad,tvMunicipio,
            tvClaveMunicipio,tvEstado,tvClaveEstado,tvVialidades,tvDecripcion,tvTelefono;

    Spinner spVialidades,spAsentamientos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_estudio_se_dg, container, false);

        tvNombreComunidad = (TextInputEditText) view.findViewById(R.id.tv_nombre_comunidad);
        tvGrupo = (TextInputEditText) view.findViewById(R.id.tv_grupo);
        tvFechaLevantamiento = (TextInputEditText) view.findViewById(R.id.tv_fecha_levantamiento);
        tvFechaCaptura = (TextInputEditText) view.findViewById(R.id.tv_fecha_captura);
        tvNombreVialidad = (TextInputEditText) view.findViewById(R.id.tv_nombre_vialidad);
        tvNumExt = (TextInputEditText) view.findViewById(R.id.tv_num_ext);
        tvNumInt = (TextInputEditText) view.findViewById(R.id.tv_num_int);
        tvNombreAsentamiento = (TextInputEditText) view.findViewById(R.id.tv_nombre_asentamiento);
        tvCp = (TextInputEditText) view.findViewById(R.id.tv_cp);
        tvLocalidad = (TextInputEditText) view.findViewById(R.id.tv_localidad);
        tvMunicipio = (TextInputEditText) view.findViewById(R.id.tv_municipio);
        tvClaveMunicipio = (TextInputEditText) view.findViewById(R.id.tv_clave_municipio);
        tvEstado = (TextInputEditText) view.findViewById(R.id.tv_estado);
        tvClaveEstado = (TextInputEditText) view.findViewById(R.id.tv_clave_estado);
        tvVialidades = (TextInputEditText) view.findViewById(R.id.tv_vialidades);
        tvDecripcion = (TextInputEditText) view.findViewById(R.id.tv_decripcion);
        tvTelefono = (TextInputEditText) view.findViewById(R.id.tv_telefono);

        tvFechaLevantamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // +1 because january is zero
                        final String selectedDate = day + " / " + (month+1) + " / " + year;
                        ((TextInputEditText)v).setText(selectedDate);
                    }
                });
                newFragment.show(((AppCompatActivity)getContext()).getSupportFragmentManager(),"datePicker");


            }
        });

        tvFechaCaptura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // +1 because january is zero
                        final String selectedDate = day + " / " + (month+1) + " / " + year;
                        ((TextInputEditText)v).setText(selectedDate);
                    }
                });
                newFragment.show(((AppCompatActivity)getContext()).getSupportFragmentManager(),"datePicker");


            }
        });

        spVialidades = (Spinner) view.findViewById(R.id.sp_vialidades);
        spAsentamientos = (Spinner) view.findViewById(R.id.sp_asentamientos);

        return view;
    }

    public void guardar()
    {
        try {


            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Nombre de la comunidad o programa",tvNombreComunidad.getText().toString());
            jsonBody.put("Grupo",tvGrupo.getText().toString() );
            jsonBody.put("Fecha de levantamiento", tvFechaLevantamiento.getText().toString());
            jsonBody.put("Fecha de captura",tvFechaCaptura.getText().toString() );
            jsonBody.put("Tipo de vialidad", spVialidades.getSelectedItem().toString() );
            jsonBody.put("Nombre de vialidad", tvNombreVialidad.getText().toString());
            jsonBody.put("Número exterior",tvNumExt.getText().toString());
            jsonBody.put("Número interior",tvNumInt.getText().toString() );
            jsonBody.put("Tipo de asentamiento",spAsentamientos.getSelectedItem().toString() );
            jsonBody.put("Nombre de asentamiento",tvNombreAsentamiento.getText().toString() );
            jsonBody.put("Código postal", tvCp.getText().toString());
            jsonBody.put("Localidad", tvLocalidad.getText().toString());
            jsonBody.put("Municipio", tvMunicipio.getText().toString());
            jsonBody.put("Clave de municipio", tvClaveMunicipio.getText().toString());
            jsonBody.put("Estado",tvEstado.getText().toString() );
            jsonBody.put("Clave de estado", tvClaveEstado.getText().toString());
            jsonBody.put("Entre vialidades",tvVialidades.getText().toString() );
            jsonBody.put(" Descripción de ubicación ", tvDecripcion.getText().toString());
            jsonBody.put("Teléfono / celular", tvTelefono.getText().toString());


            Utils.jsonEncuesta.put("Datos generales",jsonBody);
        }catch (JSONException ex)
        {}


    }

}