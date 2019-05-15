package project.jsht.mx.org.bamx.jshtablet.Expandable;

import android.app.Activity;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 31/07/2018.
 */

public class ExpandableSSSAdapter extends RecyclerView.Adapter<ExpandableSSSAdapter.ViewHolder> {
    private Activity context;
    private ArrayList<ExpandableItem> data;
    private LayoutInflater inflater;
    private int previousPosition = 0;

    public  ArrayList<ExpandableSSSAdapter.ViewHolder> mViewHolders = new ArrayList<>();




    public ExpandableSSSAdapter(Activity context, ArrayList<ExpandableItem> data)
    {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ExpandableSSSAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_estudio_se_sss_item, parent, false);
        ExpandableSSSAdapter.ViewHolder holder = new ExpandableSSSAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ExpandableSSSAdapter.ViewHolder myViewHolder, final int position) {



        final String[] estatus = new String[1];
        estatus[0] = "expand";
        ((TextView)myViewHolder.lyHeader.findViewById(R.id.tv_titulo)).setText("Integrante #" + String.valueOf(position+1) +": " + data.get(position).nombre[position].toString());
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
        mViewHolders.add(myViewHolder);

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

    public void guardar(final ExpandableSSSAdapter.ViewHolder myViewHolder, final int position) {
        try {

            int radioButtonJubilacionID = myViewHolder.rg_jubilacion.getCheckedRadioButtonId();
            View radioButtonJubilacion = myViewHolder.rg_jubilacion.findViewById(radioButtonJubilacionID);
            int idx = myViewHolder.rg_jubilacion.indexOfChild(radioButtonJubilacion);
            RadioButton rJubilacion = (RadioButton)  myViewHolder.rg_jubilacion.getChildAt(idx);
            String selectedtext = rJubilacion.getText().toString();


            JSONObject jsonPersona = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            JSONArray jsonArrayPrestaciones = new JSONArray();

            JSONObject jsonBody = new JSONObject();
            jsonBody.put(StringUtils.stripAccents("id"), String.valueOf(position));
            jsonBody.put(StringUtils.stripAccents("Ocupación"), myViewHolder.sp_ocupacion.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Tipo de empleo"), myViewHolder.sp_tipo_empleo.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Derechohabiencia"), myViewHolder.et_derecho.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Motivo derechohabiencia"), myViewHolder.sp_motivo.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Capacidades diferentes"), myViewHolder.et_capacidad.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Condiciones de salud"), myViewHolder.et_salud.getText().toString());

            jsonBody.put(StringUtils.stripAccents("Adicciones"), myViewHolder.sp_adicciones.getSelectedItem().toString());
            jsonBody.put(StringUtils.stripAccents("Etnia indígena"), myViewHolder.et_etnia.getText().toString());
            jsonBody.put(StringUtils.stripAccents("Peso"), myViewHolder.et_peso.getText());
            jsonBody.put(StringUtils.stripAccents("Talla"), myViewHolder.et_talla.getText());

            jsonBody.put("Jubilación o pensionado", selectedtext);

            if (myViewHolder.chk1.isChecked())
                jsonArrayPrestaciones.put(myViewHolder.chk1.getText().toString());
            if (myViewHolder.chk2.isChecked())
                jsonArrayPrestaciones.put(myViewHolder.chk2.getText().toString());
            if (myViewHolder.chk3.isChecked())
                jsonArrayPrestaciones.put(myViewHolder.chk3.getText().toString());
            if (myViewHolder.chk4.isChecked())
                jsonArrayPrestaciones.put(myViewHolder.chk4.getText().toString());
            if (myViewHolder.chk5.isChecked())
                jsonArrayPrestaciones.put(myViewHolder.chk5.getText().toString());
            if (myViewHolder.chk6.isChecked())
                jsonArrayPrestaciones.put(myViewHolder.chk6.getText().toString());
            if (myViewHolder.chk7.isChecked())
                jsonArrayPrestaciones.put(myViewHolder.chk7.getText().toString());
            if (myViewHolder.chk8.isChecked())
                jsonArrayPrestaciones.put(myViewHolder.chk8.getText().toString());
            
            jsonBody.put(StringUtils.stripAccents("Prestaciones"),jsonArrayPrestaciones);
            
            JSONArray jsonArraybusqueda;
            try {
                    jsonArraybusqueda = (Utils.jsonEncuesta).getJSONObject("Estructura familiar Detalles").getJSONArray("Integrantes");
                    jsonArraybusqueda.put(jsonBody);

            } catch (JSONException ex) {
                jsonPersona = new JSONObject();
                jsonArray = new JSONArray();
                jsonArray.put(jsonBody);
                jsonPersona.put("Integrantes", jsonArray);
                Utils.jsonEncuesta.put("Estructura familiar Detalles", jsonPersona);
            }


        } catch (JSONException ex) {
        }
    }

        @Override
    public int getItemCount() {
        return data.size();
    }

     public class ViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText et_derecho,et_capacidad,et_salud,et_etnia,et_peso,et_talla;
        RadioGroup rg_jubilacion;
        ImageView chk;
        LinearLayout lyHeader,lyContent;
        CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8;

        Spinner sp_ocupacion,sp_tipo_empleo,sp_motivo,sp_adicciones;
         public ViewHolder(View itemView) {
            super(itemView);

            lyContent = (LinearLayout) itemView.findViewById(R.id.ly_content);
            lyHeader = (LinearLayout) itemView.findViewById(R.id.ly_header);

            chk = (ImageView) itemView.findViewById(R.id.img_visible);


            sp_ocupacion = (Spinner) itemView.findViewById(R.id.sp_ocupacion);
            sp_tipo_empleo = (Spinner) itemView.findViewById(R.id.sp_tipo_empleo);
            et_derecho = (TextInputEditText) itemView.findViewById(R.id.et_derecho);
            sp_motivo = (Spinner) itemView.findViewById(R.id.sp_motivo);
            et_capacidad = (TextInputEditText) itemView.findViewById(R.id.et_capacidad);
            et_salud = (TextInputEditText) itemView.findViewById(R.id.et_salud);
            sp_adicciones = (Spinner) itemView.findViewById(R.id.sp_adicciones);

            et_etnia = (TextInputEditText) itemView.findViewById(R.id.et_etnia);
            et_peso = (TextInputEditText) itemView.findViewById(R.id.et_peso);
            et_talla = (TextInputEditText) itemView.findViewById(R.id.et_talla);


            rg_jubilacion = (RadioGroup) itemView.findViewById(R.id.rg_jubilacion);

            chk1 = (CheckBox) itemView.findViewById(R.id.chk_1);
            chk2 = (CheckBox) itemView.findViewById(R.id.chk_2);
            chk3 = (CheckBox) itemView.findViewById(R.id.chk_3);
            chk4 = (CheckBox) itemView.findViewById(R.id.chk_4);
            chk5 = (CheckBox) itemView.findViewById(R.id.chk_5);
            chk6 = (CheckBox) itemView.findViewById(R.id.chk_6);
            chk7 = (CheckBox) itemView.findViewById(R.id.chk_7);
            chk8 = (CheckBox) itemView.findViewById(R.id.chk_8);


             new Utils(context).mostrarCatalogo(sp_ocupacion,"Ocupacion");
             new Utils(context).mostrarCatalogo(sp_tipo_empleo,"TipoEmpleo");
             new Utils(context).mostrarCatalogo(sp_motivo,"MotivoDerechoHabiencia");
             new Utils(context).mostrarCatalogo(sp_adicciones,"Adicciones");

        }
    }

    public void removeItem(int position) {

        data.remove(data.get(position));
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());

    }

    // This method adds(duplicates) a Object (item ) to our Data set as well as Recycler View.
    public void addItem() {

        data.add( new ExpandableItem());
        notifyItemInserted(data.size() );
        notifyItemRangeChanged(data.size()-1,data.size() );
    }


}
