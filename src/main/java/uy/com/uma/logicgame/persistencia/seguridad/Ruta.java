package uy.com.uma.logicgame.persistencia.seguridad;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uy.com.uma.logicgame.api.bean.RutaDO;
import uy.com.uma.logicgame.persistencia.juego.Juego;

/**
 * Representa la tabla rutas
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "rutas")
public class Ruta {

	@EmbeddedId
	private RutaPK id;
	
	@ManyToOne
	@JoinColumn(name="id_juego", referencedColumnName="id", insertable=false, updatable=false)
	private Juego juego;
	
	@Column(name="hoja_estilo")
	private String hojaEstilo;

	
	
	/**
	 * Retorna los datos de la ruta en una clase "liviana"
	 */
	public static RutaDO getRuta (Ruta r) {
		RutaDO ret = new RutaDO();
		ret.setId(r.getId().getId());
		ret.setNivel(r.getId().getNivel());
		ret.setIdJuego(r.getJuego().getId());
		ret.setHojaEstilo(r.getHojaEstilo());
		return ret;		
	}
	
	public RutaPK getId() {
		return id;
	}
	public void setId(RutaPK id) {
		this.id = id;
	}
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	public String getHojaEstilo() {
		return this.hojaEstilo;
	}
	public void setHojaEstilo (String hoja) {
		this.hojaEstilo = hoja;
	}
}
