package project.jsht.mx.org.bamx.jshtablet.NetWorking;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import project.jsht.mx.org.bamx.jshtablet.Utils.Constants;
import project.jsht.mx.org.bamx.jshtablet.Utils.UserMessage;

/**
 * Created by PC on 20/06/2018.
 */

public class NetServicesJSONObject extends AsyncTask<String, Void, JSONObject> {
    private ProgressDialogMessage progressDialogMessage = null;
    private onTaskCompleted taskCompleted = null;
    private Activity context = null;
    private Boolean dialog = null;
    private String dialogText = null;


    public NetServicesJSONObject(onTaskCompleted taskContext, Activity context,
                                 Boolean dialog,String dialogText ) {
        this.taskCompleted = taskContext;
        this.context = context;
        this.dialog = dialog;
        this.dialogText = dialogText;
    }

    protected JSONObject doInBackground(String... values) {
        JSONObject jsonObject = null;

        try {
            if (values[0] == "post") //request post
                jsonObject = new JSONObject(new RequestPost().requestPost(values));

            if (values[0] == "get")  //request get
                jsonObject = new JSONObject(new RequestGet().requestGet(values));

            if (values[0] == "put") //request put
                jsonObject = new JSONObject(new RequestPut().requestPut(values));

            if (values[0] == "del") //request delete
                jsonObject = new JSONObject(new RequestDelete().requestPut(values));

        } catch (JSONException ex) {

        }

        //este objeto lo cacha el método onPostExecute
        return jsonObject;
    }

    //se ejecuta una vez terminado el hilo de doInBackground....
    protected void onPostExecute(JSONObject jsonObject) {
        if (dialog = true) {
            progressDialogMessage.dismissProgressDialog();
            progressDialogMessage = null;
        }
        if (jsonObject != null)
            taskCompleted.onTaskCompleted(jsonObject.toString());
        else {
            new UserMessage().UserMessage(context, Constants.MSG_ERROR_WEB_SERVICES_RESPONSE);
        }


    }

    protected void onPreExecute() {
        if (dialog = true) {
            progressDialogMessage = new ProgressDialogMessage(context);

            progressDialogMessage.showProgressDialog(dialogText);
            progressDialogMessage.progressDialog.setCanceledOnTouchOutside(false);
            progressDialogMessage.progressDialog.setCancelable(false);
        }
    }
}

