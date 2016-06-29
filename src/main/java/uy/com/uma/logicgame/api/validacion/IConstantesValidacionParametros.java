package uy.com.uma.logicgame.api.validacion;

/**
 * Constantes utilizadas para "sanitizar" datos que se pasan por parámetro al back-end
 *
 * @author Santiago Dalchiele
 */
public interface IConstantesValidacionParametros {

	
	/** Patrones para validación de datos */
	public static final String PATRON_IDIOMAS = "^[a-zA-Z]{1,2}$";
	public static final String PATRON_IDS = "^[a-zA-Z0-9]{1,1}[_a-zA-Z0-9- \\.]{0,63}$";
	public static final String PATRON_CLAVES = "^[_a-zA-Z0-9-\\.]{5,32}$";
	public static final String PATRON_EMAILS = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,4})+$";
	public static final String PATRON_IDS_MATRIZ_JUEGO = "^(\\d{1,2}\\.){3}\\d{1,2}$";

	
	/** Largos máximos segun tipo de dato */
	public static final int LARGO_MAX_EMAILS = 128;
}
