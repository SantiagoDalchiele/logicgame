package uy.com.uma.logicgame.persistencia.juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

import uy.com.uma.comun.util.UtilJSON;
import uy.com.uma.comun.util.UtilString;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores.Valor;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.UtilJuego;


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
	public static String construir (String idioma, Juego juego) throws PersistenciaException {
		Map<String, Object> props = new LinkedHashMap<String, Object>();
		Collection<JsonObject> dimsSup = new ArrayList<JsonObject>();
		Collection<JsonObject> dimsLat = new ArrayList<JsonObject>();
		Collection<String> pistas = new ArrayList<String>();
		List<Dimension> dims = juego.getDimensiones().getDimension();
		
		/** Dimensiones por columna (parte superior del tablero): de la 2° hasta la última */
		for (int i = 1; i < dims.size(); i++)
			addDimensionJSON(idioma, dims.get(i), dimsSup);	
		
		/** Dimensiones por fila (parte lateral del tablero): la 1° y de la nésima hasta la 3° */
		addDimensionJSON(idioma, dims.get(0), dimsLat);
		
		for (int i = (dims.size()-1); i >= 2; i--)
			addDimensionJSON(idioma, dims.get(i), dimsLat);
		
		for (PistaDelJuego pj : juego.getPistasDelJuego().getPistaDelJuego())
			pistas.add(reemplazarCaracteresEspeciales(UtilJuego.getTextoXIdioma(idioma, pj.getTexto())));
		
		props.put(TAG_TITULO, UtilJuego.getTextoXIdioma(idioma, juego.getTitulo()));
		props.put(TAG_TEXTO, UtilJuego.getTextoXIdioma(idioma, juego.getTitulo()));
		props.put(TAG_DIMS_SUPERIOR, dimsSup.toArray(new JsonObject [dimsSup.size()]));
		props.put(TAG_DIMS_LATERAL, dimsLat.toArray(new JsonObject [dimsLat.size()]));
		props.put(TAG_PISTAS, pistas.toArray(new String [pistas.size()]));
		String ret = UtilJSON.getJSONObject(props).toString();
		ret = UtilString.quitarUltimosCaracteres(ret.substring(1), 1);		// quita los {}
		ret = ret.replaceAll("\n", " ").replaceAll("\t", " ");
		ret = ret.replace("\\t", " ");
		
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
	private static void addDimensionJSON (String idioma, Dimension d, Collection<JsonObject> jab) throws PersistenciaException {
		Map<String, Object> props = new LinkedHashMap<String, Object>();
		Collection<String> valores = new ArrayList<String>();
		
		for (Valor v : d.getValores().getValor())
			valores.add(reemplazarCaracteresEspeciales(UtilJuego.getTextoXIdioma(idioma, v.getId())));
		
		props.put(TAG_ID, reemplazarCaracteresEspeciales(UtilJuego.getTextoXIdioma(idioma, d.getId())));
		props.put(TAG_VALORES, valores.toArray(new String [valores.size()]));
		jab.add(UtilJSON.getJSONObject(props));
	}
}
