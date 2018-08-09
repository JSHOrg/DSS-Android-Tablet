package project.jsht.mx.org.bamx.jshtablet.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by PC on 20/06/2018.
 */

public class UserMessage {

    //Método encargado de enviar los mensajes a los usuarios en la pantalla solicitada
    public void UserMessage(Context activity, String message) {
        Toast toast = Toast.makeText(activity, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
