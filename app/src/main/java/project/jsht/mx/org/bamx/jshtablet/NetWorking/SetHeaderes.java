package project.jsht.mx.org.bamx.jshtablet.NetWorking;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import project.jsht.mx.org.bamx.jshtablet.Utils.ServiciosWeb;
import project.jsht.mx.org.bamx.jshtablet.Utils.Utils;

/**
 * Created by PC on 20/06/2018.
 */

public class SetHeaderes {

    public static CookieManager cookieManager = null;
    public static String TokenServicios = null;

    static final String COOKIES_HEADER = "Set-Cookie";
    List<HttpCookie> cookies = null;
    HttpCookie cookie = null;


    public void getCookie(HttpURLConnection httpconn) {
        cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        Map<String, List<String>> headerFields = httpconn.getHeaderFields();
        List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);
        if (cookiesHeader != null) {
            for (String cookie : cookiesHeader) {
                cookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
            }
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_NONE);
        }
    }

    public void setCookie(HttpURLConnection httpconn) {

        if (cookieManager.getCookieStore().getCookies().size() > 0) {

            cookies = cookieManager.getCookieStore().getCookies();

            if (cookies != null) {
                cookie = cookies.get(0);
                httpconn.setRequestProperty("Cookie", cookie.getName() + "=" + cookie.getValue());
            }
        }

    }

    public void setToken(HttpURLConnection httpconn) {

        if (TokenServicios != null)
            httpconn.setRequestProperty("Authorization","Bearer " + TokenServicios);
    }

    public void setLoginHeader(HttpURLConnection httpconn)
    {
        if (Utils.nombreServicioWebActual == ServiciosWeb.NombreServicioWeb.Login) {
            //httpconn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //httpconn.setRequestProperty("Authorization", "Basic dXNlcjpwYXNzd29yZA==");
            //httpconn.setRequestProperty("Accept", "application/json");

            httpconn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpconn.setRequestProperty("Authorization", "Basic dXNlcjpwYXNzd29yZA==");
            httpconn.setRequestProperty("Accept", "application/json");
            httpconn.setRequestProperty("cache-control", "no-cache");
            httpconn.setRequestProperty("postman-token", "3a1882d6-1c6c-b7af-0ef5-b6784a9d0bf7");

        }
    }

    public void Header(HttpURLConnection httpconn)
    {
        httpconn.setRequestProperty("Content-Type", "application/json");

    }
}