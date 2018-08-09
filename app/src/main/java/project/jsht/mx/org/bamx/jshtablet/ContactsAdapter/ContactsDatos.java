package project.jsht.mx.org.bamx.jshtablet.ContactsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.jsht.mx.org.bamx.jshtablet.Utils.ServiciosWeb;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 08/05/2018.
 */

public class ContactsDatos {

    public static ArrayList<ContactItem> Data(String response, ServiciosWeb.NombreServicioWeb servicioWeb)
    {
        JSONArray jsonArray = null;

        ArrayList<ContactItem> ContactsItems = new ArrayList<>();
        int[] ID = {1,2, 3,4,5, 6,7};

        switch (Utils.nombreServicioWebActual){
            case GetBancosAlimentos:
                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("bancoalimentos"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();

                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                        }

                        current.iniciales=s;

                        current.nombre = jsonArray.getJSONObject(i).getString("nombre");
                        current.telefono = jsonArray.getJSONObject(i).getString("telefono");
                        current.email = jsonArray.getJSONObject(i).getString("email");
                        current.razonSocial = jsonArray.getJSONObject(i).getString("razonSocial");
                        current.calificacion = jsonArray.getJSONObject(i).getInt("calificacion");
                        current.fechaRegistro = jsonArray.getJSONObject(i).getString("fechaRegistro");


                        current.cnombre = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("nombre");
                        current.capellido = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("apellido");
                        current.ctelefono = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("telefono");
                        current.ccelular = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("celular");
                        current.cemail = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("email");
                        current.cextension = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("extension");


                        current.dcalle = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("calle");
                        current.dnumero = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("numero");
                        current.dciudad = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("ciudad");
                        current.destado = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("estado");
                        current.dlongitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("longitud");
                        current.dlatitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("latitud");
                        current.dcolonia = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("colonia");
                        current.dcp = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("cp");

                        current.direccionCompleta = "C.P "+ current.dcp + " " + current.dciudad + ", "+ current.destado;
                        current.domicilioCompleto = current.dcalle + " No. " + current.dnumero + " Col. " + current.dcolonia;
                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case GetCentrosComunitarios:
                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("comunitarios"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();

                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                        }
                        current.iniciales=s;

                        current.nombre = jsonArray.getJSONObject(i).getString("nombre");
                        //current.telefono = jsonArray.getJSONObject(i).getString("telefono");
                        //current.email = jsonArray.getJSONObject(i).getString("email");
                        //current.razonSocial = jsonArray.getJSONObject(i).getString("razonSocial");
                        //current.calificacion = jsonArray.getJSONObject(i).getInt("calificacion");
                        current.fechaRegistro = jsonArray.getJSONObject(i).getString("fechaRegistro");



                        current.cnombre = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("nombre");
                        current.capellido = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("apellido");
                        current.ctelefono = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("telefono");
                        current.ccelular = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("celular");
                        current.cemail = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("email");
                        current.cextension = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("extension");

                        current.dcalle = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("calle");
                        current.dnumero = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("numero");
                        current.dciudad = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("ciudad");
                        current.destado = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("estado");
                        current.dlongitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("longitud");
                        current.dlatitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("latitud");
                        current.dcolonia = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("colonia");
                        current.dcp = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("cp");

                        current.direccionCompleta = "C.P "+ current.dcp + " " + current.dciudad + ", "+ current.destado;
                        current.domicilioCompleto = current.dcalle + " No. " + current.dnumero + " Col. " + current.dcolonia;

                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }


        return ContactsItems;
    }
}
