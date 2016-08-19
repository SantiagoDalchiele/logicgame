package uy.com.uma.logicgame.resolucion;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.comun.util.UtilString;
import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.conf.ConfiguracionLoadHelper;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.UtilJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.estrategias.IEstrategia;
import uy.com.uma.logicgame.resolucion.estrategias.PorAbsurdo;
import uy.com.uma.logicgame.resolucion.modelo.DatoCuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Implementa la l�gica de resoluci�n de un juego de l�gica por deducciones l�gicas o por el absurdo
 *
 * @author Santiago Dalchiele
 */
class RazonadorXDeducciones {
	
	private static final Logger log = LogManager.getLogger(RazonadorXDeducciones.class.getName());
	
	/** Matriz de decisi�n (modelo) */
	private MatrizDecision matriz;
	
	/** Estrategias para resolver el juego (l�gica) */
	private SortedSet<IEstrategia> estrategias;
	
	
	
	/**
	 * Arma la matriz de decisi�n a partir del juego, y asigna las pistas.
	 * Carga las distintas estrategias de resoluci�n de un archivo de configuraci�n
	 * @param juego Juego del archivo xml
	 * @throws ValidadorJuegoException excepci�n por definici�n inconsistente del juego
	 * @throws JAXBException excepci�n al cargar la configuraci�n
	 */
	public RazonadorXDeducciones (Juego juego) throws ValidadorJuegoException, ConfiguracionException {
		estrategias = ConfiguracionLoadHelper.getInstancia().cargarEstrategias();
		String idiomaXDefecto = UtilJuego.getIdiomaXDefecto(juego);
		new ValidadorJuego().validarJuego(juego);
		this.matriz = MatrizDecisionBuilder.construir(idiomaXDefecto, juego.getDimensiones());
		AsignadorPistas ap = new AsignadorPistas(this.matriz);
		ap.asignar (idiomaXDefecto, juego.getPistasDelJuego());
		log.debug("Luego de asignar las pistas iniciales ---------------------------------------------------------");
		log.debug(this.matriz);
	}

	
	
	/**
	 * Itera sobre las estrategias hasta que ninguna genera ning�n cambio en la matriz o el juego est� resuelto
	 * @return Costo de resoluci�n, -1 si no es resoluble por una contradicci�n o por estar incompleto
	 */
	public long resolver() {
		boolean hubieronCambios = true;
		long costo = 0;
		
		while (hubieronCambios && (!matriz.resueltoCompleto())) {
			hubieronCambios = false;
			
			for (Iterator<IEstrategia> it = estrategias.iterator(); it.hasNext() && !hubieronCambios;) {
				IEstrategia estrategia = it.next();
			
				try {
					Collection<DatoCuadroDecision> cambios = estrategia.aplicar(matriz);
					hubieronCambios = hubieronCambios || (!cambios.isEmpty());
					
					if (!cambios.isEmpty()) {
						costo += (cambios.size() * estrategia.getCosto());
						log.debug("Luego de aplicar estrategia " + estrategia.getClass().getSimpleName() + " ----------------------------------------------------------------");
						log.debug(this.matriz);
						log.debug("Costo actualizado: " + costo);
					}
				} catch (EstrategiaException e) {
					log.warn("Error al aplicar la estrategia " + estrategia.getClass().getSimpleName(), e);
					return -1;
				}				
			}
			
			if ((!hubieronCambios) && (!matriz.resueltoCompleto())) {
				IEstrategia absurdo = new PorAbsurdo();
				
				try {					
					Collection<DatoCuadroDecision> cambios = absurdo.aplicar(matriz);
					hubieronCambios = hubieronCambios || (!cambios.isEmpty());
					
					if (!cambios.isEmpty()) {
						costo += absurdo.getCosto();
						log.debug("Luego de aplicar estrategia " + absurdo.getClass().getSimpleName() + " ----------------------------------------------------------------");
						log.debug(this.matriz);
						log.debug("Costo actualizado: " + costo);
					}
				} catch (EstrategiaException e) {
					log.warn("Error al aplicar la estrategia " + absurdo.getClass().getSimpleName(), e);
					return -1;
				}				
			}
		}
		
		log.debug("Al finalizar la resoluci�n ----------------------------------------------------------------");
		log.debug(this.matriz);
		
		return matriz.resuelto() ? costo : -1;		
	}
	
	
	
	/**
	 * Retorna la soluci�n obtenida de la forma: fila_cuadro.col_cuadro.fila_celda.col_celda,afirma/niega;.....
	 * Se debe invocar primero al m�todo resolver de esta clase
	 */
	public String getSolucion() {
		String solucion = "";
		int dimMatriz = matriz.getDimensionesXFila().size();
		
		for (short i = 0; i < dimMatriz; i++)
			for (short j = 0; j < (dimMatriz -i); j++)
				for (short k = 0; k < matriz.getCantValores(); k++)
					for (short h = 0; h < matriz.getCantValores(); h++) {
						String id = i + "." + j + "." + k + "." + h;
						short valor = matriz.getValor(i, j, k, h);
						solucion += id + "," + valor + ";";
					}
						
		return UtilString.quitarUltimosCaracteres(solucion, 1);
	}
	
	
	public MatrizDecision getMatriz() {
		return this.matriz;
	}
}
