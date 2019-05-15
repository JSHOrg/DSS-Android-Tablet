package project.jsht.mx.org.bamx.jshtablet.Expandable;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.DatePickerFragment;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 19/07/2018.
 */

public class ExpandableEFAdapter extends RecyclerView.Adapter<ExpandableEFAdapter.ViewHolder> {
    private Activity context;
    private ArrayList<ExpandableItem> data;
    private LayoutInflater inflater;
    private int previousPosition = 0;
    private ViewHolder myViewHolder;


    public ExpandableEFAdapter(Activity context, ArrayList<ExpandableItem> data)
    {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ExpandableEFAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_estudio_se_ef_item, parent, false);
        ExpandableEFAdapter.ViewHolder holder = new ExpandableEFAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ExpandableEFAdapter.ViewHolder myViewHolder, final int position) {

        this.myViewHolder = myViewHolder;

        myViewHolder.etNombre.setText("");
        myViewHolder.etApellidoP.setText("");
        myViewHolder.etApellidoM.setText("");
        myViewHolder.etFechaNacimiento.setText("");
        myViewHolder.etEntidad.setText("");
        myViewHolder.etCurp.setText("");
        myViewHolder.etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ((TextView)myViewHolder.lyHeader.findViewById(R.id.tv_titulo)).setText("Integrante #" + String.valueOf(position+1) +": " + s.toString());
                Utils.integrantes[position] = s.toString();
            }
        });

        final String[] estatus = new String[1];
        estatus[0] = "expand";
        ((TextView)myViewHolder.lyHeader.findViewById(R.id.tv_titulo)).setText("Integrante #" + String.valueOf(position+1) +":");
        myViewHolder.lyHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estatus[0] == "collapse" ) {
                    //collapse(myViewHolder.lyContent);
                    //myViewHolder.lyContent.setVisibility(View.GONE);
                    expandOrCollapse(myViewHolder.lyContent,"expand");
                    estatus[0] = "expand";
                    myViewHolder.chk.animate().rotation(180).start();
                }
                else if(estatus[0] == "expand" ){
                expandOrCollapse(myViewHolder.lyContent ,"collapse");
                estatus[0] = "collapse";
                    myViewHolder.chk.animate().rotation(0).start();}



            }
        });

        myViewHolder.etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // +1 because january is zero
                        final String selectedDate = (day<10? "0"+day : day) + " / " + ((month+1)<10 ? "0" + (month+1) :(month+1))  + " / " + year;
                        ((TextInputEditText)v).setText(selectedDate);
                    }
                });
                newFragment.show(((AppCompatActivity)context).getSupportFragmentManager(),"datePicker");


            }
        });

    }
    public void guardar(final ExpandableEFAdapter.ViewHolder myViewHolder, final int position)
    {
        try {

            JSONObject jsonPersona = new JSONObject();
            JSONArray jsonArray = new JSONArray();;

            JSONObject jsonBody = new JSONObject();
            jsonBody.put(StringUtils.stripAccents("id"), String.valueOf(position));
            jsonBody.put(StringUtils.stripAccents("Nombre(s)"), myViewHolder.etNombre.getText());
            jsonBody.put(StringUtils.stripAccents("Primer apellido"), myViewHolder.etApellidoP.getText());
            jsonBody.put(StringUtils.stripAccents("Segundo apellido"), myViewHolder.etApellidoM.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Fecha de nacimiento"), myViewHolder.etFechaNacimiento.getText().toString().replace(" ",""));
            jsonBody.put(StringUtils.stripAccents("Sexo"), myViewHolder.rbHombre.isChecked() ? myViewHolder.rbHombre.getText().toString() : myViewHolder.rbMujer.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Entidad de nacimiento"), myViewHolder.etEntidad.getText().toString());
            jsonBody.put(StringUtils.stripAccents("CURP"), myViewHolder.etCurp.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Estado civil"), myViewHolder.spEstadoCivil.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Parentesco"), myViewHolder.spParentesco.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Grado"), myViewHolder.spGrado.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Nivel"), myViewHolder.spNivel.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Asiste a la escuela"), myViewHolder.spAsisteEscuela.getSelectedItem().toString());

            JSONArray jsonArraybusqueda;
            try {

                    jsonArraybusqueda = (Utils.jsonEncuesta).getJSONObject("Estructura familiar").getJSONArray("Integrantes");
                    jsonArraybusqueda.put(jsonBody);

            }catch (JSONException ex)
            {
                jsonPersona = new JSONObject();
                jsonArray = new JSONArray();
                jsonArray.put(jsonBody);
                jsonPersona.put("Integrantes",jsonArray);
                Utils.jsonEncuesta.put("Estructura familiar",jsonPersona);
            }






        }catch (JSONException ex)
        {}

    }




    public void expandOrCollapse(final View v,String exp_or_colpse) {
        float height = -v.getHeight();
        TranslateAnimation anim = null;
        if(exp_or_colpse.equals("expand"))
        {
            anim = new TranslateAnimation(0.0f, 0.0f,height , 0.00f);
            v.setVisibility(View.VISIBLE);
        }
        else{
            anim = new TranslateAnimation(0.0f, 0.0f, 0.0f ,height );
            Animation.AnimationListener collapselistener= new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setVisibility(View.GONE);
                }
            };

            anim.setAnimationListener(collapselistener);
        }
        anim.setDuration(400);
        anim.setInterpolator(new AccelerateInterpolator(0.5f));
        v.startAnimation(anim);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText etNombre,etApellidoP,etApellidoM,
        etFechaNacimiento, etEntidad,etCurp;
        RadioButton rbHombre,rbMujer;
        Spinner spEstadoCivil,spParentesco,spGrado,spNivel,spAsisteEscuela;
        ImageView chk;
        LinearLayout lyHeader,lyContent;

        public ViewHolder(View itemView) {
            super(itemView);

            lyContent = (LinearLayout) itemView.findViewById(R.id.ly_content);
            lyHeader = (LinearLayout) itemView.findViewById(R.id.ly_header);

            chk = (ImageView) itemView.findViewById(R.id.img_visible);


            etNombre = (TextInputEditText) itemView.findViewById(R.id.et_nombre);
            etApellidoP = (TextInputEditText) itemView.findViewById(R.id.et_apellido_p);
            etApellidoM = (TextInputEditText) itemView.findViewById(R.id.et_apellido_m);
            etFechaNacimiento = (TextInputEditText) itemView.findViewById(R.id.et_fecha_nacimiento);
            etEntidad = (TextInputEditText) itemView.findViewById(R.id.et_entidad);
            etCurp = (TextInputEditText) itemView.findViewById(R.id.et_curp);

            rbHombre = (RadioButton) itemView.findViewById(R.id.rb_hombre);
            rbMujer = (RadioButton) itemView.findViewById(R.id.rb_mujer);

            spEstadoCivil = (Spinner) itemView.findViewById(R.id.sp_estado_civil);
            spParentesco = (Spinner) itemView.findViewById(R.id.sp_parentesco);
            spGrado = (Spinner) itemView.findViewById(R.id.sp_grado);
            spNivel = (Spinner) itemView.findViewById(R.id.sp_nivel);

            spAsisteEscuela = (Spinner) itemView.findViewById(R.id.sp_escuela);

            new Utils(context).mostrarCatalogo(spEstadoCivil,"EstadoCivil");
            new Utils(context).mostrarCatalogo(spGrado,"Escolaridad");

        }
    }

    public void removeItem(int position) {
        Utils.integrantes[position] = "";
        data.remove(data.get(position));
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());

    }

    // This method adds(duplicates) a Object (item ) to our Data set as well as Recycler View.
    public void addItem() {

        data.add( new ExpandableItem());
        Utils.integrantes[data.size()-1] = "";
        notifyItemInserted(data.size() );
        notifyItemRangeChanged(data.size()-1,data.size() );
    }



}
