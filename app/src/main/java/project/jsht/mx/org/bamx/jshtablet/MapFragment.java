package project.jsht.mx.org.bamx.jshtablet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 30/04/2018.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Latitud = "latitud";
    private static final String ARG_Longitud = "longitud";
    private static final String ARG_Titulo = "titulo";

    // TODO: Rename and change types of parameters
    public static Double latitud;
    public static Double longitud;
    public static String titulo;
    MapView mapView;
    GoogleMap googleMap;
    private GoogleMap mMap;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance(Double latitud, Double longitud, String titulo) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_Latitud, latitud);
        args.putDouble(ARG_Longitud, longitud);
        args.putString(ARG_Titulo, titulo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            latitud = getArguments().getDouble(ARG_Latitud);
            longitud = getArguments().getDouble(ARG_Longitud);
            titulo = getArguments().getString(ARG_Titulo);

            Utils.latitud =latitud;
            Utils.longitud = longitud;
            Utils.tiulomap = titulo;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = (MapView) view.findViewById(R.id.map);
        MapsInitializer.initialize(getContext());
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
        return view;
    }


    public void setPoint()
    {
        LatLng location = new LatLng(latitud,longitud);
        mMap.addMarker(new MarkerOptions().position(location).title(titulo));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        this.googleMap = mMap;
        setPoint();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}