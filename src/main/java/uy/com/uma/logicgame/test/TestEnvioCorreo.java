package uy.com.uma.logicgame.test;

import javax.mail.MessagingException;

import uy.com.uma.comun.mail.MailParams;
import uy.com.uma.comun.mail.UtilMail;

/**
 * Testea el envio de correos electrónicos
 * 
 * @author Santiago Dalchiele
 */
public class TestEnvioCorreo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MailParams parms = new MailParams("correo", "d0172", "nidO.978", "stgodal@dgi.gub.uy", "santiagodalchiele@gmail.com", "Prueba", "Mail enviado exitosamente!");
			parms.setTimeout(60000);
			UtilMail.enviar(parms);
			
			parms.setTexto(null);
			parms.setContenidoHTML("<h1>El mensaje de nuestro primer correo HTML</h1>");
			UtilMail.enviar(parms);
			
			parms.addCc("stgodal@dgi.gub.uy");
			parms.addBcc("d0172@dgi.gub.uy");
			parms.getAdjuntos().add("c:/temp/logicgame.log");
			parms.getAdjuntos().add("c:/temp/nuevojuego10.xml");
			UtilMail.enviar(parms);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
