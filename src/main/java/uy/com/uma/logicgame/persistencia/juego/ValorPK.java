package uy.com.uma.logicgame.persistencia.juego;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * Clave primaria de la tabla valores
 *
 * @author Santiago Dalchiele
 */
class ValorPK implements Serializable {

	private static final long serialVersionUID = 4868227471254257674L;
	
	@Column(name="id_juego")	
	private Integer idJuego;
	
	@Column(name="sec_dim")
	private Short secDim;
	
	@Column
	private Short sec;

	
	
	public ValorPK() {
	}

	public ValorPK(Integer idJuego, Short secDim, Short sec) {
		this.idJuego = idJuego;
		this.secDim = secDim;
		this.sec = sec;
	}

	
	
	public Integer getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(Integer idJuego) {
		this.idJuego = idJuego;
	}
	public Short getSecDim() {
		return secDim;
	}
	public void setSecDim(Short secDim) {
		this.secDim = secDim;
	}
	public Short getSec() {
		return sec;
	}
	public void setSec(Short sec) {
		this.sec = sec;
	}
}
