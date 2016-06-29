package uy.com.uma.logicgame.api.persistencia;

import java.io.IOException;
import java.io.InputStream;

import uy.com.uma.logicgame.api.LogicGameException;

/**
 * Define los métodos para la carga de recursos a la base de datos a partir de un archivo .zip con archivos dentro que pueden contener:
 * -idiomas: csv con 4 columnas (id, nombre, icono, valor_sin_utilizar)
 * -literales: csv con 3 columnas (idLiteral, idIdioma, texto)
 * -juegos: xml
 *
 * @author Santiago Dalchiele
 */
public interface ICargadorRecursos {

	/** Setea el input stream con el archivo .zip */
	void setInputStream (InputStream is) throws IOException;
	
	/** Setea el idioma */
	void setIdioma(String idioma);
	
	/** Carga en la base de datos los datos incluidos dentro del zip */
	void cargar() throws LogicGameException;
	
	/** Retorna unicamente los archivos procesados */
	String [] getArchivosProcesados();
}
