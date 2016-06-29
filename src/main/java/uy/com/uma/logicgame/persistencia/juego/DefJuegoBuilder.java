package uy.com.uma.logicgame.persistencia.juego;

import java.util.List;

import uy.com.uma.comun.util.UtilJSON;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;

/**
 * Construye la definición del juego para ser grabado en la tabla juegosxidioma.
 * Redundancia utilizada para retornar la definición del juego a la interface web.
 *
 * @author Santiago Dalchiele
 */
abstract class DefJuegoBuilder {
	
	/** Constantes con los identificadores de los tags */
	private static final String TAG_TITULO = "titulo";
	private static final String TAG_TEXTO = "texto";
	private static final String TAG_DIMS_SUPERIOR = "dimsSuperior";
	private static final String TAG_DIMS_LATERAL = "dimsLateral";
	private static final String TAG_PISTAS = "pistas";
	private static final String TAG_ID = "id";
	private static final String TAG_VALORES = "valores";
	
	/** Caracteres especiales */
	private static final String [] CARACTERES_ESPECIALES = {"&", "\"", "“", "”"};
	
	private static final String [] CARACTERES_ESPECIALES_XML = {"&amp;", "&quot;", "&quot;", "&quot;"};

	
	
	
	/**
	 * Construye la definición del juego en formato json para ser utilizado en la interface web
	 * @param juego objeto juego en jaxb
	 * @return String con el objeto json
	 */
	public static String construir(Juego juego) {
		StringBuffer def = new StringBuffer();
		def.append(UtilJSON.getPropJSON(TAG_TITULO) + UtilJSON.getValorJSON(juego.getTitulo()));
		def.append(UtilJSON.getPropJSON(TAG_TEXTO) + UtilJSON.getValorJSON(juego.getTexto()));
		def.append(UtilJSON.getPropJSON(TAG_DIMS_SUPERIOR) + "[");
		List<Dimension> dims = juego.getDimensiones().getDimension();
		
		/** Dimensiones por columna (parte superior del tablero): de la 2° hasta la última */
		for (int i = 1; i < dims.size(); i++)
			addDimensionJSON(dims.get(i), def, (i == (dims.size()-1)));
		
		def.append("]," + UtilJSON.getPropJSON(TAG_DIMS_LATERAL) + "[");
		
		/** Dimensiones por fila (parte lateral del tablero): la 1° y de la nésima hasta la 3° */
		addDimensionJSON(dims.get(0), def, false);
		
		for (int i = (dims.size()-1); i >= 2; i--)
			addDimensionJSON(dims.get(i), def, (i == 2));
		
		def.append("]," + UtilJSON.getPropJSON(TAG_PISTAS) + "[");
		
		for (PistaDelJuego pj : juego.getPistasDelJuego().getPistaDelJuego()) {
			String pista = reemplazarCaracteresEspeciales(pj.getTexto());
			def.append(UtilJSON.getValorJSON(pista));
		}
		
		def.deleteCharAt(def.length()-1);
		String ret = def.toString() + "]"; 
		ret = ret.replaceAll("\n", " ").replaceAll("\t", " ");
		
		while (ret.indexOf("  ") != -1)
			ret = ret.replaceAll("  ", " ");
		
		return ret;
	}
	
	
	
	
	/**
	 * Elimina caracteres que generen conflicto en el contenido
	 */
	private static String reemplazarCaracteresEspeciales (String s) {
		if (s == null)
			return s;
		
		for (int i = 0; i < CARACTERES_ESPECIALES.length; i++)
			s = s.replaceAll (CARACTERES_ESPECIALES[i],	CARACTERES_ESPECIALES_XML[i]);
		
		return s;
	}
	
	
	
	/**
	 * Agrega una dimension, su identificador y valores a la definición en JSON del juego
	 */
	private static void addDimensionJSON (Dimension d, StringBuffer def, boolean ultimo) {
		String id = reemplazarCaracteresEspeciales(d.getId());
		def.append("{" + UtilJSON.getPropJSON(TAG_ID) + UtilJSON.getValorJSON(id) + UtilJSON.getPropJSON(TAG_VALORES) + "[");
		
		for (String v : d.getValores().getValor()) {
			String value = reemplazarCaracteresEspeciales(v);
			def.append(UtilJSON.getValorJSON(value));
		}
		
		def.deleteCharAt(def.length()-1);
		def.append("]}" + (ultimo ? "" : ","));
	}
}
