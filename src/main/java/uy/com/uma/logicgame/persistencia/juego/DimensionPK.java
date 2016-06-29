package uy.com.uma.logicgame.persistencia.juego;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * Clave primaria de la tabla dimensiones
 *
 * @author Santiago Dalchiele
 */
class DimensionPK implements Serializable {

	private static final long serialVersionUID = -4066448263358154338L;

	@Column(name="id_juego")	
	private Integer idJuego;
	
	@Column
	private Short sec;

	
	
	public DimensionPK() {		
	}
	
	public DimensionPK(Integer idJuego, Short sec) {
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
