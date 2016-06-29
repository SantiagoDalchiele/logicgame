package uy.com.uma.logicgame.persistencia.juego;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Representa la tabla pistas_juego
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "pistas_juego")
class PistaJuego {

	@EmbeddedId
	private PistaJuegoPK id;
	
	@Column
	private Long texto;
	
	@OneToMany(mappedBy="pista", cascade=CascadeType.ALL)
	@OrderBy("id")
	private List<Pista> pistas = new ArrayList<Pista>();
	
	@ManyToOne
	@JoinColumn(name="id_juego", referencedColumnName="id", insertable=false, updatable=false)
	private Juego juego;

	
	
	public PistaJuegoPK getId() {
		return id;
	}
	public void setId(PistaJuegoPK id) {
		this.id = id;
	}
	public Long getTexto() {
		return texto;
	}
	public void setTexto(Long texto) {
		this.texto = texto;
	}
	public List<Pista> getPistas() {
		return pistas;
	}
	public void setPistas(List<Pista> pistas) {
		this.pistas = pistas;
	}	
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
}
