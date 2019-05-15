package project.jsht.mx.org.bamx.jshtablet.Encuestas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import project.jsht.mx.org.bamx.jshtablet.Expandable.ExpandableEFAdapter;
import project.jsht.mx.org.bamx.jshtablet.Expandable.ExpandableDatos;
import project.jsht.mx.org.bamx.jshtablet.R;

/**
 * Created by PC on 17/07/2018.
 */

public class FragmentEstudioSEEF extends Fragment
{
    static RecyclerView lyRecycler;
    RecyclerView.LayoutManager mLayoutManager;
    static ExpandableEFAdapter expandableEFAdapter;
    View ly_add, view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            View view = inflater.inflate(R.layout.layout_estudio_se_ef, container, false);
            expandableEFAdapter = new ExpandableEFAdapter(getActivity(), ExpandableDatos.Data());

            mLayoutManager = new GridLayoutManager(this.getActivity(), 1);
            lyRecycler = (RecyclerView) view.findViewById(R.id.ly_recycler);
            lyRecycler.setLayoutManager(mLayoutManager);
            lyRecycler.setAdapter(expandableEFAdapter);
            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    //Toast.makeText(getActivity(), "on Move", Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    //Toast.makeText(getActivity(), "on Swiped ", Toast.LENGTH_SHORT).show();
                    //Remove swiped item from list and notify the RecyclerView
                    final int position = viewHolder.getAdapterPosition();
                    expandableEFAdapter.removeItem(position);

                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(lyRecycler);

            view.findViewById(R.id.ly_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandableEFAdapter.addItem();
                }
            });

            this.view = view;
        }
        return view;
    }
    public static void guardar()
    {
        for(int i = 0; i <lyRecycler.getAdapter().getItemCount();  i++ )
        {
            expandableEFAdapter.guardar((ExpandableEFAdapter.ViewHolder)lyRecycler.findViewHolderForAdapterPosition(i),i);
        }

    }

}