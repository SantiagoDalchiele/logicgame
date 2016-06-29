package uy.com.uma.logicgame.persistencia.juego;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clave primaria de la tabla pistas
 *
 * @author Santiago Dalchiele
 */
@Embeddable
class PistaPK implements Serializable {

	private static final long serialVersionUID = -237404223586741159L;

	@Column(name="id_juego")	
	private Integer idJuego;
	
	@Column(name="sec_pista")
	private Short secPista;
	
	@Column
	private Short sec;

	
	
	public PistaPK() {		
	}
	
	public PistaPK(Integer idJuego, Short secPista, Short sec) {
		this.idJuego = idJuego;
		this.secPista = secPista;
		this.sec = sec;
	}

	
	
	public Integer getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(Integer idJuego) {
		this.idJuego = idJuego;
	}
	public Short getSecPista() {
		return secPista;
	}
	public void setSecPista(Short secPista) {
		this.secPista = secPista;
	}
	public Short getSec() {
		return sec;
	}
	public void setSec(Short sec) {
		this.sec = sec;
	}	
}