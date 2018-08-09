package project.jsht.mx.org.bamx.jshtablet.NetWorking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import project.jsht.mx.org.bamx.jshtablet.Utils.Constants;
import project.jsht.mx.org.bamx.jshtablet.Utils.UserMessage;

/**
 * Created by PC on 20/06/2018.
 */

public class NetServicesJSONArray extends AsyncTask<String, Void, JSONArray> {
    private ProgressDialogMessage progressDialogMessage = null;
    private onTaskCompleted taskCompleted = null;
    private Context context;

    public NetServicesJSONArray(onTaskCompleted context) {
        this.taskCompleted = context;
        this.context = (Context)context;
    }

    protected JSONArray doInBackground(String... values) {
        JSONArray jsonArray = null;

        try {
            if (values[0] == "get") { //request get
                jsonArray = new JSONArray(new RequestGet().requestGet(values).toString());
            }
        } catch (JSONException ex) {
            Log.d("xxxxxxxx", ex.toString());
        }

        //este objeto lo cacha el método onPostExecute
        return jsonArray;
    }

    //se ejecuta una vez terminado el hilo de doInBackground....
    protected void onPostExecute(JSONArray jsonArray) {
        progressDialogMessage.dismissProgressDialog();

        if (jsonArray != null)
            taskCompleted.onTaskCompleted(jsonArray.toString());
        else
            new UserMessage().UserMessage((Context) taskCompleted, Constants.MSG_ERROR_WEB_SERVICES_RESPONSE);
    }

    protected void onPreExecute() {
        progressDialogMessage = new ProgressDialogMessage((Context) taskCompleted);
        progressDialogMessage.showProgressDialog(Constants.MESSAGE_PROGRESS_DIALOG);
        progressDialogMessage.progressDialog.setCanceledOnTouchOutside(false);
        progressDialogMessage.progressDialog.setCancelable(false);
    }
}
