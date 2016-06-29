package uy.com.uma.logicgame.persistencia.seguridad;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
