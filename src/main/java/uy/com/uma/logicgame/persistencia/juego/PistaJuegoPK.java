package uy.com.uma.logicgame.persistencia.juego;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * Clave primaria de la tabla pistas_juego
 *
 * @author Santiago Dalchiele
 */
class PistaJuegoPK implements Serializable {

	private static final long serialVersionUID = 4537015902498735544L;

	@Column(name="id_juego")	
	private Integer idJuego;
	
	@Column
	private Short sec;

	
	
	public PistaJuegoPK() {		
	}
	
	public PistaJuegoPK(Integer idJuego, Short sec) {
		this.idJuego = idJuego;
		this.sec = sec;
	}
	
	
	
	public Integer getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(Integer idJuego) {
		this.idJuego = idJuego;
	}
	public Short getSec() {
		return sec;
	}
	public void setSec(Short sec) {
		this.sec = sec;
	}	
}
