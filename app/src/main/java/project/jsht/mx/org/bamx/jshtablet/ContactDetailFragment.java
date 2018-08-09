package project.jsht.mx.org.bamx.jshtablet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ContactDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_nombreBanco = "nombreBanco";
    private static final String ARG_nombre = "nombre";
    private static final String ARG_banco = "banco";
    private static final String ARG_apellido = "apellido";
    private static final String ARG_telefono = "telefono";
    private static final String ARG_extension = "extension";
    private static final String ARG_celular = "celular";
    private static final String ARG_correo = "correo";
    private static final String ARG_localidad = "localidad";
    private static final String ARG_domicilio = "domicilio";
    private static final String ARG_iniciales = "iniciales";
    // TODO: Rename and change types of parameters

    private String nombreBanco;
    private String nombre;
    private String apellido;
    private String telefono;
    private String extension;
    private String celular;
    private String correo;
    private String localidad;
    private String domicilio;
    private String iniciales;
    private String banco;

    private TextView tvIniciales,tvNombreBanco,tvLocalidad,tvDomicilio,tvBanco,
            tvNombre,tvApellido,tvTelefono,tvExtension,tvCelular,tvCorreo;
    public ContactDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment ContactDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactDetailFragment newInstance(String nombreBanco, String nombre, String banco
            , String apellido, String telefono, String extension, String celular, String correo
            , String localidad, String domicilio, String iniciales) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_nombreBanco, nombreBanco);
        args.putString(ARG_nombre, nombre);
        args.putString(ARG_apellido, apellido);
        args.putString(ARG_telefono, telefono);
        args.putString(ARG_extension, extension);
        args.putString(ARG_celular, celular);
        args.putString(ARG_correo, correo);
        args.putString(ARG_localidad, localidad);
        args.putString(ARG_domicilio, domicilio);
        args.putString(ARG_iniciales, iniciales);
        args.putString(ARG_banco, banco);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombreBanco = getArguments().getString(ARG_nombreBanco);
            nombre = getArguments().getString(ARG_nombre);
             apellido = getArguments().getString(ARG_apellido);
             telefono = getArguments().getString(ARG_telefono);
             extension = getArguments().getString(ARG_extension);
             celular = getArguments().getString(ARG_celular);
             correo = getArguments().getString(ARG_correo);
             localidad = getArguments().getString(ARG_localidad);
             domicilio = getArguments().getString(ARG_domicilio);
            iniciales = getArguments().getString(ARG_iniciales);
            banco = getArguments().getString(ARG_banco);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_contact_info, container, false);
        tvIniciales = (TextView) view.findViewById(R.id.tv_iniciales);
        tvNombreBanco = (TextView) view.findViewById(R.id.tv_nombre_banco);
        tvLocalidad = (TextView) view.findViewById(R.id.tv_localidad);
        tvDomicilio = (TextView) view.findViewById(R.id.tv_domicilio);
        tvBanco = (TextView) view.findViewById(R.id.tv_banco);
        tvNombre = (TextView) view.findViewById(R.id.tv_nombre);
        tvApellido = (TextView) view.findViewById(R.id.tv_apellido);
        tvTelefono = (TextView) view.findViewById(R.id.tv_telefono);
        tvExtension = (TextView) view.findViewById(R.id.tv_extension);
        tvCelular = (TextView) view.findViewById(R.id.tv_celular);
        tvCorreo = (TextView) view.findViewById(R.id.tv_correo);

        tvIniciales.setText(iniciales);
        tvNombreBanco.setText(nombreBanco);
        tvLocalidad.setText(localidad);
        tvDomicilio.setText(domicilio);
        tvBanco.setText(banco);
        tvNombre.setText(nombre);
        tvApellido.setText(apellido);
        tvTelefono.setText(telefono);
        tvExtension.setText(extension);
        tvCelular.setText(celular);
        tvCorreo.setText(correo);

        return view;
    }


}
