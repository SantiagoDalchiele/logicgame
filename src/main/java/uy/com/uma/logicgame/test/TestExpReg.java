package uy.com.uma.logicgame.test;

import java.security.KeyException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uy.com.uma.comun.util.EncriptadorString;
import uy.com.uma.logicgame.api.validacion.IConstantesValidacionParametros;
import uy.com.uma.logicgame.api.validacion.UtilValidacionParametros;

/**
 * Testea el uso de expresiones regulares
 *
 * @author Santiago Dalchiele
 */
public class TestExpReg {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EncriptadorString.encripta("a");
		} catch (KeyException e) {
			e.printStackTrace();
		}
		
		String cadena = "Da.vid Jli_091-456";
		Pattern pat = Pattern.compile(IConstantesValidacionParametros.PATRON_IDS);
	    Matcher mat = pat.matcher(cadena);	    
        System.out.println(mat.matches() ? "SI" : "NO");
        
        cadena = "1234567890123456789012345678901234567890123456789012345678901234";
        pat = Pattern.compile(IConstantesValidacionParametros.PATRON_IDS);
	    mat = pat.matcher(cadena);	    
        System.out.println(mat.matches() ? "SI" : "NO");
        
        cadena = "Dav.idJli_091-456123456789012345";
        pat = Pattern.compile(IConstantesValidacionParametros.PATRON_CLAVES);
	    mat = pat.matcher(cadena);	    
        System.out.println(mat.matches() ? "SI" : "NO");
        
        cadena = "d0172@dgi.gub.uy";    
        System.out.println(UtilValidacionParametros.esValidoCorreo(cadena) ? "SI" : "NO");        
        System.out.println(UtilValidacionParametros.esValidoCorreo(null) ? "SI" : "NO");
        
        cadena = "' OR 1=1 '";
        System.out.println(UtilValidacionParametros.esValidoCorreo(cadena) ? "SI" : "NO");        
        System.out.println(UtilValidacionParametros.esValidoIdIdioma("u") ? "SI" : "NO");
        
        System.out.println("Identificadores de matriz de juego");
        System.out.println(UtilValidacionParametros.esValidoIdMatrizJuego("1.1.3.3") ? "SI" : "NO");
        System.out.println(UtilValidacionParametros.esValidoIdMatrizJuego("103.1.3.3") ? "SI" : "NO");
        System.out.println(UtilValidacionParametros.esValidoIdMatrizJuego("99.99.99.99") ? "SI" : "NO");
        System.out.println(UtilValidacionParametros.esValidoIdMatrizJuego("1.1.3") ? "SI" : "NO");
        System.out.println(UtilValidacionParametros.esValidoIdMatrizJuego("1.1..3") ? "SI" : "NO");        
	}
}
