package project.jsht.mx.org.bamx.jshtablet.Expandable;

import org.json.JSONArray;

import java.util.ArrayList;

import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 19/07/2018.
 */

public class ExpandableDatos{
    public static ArrayList<ExpandableItem> Data()
    {
        JSONArray jsonArray = null;

        ArrayList<ExpandableItem> ExpandableItems = new ArrayList<>();

                    for (int i = 0; i <  Utils.integrantes.length ; i++) {
                        if (Utils.integrantes[i] != ""){
                        ExpandableItem current = new ExpandableItem();
                        current.nombre[i] = Utils.integrantes[i];
                        ExpandableItems.add(current);}
                    }


        return ExpandableItems;
    }
}