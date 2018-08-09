package project.jsht.mx.org.bamx.jshtablet.ContactsAdapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import project.jsht.mx.org.bamx.jshtablet.ContactDetailFragment;
import project.jsht.mx.org.bamx.jshtablet.MapFragment;
import project.jsht.mx.org.bamx.jshtablet.R;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;


/**
 * Created by PC on 08/05/2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private Activity context;
    private ArrayList<ContactItem> data;
    private LayoutInflater inflater;
    private int previousPosition = 0;

    public  ContactsAdapter(Activity context, ArrayList<ContactItem> data)
    {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.contacts_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder myViewHolder, final int position) {

        final MapFragment[] mapFragment = new MapFragment[1];
        final ContactDetailFragment[] contactDetailFragment = new ContactDetailFragment[1];

        myViewHolder.tvColonia.setText(data.get(position).nombre);
        myViewHolder.tvDomicilio.setText(data.get(position).direccionCompleta);
        myViewHolder.tvBeneficiarios.setText(data.get(position).domicilioCompleto);

        myViewHolder.tvIniciales.setText(data.get(position).iniciales);

        previousPosition = position;
        myViewHolder.cvContenedor.setFocusableInTouchMode(true);
        myViewHolder.cvContenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
            }
        });
        myViewHolder.cvContenedor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.isFocused()) {
                    myViewHolder.tvColonia.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    myViewHolder.tvIniciales.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_green));
                    myViewHolder.chk.setChecked(true);

                    switch (Utils.opcionActual)
                    {
                        case Contactos:

                             contactDetailFragment[0] =
                                    ContactDetailFragment.newInstance(data.get(position).nombre,
                                            data.get(position).cnombre,data.get(position).nombre,data.get(position).capellido
                                    ,data.get(position).ctelefono,data.get(position).cextension,data.get(position).ccelular
                                    ,data.get(position).cemail ,data.get(position).direccionCompleta,data.get(position).domicilioCompleto
                                    ,myViewHolder.tvIniciales.getText().toString());

                            for (Fragment fragment: Utils.fragmentTransaction.getFragments()) {
                                if (fragment == contactDetailFragment[0] && fragment == mapFragment[0]) {
                                    Utils.fragmentTransaction.beginTransaction().remove(fragment).commit();
                                }
                            }


                            if (Utils.fragmentTransaction != null)
                                Utils.fragmentTransaction.beginTransaction().add(R.id.lyMain2, contactDetailFragment[0]).commit();
                            break;

                        case Mapa:

                            mapFragment[0] = MapFragment.newInstance(Double.valueOf(data.get(position).dlatitud)
                                    , Double.valueOf(data.get(position).dlongitud)
                            ,data.get(position).iniciales);

                            for (Fragment fragment: Utils.fragmentTransaction.getFragments()) {
                                if (fragment == contactDetailFragment[0] && fragment == mapFragment[0]) {
                                    Utils.fragmentTransaction.beginTransaction().remove(fragment).commit();
                                }
                            }
                            if (Utils.fragmentTransaction != null)
                                Utils.fragmentTransaction.beginTransaction().add(R.id.lyMain2, mapFragment[0]).commit();

                            break;
                    }
                }
                else{
                    myViewHolder.tvColonia.setTextColor(context.getResources().getColor(R.color.cardview_dark_background));
                    myViewHolder.tvIniciales.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner));
                    myViewHolder.chk.setChecked(false);}
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvColonia, tvDomicilio, tvIniciales,tvBeneficiarios;
        CheckBox chk;
        CardView cvContenedor;


        public ViewHolder(View itemView) {
            super(itemView);

            tvColonia = (TextView) itemView.findViewById(R.id.tv_colonia);
            tvDomicilio = (TextView) itemView.findViewById(R.id.tv_domicilio);
            tvIniciales = (TextView) itemView.findViewById(R.id.tv_iniciales);
            tvBeneficiarios = (TextView) itemView.findViewById(R.id.tv_beneficiarios);

            cvContenedor = (CardView) itemView.findViewById(R.id.cvContenedor);

            chk = (CheckBox) itemView.findViewById(R.id.chk);
        }
    }

    private void removeItem(ContactItem infoData) {

        int currPosition = data.indexOf(infoData);
        data.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

    // This method adds(duplicates) a Object (item ) to our Data set as well as Recycler View.
    private void addItem(int position, ContactItem infoData) {

        data.add(position, infoData);
        notifyItemInserted(position);
    }


}
