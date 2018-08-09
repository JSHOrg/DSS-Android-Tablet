package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.jsht.mx.org.bamx.jshtablet.Expandable.ExpandableDatos;
import project.jsht.mx.org.bamx.jshtablet.Expandable.ExpandableEFAdapter;
import project.jsht.mx.org.bamx.jshtablet.Expandable.ExpandableSSSAdapter;
import project.jsht.mx.org.bamx.jshtablet.R;

/**
 * Created by PC on 17/07/2018.
 */

public class FragmentEstudioSESSS extends Fragment
{
    RecyclerView lyRecycler;
    RecyclerView.LayoutManager mLayoutManager;
    ExpandableSSSAdapter expandableSSSAdapter;
    View ly_add;
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_estudio_se_sss, container, false);

            expandableSSSAdapter = new ExpandableSSSAdapter(getActivity(), ExpandableDatos.Data());
            mLayoutManager = new GridLayoutManager(this.getActivity(), 1);
            lyRecycler = (RecyclerView) view.findViewById(R.id.ly_recycler);
            lyRecycler.setLayoutManager(mLayoutManager);
            lyRecycler.setAdapter(expandableSSSAdapter);

        return view;
    }

    public  void guardar()
    {
        for(int i = 0; i <lyRecycler.getAdapter().getItemCount();  i++ )
        {
            expandableSSSAdapter.guardar((ExpandableSSSAdapter.ViewHolder)lyRecycler.findViewHolderForAdapterPosition(i),i);
        }

    }

}