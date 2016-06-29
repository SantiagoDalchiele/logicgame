package uy.com.uma.logicgame.persistencia.juego;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * Clave primaria de la tabla juegosxidioma
 *
 * @author Santiago Dalchiele
 */
class JuegoXIdiomaPK implements Serializable {

	private static final long serialVersionUID = 1317541145260726676L;

	@Column(name="id_juego")	
	private Integer idJuego;
	
    @Column(columnDefinition = "bpchar(2)")
	private String idioma;
	
	
	
	public JuegoXIdiomaPK() {		
	}
	
	public JuegoXIdiomaPK (Integer idJuego, String idioma) {
		this.idJuego = idJuego;
		this.idioma = idioma;
	}
	
	
	
	public Integer getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(Integer idJuego) {
		this.idJuego = idJuego;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
}
