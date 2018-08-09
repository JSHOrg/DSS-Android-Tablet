package project.jsht.mx.org.bamx.jshtablet.Utils;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import project.jsht.mx.org.bamx.jshtablet.AcopioFragment;

/**
 * Created by PC on 02/08/2018.
 */

public class Dialog extends DialogFragment {

    public Dialog() {
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        return createSimpleDialog();
    }

    /**
     * Crea un diálogo de alerta sencillo
     * @return Nuevo diálogo
     */
    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Encuesta Terminada")
                .setMessage("Estas a punto de enviar las encuestas,no se podra modificar la informacion.\n ¿Deseas continuar?")
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AcopioFragment.seguir = true;
                                return;
                            }
                        })
                .setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        return builder.create();
    }
}