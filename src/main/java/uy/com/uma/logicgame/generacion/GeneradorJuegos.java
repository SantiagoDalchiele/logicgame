package uy.com.uma.logicgame.generacion;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores.Valor;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista;
import uy.com.uma.logicgame.nucleo.jaxb.juego.UtilJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.Resolucion;


/**
 * Genera juegos de forma aleatoria en base a ciertos parámetros
 *
 * @author Santiago Dalchiele
 */
public class GeneradorJuegos {
	
	/** Literal de juego auto-generado */
	public static final String JUEGO_AUTO_GEN = "juego auto-generado";
	
	/** Idioma por defecto */
	private static String idiomaXDefecto = Locale.getDefault().getLanguage();
	
	
	
	/** Parametros a los efectos de la generación */
	private ParametrosGeneracionJuego param;
	
	/** Juego a retornar */
	private Juego juego;
	
	/** Hora que arranca el proceso */
	private long timeInicio;	
	

	
	
	/**
	 * Constructor, setea los parametros para la generación
	 */
	public GeneradorJuegos (ParametrosGeneracionJuego param) {
		this.param = param;
		initJuego();
	}
	
	
	
	
	/**
	 * Según los parametros de generación, genera pistas al azar hasta que:
	 * -Se termine el tiempo máximo de ejecución
	 * -El juego tenga solución, sea única y el costo esté dentro de los parámetros asignados
	 * 
	 * @return juego de lógica construido
	 */
	public Juego generar() throws ValidadorJuegoException, ConfiguracionException {
		timeInicio = System.currentTimeMillis();
		Stack<PistaDelJuego> pila = new Stack<>();
		boolean timeoutOK = true;
		boolean juegoValido = false;
		int maxPistas = 2 * param.getCantDimensiones() * param.getCantValores();
		
		while ((timeoutOK) && (!juegoValido)) {			
			PistaDelJuego pista = pistaAlAzar();			
			while (contiene(pila, pista)) pista = pistaAlAzar();			
			pila.push(pista);
			juego.setPistasDelJuego(col2Pistas(pila));			
			Resolucion resol = Resolucion.resolver(juego);
			
			if (resol.tieneSolucion() && resol.solucionUnica()) {
				long costo = resol.getCosto();
				System.out.print(costo);								
				
				if ((costo > param.getCostoMin()) && (costo < param.getCostoMax())) {
					new OptimizadorPistas().optimizar(juego);
					resol = Resolucion.resolver(juego);
					costo = resol.getCosto();
					
					if ((costo > param.getCostoMin()) && (costo < param.getCostoMax())) {
						juego.setCosto(BigInteger.valueOf(costo));					
						juego.setPistasDelJuego(agrupar(juego.getPistasDelJuego().getPistaDelJuego()));
						juegoValido = true;
					} else
						pila.pop();
				} else if (costo > param.getCostoMax())
					pila.pop();				
			}
			
			if ((!juegoValido) && (pila.size() == maxPistas)) {
				pila.clear();
				System.out.println(".");
			}
			
			timeoutOK = ((System.currentTimeMillis() - timeInicio) / 1000) < param.getTimeout();
			System.out.print(".");
		}
		
		System.out.println(".");		
		return juego;
	}
	
	
	
	/**
	 * Inicializa el juego a retornar
	 */
	private void initJuego() {
		juego = new Juego();
		juego.setTitulo(UtilJuego.getLiteral(idiomaXDefecto, JUEGO_AUTO_GEN));
		juego.setTexto(UtilJuego.getLiteral(idiomaXDefecto, JUEGO_AUTO_GEN));
		juego.setDimensiones(new Dimensiones());
		juego.setPistasDelJuego(new PistasDelJuego());
		juego.setCosto(BigInteger.valueOf(-1));
		
		for (int i = 1; i <= param.getCantDimensiones(); i++) {
			String id = "dime" + (i < 10 ? "0" : "") + i;
			String prefVal = "dim" + (i < 10 ? "0" : "") + i + "_val";
			Dimension d = new Dimension();
			d.setId(UtilJuego.getLiteral(idiomaXDefecto, id));			
			d.setValores(new Valores());
			
			for (short j = 1; j <= param.getCantValores(); j++) {
				String idV = prefVal + (j < 10 ? "0" : "") + j;
				Valor v = new Valor();
				v.setNro(j);
				v.setId(UtilJuego.getLiteral(idiomaXDefecto, idV));
				d.getValores().getValor().add(v);
			}
			
			juego.getDimensiones().getDimension().add(d);			
		}
	}
	
	
	
	/**
	 * Retorna una pista elegida al azar
	 */
	private PistaDelJuego pistaAlAzar() {
		PistaDelJuego ret = new PistaDelJuego();
		ret.setPistas(new Pistas());
		ret.setTexto(UtilJuego.getLiteral(idiomaXDefecto, JUEGO_AUTO_GEN));
		Pista p = new Pista();
		Random azar = new Random();
		int indDim1 = azar.nextInt(param.getCantDimensiones());
		int indDim2 = azar.nextInt(param.getCantDimensiones());
		short indVal1 = (short) (1 + azar.nextInt(param.getCantValores()));
		short indVal2 = (short) (1 + azar.nextInt(param.getCantValores()));
		int valorAfirma = azar.nextInt(100);
		
		while (indDim1 == indDim2)
			indDim2 = azar.nextInt(param.getCantDimensiones());
		
		Dimension d1 = juego.getDimensiones().getDimension().get(indDim1);
		Dimension d2 = juego.getDimensiones().getDimension().get(indDim2);
		p.setDimension1(d1.getNro());
		p.setDimension2(d2.getNro());
		p.setValor1(indVal1);
		p.setValor2(indVal2);
		p.setAfirmaNiega(valorAfirma <= param.getPorcAfirma());		
		ret.getPistas().getPista().add(p);
		return ret;
	}
	
	
	
	
	/**
	 * De una colección de PistaDelJuego retorna el objeto PistasDelJuego
	 */
	private static PistasDelJuego col2Pistas (Collection<PistaDelJuego> pistas) {
		PistasDelJuego ret = new PistasDelJuego();
		ret.getPistaDelJuego().addAll(pistas);		
		return ret;
	}
	
	
	
	/**
	 * Retorna TRUE si la colección ya contiene la pista
	 */
	private static boolean contiene (Collection<PistaDelJuego> pistas, PistaDelJuego pista) {
		Pista p = pista.getPistas().getPista().get(0);
		
		for (PistaDelJuego pj : pistas) {
			Pista h = pj.getPistas().getPista().get(0);
			boolean igualX1 = (p.getDimension1() == h.getDimension1()) && (p.getValor1() == h.getValor1());
			boolean igualX2 = (p.getDimension2() == h.getDimension2()) && (p.getValor2() == h.getValor2());
			boolean igualInv1 = (p.getDimension1() == h.getDimension2()) && (p.getValor1() == h.getValor2());
			boolean igualInv2 = (p.getDimension2() == h.getDimension1()) && (p.getValor2() == h.getValor1());
			
			if ((igualX1 && igualX2) || (igualInv1 && igualInv2))
				return true;
		}
		
		return false;
	}
	
	
	
	/**
	 * Agrupa las pistas que tienen las mismas dimensiones
	 */
	private static PistasDelJuego agrupar (Collection<PistaDelJuego> pistas) throws ValidadorJuegoException {
		PistasDelJuego ret = new PistasDelJuego();
		Map<String,DimensionYPistas> mapeo = new HashMap<String, DimensionYPistas>();
		
		for (PistaDelJuego pj : pistas) {
			Pista p = pj.getPistas().getPista().get(0);
			DimensionYPistas dim;
			String key1 = DimensionYPistas.key(p.getDimension1(), p.getValor1());
			String key2 = DimensionYPistas.key(p.getDimension2(), p.getValor2());
			
			if (mapeo.containsKey(key1))
				dim = mapeo.get(key1);
			else if (mapeo.containsKey(key2))
				dim = mapeo.get(key2);
			else {
				dim = new DimensionYPistas(key1);				
				mapeo.put(dim.getId(), dim);
			}
			
			dim.addPista(p);
		}
		
		for (DimensionYPistas dim : mapeo.values()) {
			PistaDelJuego pj = new PistaDelJuego();
			pj.setTexto(UtilJuego.getLiteral(idiomaXDefecto, JUEGO_AUTO_GEN));
			pj.setPistas(dim.getPistas());
			ret.getPistaDelJuego().add(pj);
		}
		
		return ret;
	}
}
