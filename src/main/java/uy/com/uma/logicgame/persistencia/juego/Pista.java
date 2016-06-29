package uy.com.uma.logicgame.persistencia.juego;

import javax.persistence.Column;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa la tabla pistas
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "pistas")
class Pista {

	@EmbeddedId
	private PistaPK id;
	
	@ManyToOne
	@JoinColumns({@JoinColumn(name="id_juego", referencedColumnName="id_juego", insertable=false, updatable=false),
				@JoinColumn(name="sec_pista", referencedColumnName="sec", insertable=false, updatable=false) })
	private PistaJuego pista;
	
	@Column
	private Short dim1;
	
	@Column
	private Short valor1;
	
	@Column
	private Short dim2;
	
	@Column
	private Short valor2;
	
	@Column(name="afirma_niega")
	private Boolean afirmaNiega;

	
	
	public PistaPK getId() {
		return id;
	}
	public void setId(PistaPK id) {
		this.id = id;
	}
	public Short getDim1() {
		return dim1;
	}
	public void setDim1(Short dim1) {
		this.dim1 = dim1;
	}
	public Short getValor1() {
		return valor1;
	}
	public void setValor1(Short valor1) {
		this.valor1 = valor1;
	}
	public Short getDim2() {
		return dim2;
	}
	public void setDim2(Short dim2) {
		this.dim2 = dim2;
	}
	public Short getValor2() {
		return valor2;
	}
	public void setValor2(Short valor2) {
		this.valor2 = valor2;
	}
	public Boolean getAfirmaNiega() {
		return afirmaNiega;
	}
	public void setAfirmaNiega(Boolean afirmaNiega) {
		this.afirmaNiega = afirmaNiega;
	}
	public PistaJuego getPista() {
		return pista;
	}
	public void setPista(PistaJuego pista) {
		this.pista = pista;
	}	
}
