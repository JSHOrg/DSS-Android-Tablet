package project.jsht.mx.org.bamx.jshtablet.NetWorking;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.jsht.mx.org.bamx.jshtablet.Utils.Constants;

/**
 * Created by PC on 20/06/2018.
 */

public class RequestGet {
    private URL url = null;
    private HttpURLConnection httpURLConnection = null;
    private InputStream inputStream = null;


    String result = "";


    public String requestGet(String[] values) {
        String urlTemp;
        String setGet = "";
        String TOKEN;
        try {
            for (int index = 2; index <= values.length - 1; index++) {
                setGet += "?" + Constants.KEY_NAME.get(index - 2) + "=" + values[index].toString();
            }
            url = new URL(Constants.URL_BASE.concat(values[1] + setGet));
            urlTemp = url.toString();
            urlTemp = urlTemp.replaceAll(" ", "%20");
            url = new URL(urlTemp);
            Log.i("url", url.toString());

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if ( SetHeaderes.TokenServicios != null) {
                new SetHeaderes().setToken(httpURLConnection);
            }
           // httpURLConnection.setRequestProperty("Content-Type", "application/json");

            if ( SetHeaderes.cookieManager != null)
                new SetHeaderes().setCookie(httpURLConnection);


            httpURLConnection.setDoOutput(false);



            if (httpURLConnection.getResponseCode() != 200) //Por alguna razon el response nos da un error
                inputStream = httpURLConnection.getErrorStream();
            else if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                if ( SetHeaderes.cookieManager == null)
                    new SetHeaderes().getCookie(httpURLConnection);
            }
            //Constants.RESPONSE_CODE = httpURLConnection.getResponseCode();
            result = convertStreamToString(inputStream);

            Log.i("resultado", result.toString());
            try {
                for (String header : httpURLConnection.getHeaderFields().keySet()) {
                    if (header != null) {
                        for (String value : httpURLConnection.getHeaderFields().get(header)) {
                            System.out.println(header + ":" + value);
                            Log.i("RequestHeaders",value);
                        }
                    }
                }
            }catch (Exception ex)
            {            Log.i("error", ex.toString());
            }

            inputStream.close();
        } catch (Exception ex) {
            Log.d("error", ex.toString());
        } finally {
            httpURLConnection.disconnect();
        }
        return result;
    }


    public String requestGet(String value) {
        String urlTemp;
        String setGet = "";
        String TOKEN;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            //for (int index = 2; index <= values.length - 1; index++) {
            //    setGet += "?" + Constants.KEY_NAME.get(index - 2) + "=" + values[index].toString();
            //}
            url = new URL(Constants.URL_BASE.concat(value));
            urlTemp = url.toString();
            urlTemp = urlTemp.replaceAll(" ", "%20");
            url = new URL(urlTemp);
            Log.i("url", url.toString());

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            //if ( SetHeaderes.TokenServicios != null) {
            //    new SetHeaderes().setToken(httpURLConnection);
            //}
            // httpURLConnection.setRequestProperty("Content-Type", "application/json");

            //if ( SetHeaderes.cookieManager != null)
            //    new SetHeaderes().setCookie(httpURLConnection);


            httpURLConnection.setDoOutput(false);



            if (httpURLConnection.getResponseCode() != 200) //Por alguna razon el response nos da un error
                inputStream = httpURLConnection.getErrorStream();
            else if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                if ( SetHeaderes.cookieManager == null)
                    new SetHeaderes().getCookie(httpURLConnection);
            }
            //Constants.RESPONSE_CODE = httpURLConnection.getResponseCode();
            result = convertStreamToString(inputStream);

            Log.i("resultado", result.toString());

            inputStream.close();
        } catch (Exception ex) {
            Log.d("error", ex.toString());
        } finally {
            if (httpURLConnection != null)
            httpURLConnection.disconnect();
        }
        return result;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("respuesta", sb.toString());
        return sb.toString();
    }
}
