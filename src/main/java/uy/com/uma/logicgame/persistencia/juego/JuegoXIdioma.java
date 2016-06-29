package uy.com.uma.logicgame.persistencia.juego;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa la tabla juegosxidioma
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "juegosxidioma")
class JuegoXIdioma {

	@EmbeddedId
	private JuegoXIdiomaPK id;
	
	@Column(name="def_juego")
	private String defJuego;
	
	@ManyToOne
	@JoinColumn(name="id_juego", referencedColumnName="id", insertable=false, updatable=false)
	private Juego juego;

	
	
	public JuegoXIdiomaPK getId() {
		return id;
	}
	public void setId(JuegoXIdiomaPK id) {
		this.id = id;
	}
	public String getDefJuego() {
		return defJuego;
	}
	public void setDefJuego(String defJuego) {
		this.defJuego = defJuego;
	}
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}	
}
