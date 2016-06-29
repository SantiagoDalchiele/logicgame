package uy.com.uma.logicgame.persistencia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.comun.util.UtilFormato;
import uy.com.uma.comun.util.UtilIO;
import uy.com.uma.comun.util.UtilString;
import uy.com.uma.logicgame.api.LogicGameException;
import uy.com.uma.logicgame.api.bean.LiteralBean;
import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.persistencia.ICargadorRecursos;
import uy.com.uma.logicgame.api.persistencia.IManejadorAdminInternacionalizacion;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuego;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;

/**
 * Implementa la carga de recursos a la base de datos a partir de un archivo .zip con archivos dentro que pueden contener:
 * -idiomas: csv con 4 columnas (id, nombre, icono, valor_sin_utilizar)
 * -literales: csv con 3 columnas (idLiteral, idIdioma, texto)
 * -juegos: xml
 *
 * @author Santiago Dalchiele
 */
public class CargadorRecursos implements ICargadorRecursos {
	
	private static final Logger log = LogManager.getLogger(CargadorRecursos.class.getName());
	
	
	/** Constantes para las extensiones de los archivos aceptados */
	private static final String EXT_CSV = "csv";
	private static final String EXT_XML = "XML";
	
	/** Separador de datos en los archivos csv */
	private static final String SEP_CSV = ";";
	
	
	/** Idioma usado para persistir los juegos */
	private String idioma = Locale.getDefault().getLanguage();
	
	/** Archivos .csv con 2 columnas con datos de idioms */
	private Map<String, String> idiomas = new HashMap<String, String>();
	
	/** Archivos .csv con 3 columnas con datos de literales */
	private Map<String, String> literales = new HashMap<String, String>();
	
	/** Archivos .xml con juegos */
	private Map<String, String> juegos = new HashMap<String, String>();
	
	/** Nombre de los archivos procesados */
	private Collection<String> archivosProcesados = new ArrayList<String>();
	
	/** Manejadores de persistencia */
	private IManejadorAdminInternacionalizacion mai = null;
	private IManejadorJuego mj = null;

	
	
	/**
	 * Punto de entrada para ejecutar desde línea de comandos pasando como parámetro la ruta del archivo .zip
	 */
	public static void main(String[] args) {
		if ((args == null) || (args.length == 0) || (args[0] == null))
			usage();
		else {
			try {
				CargadorRecursos cr = new CargadorRecursos(new FileInputStream(args[0]));
				cr.cargar();			
				System.out.println("Archivos procesados:");

				for (String arch : cr.getArchivosProcesados())
					System.out.println(arch);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				SessionFactoryUtil.shutdown();
			}
		}
	}
	
	
	
	/**
	 * Muestra el uso de la aplicación
	 */
	private static void usage() {
		System.out.println("Usage:");
		System.out.println("CargadorRecursos [zip_file_path]");
	}

	
	
	/**
	 * Constructor por defecto
	 */
	public CargadorRecursos() {		
	}
	
	
	
	/**
	 * Carga en los mapeos los archivos incluidos en InputStream de un archivo .zip
	 * @param is InputStream de un archivo .zip
	 */
	public CargadorRecursos (InputStream is) throws IOException {
		setInputStream(is);
	}
	
	
	
	/**
	 * Carga en los mapeos los archivos incluidos en InputStream de un archivo .zip
	 * @param is InputStream de un archivo .zip
	 */
	public void setInputStream (InputStream is) throws IOException {
		ZipInputStream zis = null;
		
		try {
			zis = new ZipInputStream(is);		        
	        ZipEntry entry;
	        
            while((entry = zis.getNextEntry()) != null) {
            	Reader r = new BufferedReader (new InputStreamReader (zis/*, cs*/));
                final String contenido = UtilString.getTexto(r, false);
                final String nombre = entry.getName();
                
                if (!UtilFormato.esNulo(contenido)) {
	                if (EXT_XML.equalsIgnoreCase(UtilIO.extFile(nombre))) {	                	
	                	juegos.put(nombre, contenido);	                	
	                } else if (EXT_CSV.equalsIgnoreCase(UtilIO.extFile(nombre))) {	                	
	                	final BufferedReader br = new BufferedReader(new StringReader(contenido));
	                	final String [] tokensFirstLine = br.readLine().split(SEP_CSV);
	                	
	                	if (tokensFirstLine.length == 4)
	                		idiomas.put(nombre, contenido);
	                	else
	                		literales.put(nombre, contenido);
	                }
                }
            }	
		} finally {
			UtilIO.closeInputStream(zis);
			UtilIO.closeInputStream(is);
		}
	}
	
	
	
	/**
	 * Retorna unicamente los archivos procesados
	 */
	public String [] getArchivosProcesados() {
		return archivosProcesados.toArray(new String[]{});
	}
	
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}



	/**
	 * Carga en la base de datos los datos incluidos dentro del zip
	 */
	public void cargar() throws LogicGameException {
		try {
			mai = PersistenciaFactory.getInstancia().getManejadorAdminInternacionalizacion();
			mj = PersistenciaFactory.getInstancia().getManejadorJuego();
			archivosProcesados.clear();
			cargarIdiomas();
			cargarLiterales();
			cargarJuegos();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | ConfiguracionException | IOException | 
				PersistenciaException | JAXBException e) {
			throw new LogicGameException("Error al cargar los recursos en la base de datos", e);
		} finally {
			SessionFactoryUtil.shutdown();
		}
	}
	

	
	/**
	 * Carga los idiomas
	 */
	private void cargarIdiomas() throws PersistenciaException, IOException {
		for (final String arch : idiomas.keySet()) {
			final String contenido = idiomas.get(arch);
			BufferedReader br = new BufferedReader(new StringReader(contenido));
			String line;
			log.info("Procesando el archivo: " + arch);
			
			while (br.ready() && ((line = br.readLine()) != null)) {
				final String [] datos = line.split(SEP_CSV);
				mai.setIdioma(datos[0], datos[1], datos[2]);
			}
			
			log.info(arch + " procesado OK");
			archivosProcesados.add(arch);
		}		
	}
	

	
	/**
	 * Carga todos los literales en una única transacción por archivo
	 */
	private void cargarLiterales() throws PersistenciaException, IOException, NumberFormatException {
		for (final String arch : literales.keySet()) {
			final String contenido = literales.get(arch);
			BufferedReader br = new BufferedReader(new StringReader(contenido));
			String line;
			Collection<LiteralBean> literales = new ArrayList<LiteralBean>();
			log.info("Procesando el archivo: " + arch);
			
			while (br.ready() && ((line = br.readLine()) != null)) {
				final String [] datos = line.split(SEP_CSV);
				literales.add(new LiteralBean(Long.parseLong(datos[0]), datos[1], datos[2]));
			}			
			
			mai.setLiterales(literales);
			log.info(arch + " procesado OK");
			archivosProcesados.add(arch);
		}
	}
	

	
	/**
	 * Carga uno a uno los juegos
	 */
	private void cargarJuegos() throws JAXBException, PersistenciaException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);
    	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		for (final String arch : juegos.keySet()) {
			final String contenido = juegos.get(arch);
			Juego juego = (Juego) jaxbUnmarshaller.unmarshal(new StringReader(contenido));
			
			if (mj.existe(juego.getId().intValue())) {
				log.warn("Borrando la definición anterior del juego " + juego.getId());
				mj.borrar(juego.getId().intValue());
			}
			
			mj.persistir(juego, idioma);
			mj.actualizarRedundancias(juego.getId().intValue());
			log.info(arch + " procesado OK");
			archivosProcesados.add(arch);
		}
	}
}
