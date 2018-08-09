package project.jsht.mx.org.bamx.jshtablet.Utils;

import java.util.List;

import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSE;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEAlimentacion;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSECE;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEDG;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEEF;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEIV;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSERepresentante;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSESSS;
import project.jsht.mx.org.bamx.jshtablet.Encuestas.FragmentEstudioSEServicios;
import project.jsht.mx.org.bamx.jshtablet.Expandable.ExpandableItem;

/**
 * Created by PC on 20/06/2018.
 */

public class Constants {
    public static List<String> KEY_NAME;

    public static List<String> JSON_PARAMS;


    public static final String MSG_ERROR_WEB_SERVICES_RESPONSE = "Ocurrio un error de conexión. Favor de intentar nuevamente";
    public static final String MESSAGE_PROGRESS_DIALOG = "Espere un momento, obteniendo información";
    public static final String URL_BASE = "http://18.221.249.12:8080/";
    public static final String HEADER_BANCOS_DE_ALIMENTOS = "BANCOS DE ALIMENTOS";
    public static final String HEADER_CENTROS_COMUNITARIOS = "CENTROS COMUNITARIOS";
    public static final String HEADER_ENTRADAS = "ENTRADAS";
    public static final String HEADER_SALIDAS= "SALIDAS";
    public static final String HEADER_POR_ALMACENAR = "POR ALMACENAR";
    public static final String HEADER_ALMACENADOS = "ALMACENADOS";
    public static final String HEADER_CADUCADOS = "CADUCADOS";
    public static ExpandableItem infoData = null;


    public static FragmentEstudioSE fragmentEstudioSE= null;
    public static FragmentEstudioSEAlimentacion fragmentEstudioSEAlimentacion= null;
    public static FragmentEstudioSECE fragmentEstudioSECE = null;
    public static FragmentEstudioSEDG fragmentEstudioSEDG = null;
    public static FragmentEstudioSEEF fragmentEstudioSEEF = null;
    public static FragmentEstudioSEIV fragmentEstudioSEIV = null;
    public static FragmentEstudioSERepresentante fragmentEstudioSERepresentante = null;
    public static FragmentEstudioSEServicios fragmentEstudioSEServicios = null;
    public static FragmentEstudioSESSS fragmentEstudioSESSS = null;

}
