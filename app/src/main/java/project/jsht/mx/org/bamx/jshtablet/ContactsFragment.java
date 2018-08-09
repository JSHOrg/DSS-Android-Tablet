package project.jsht.mx.org.bamx.jshtablet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.jsht.mx.org.bamx.jshtablet.ContactsAdapter.ContactsAdapter;
import project.jsht.mx.org.bamx.jshtablet.Utils.Constants;
import project.jsht.mx.org.bamx.jshtablet.Utils.ServiciosWeb;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;


public class ContactsFragment extends Fragment {

    private ServiciosWeb.NombreServicioWeb servicioWeb;
    View container;
    RecyclerView lyRecycler;
    ContactsAdapter contactsAdapter;
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(), 1);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        this.container = container;
        lyRecycler = (RecyclerView) view.findViewById(R.id.ly_recycler);
        lyRecycler.setLayoutManager(mLayoutManager);
        //lyRecycler.setAdapter(contactsAdapter);
        // Inflate the layout for this fragment


        int currentPage = ((ViewPager)container).getCurrentItem();
        //int pos= ((ViewPager)container).getAdapter().getItemPosition(this.getId());
        Utils.currentTab =((ViewPager)container).getAdapter().getPageTitle(currentPage).toString();

        switch (Utils.opcionActual) {
            case Inicio:
                break;
            case Contactos:

                /*if(Utils.currentTab == Constants.HEADER_BANCOS_DE_ALIMENTOS)
                    new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetBancosAlimentos ,lyRecycler,contactsAdapter).GetBancosAlimentos();
                else if (Utils.currentTab == Constants.HEADER_CENTROS_COMUNITARIOS)
                    new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetCentrosComunitarios ,lyRecycler,contactsAdapter).GetCentrosComunitarios();


                break;*/
            case Mapa:

                if(Utils.currentTab == Constants.HEADER_BANCOS_DE_ALIMENTOS)
                    new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetBancosAlimentos ,lyRecycler,contactsAdapter).GetBancosAlimentos();
                else if (Utils.currentTab == Constants.HEADER_CENTROS_COMUNITARIOS)
                    new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetCentrosComunitarios ,lyRecycler,contactsAdapter).GetCentrosComunitarios();


                break;
            case Acopio:
            default:
                break;
        }
        return view;
    }

    MyReceiver r;
    public void refresh() {
        int currentPage = ((ViewPager)container).getCurrentItem();
        //int pos= ((ViewPager)container).getAdapter().getItemPosition(this.getId());
        Utils.currentTab =((ViewPager)container).getAdapter().getPageTitle(currentPage).toString();

        switch (Utils.opcionActual) {
            case Inicio:
                break;
            case Mapa:
            case Contactos:
                if(Utils.currentTab == Constants.HEADER_BANCOS_DE_ALIMENTOS)
                    new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetBancosAlimentos ,lyRecycler,contactsAdapter).GetBancosAlimentos();
                else if (Utils.currentTab == Constants.HEADER_CENTROS_COMUNITARIOS)
                    new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetCentrosComunitarios ,lyRecycler,contactsAdapter).GetCentrosComunitarios();
                break;

            case Acopio:
            default:
                break;
        }
        Log.i("Refresh", "YES");
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(r);
    }

    public void onResume() {
        super.onResume();
        r = new MyReceiver();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(r,
                new IntentFilter("TAG_REFRESH"));
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ContactsFragment.this.refresh();
        }
    }

}
