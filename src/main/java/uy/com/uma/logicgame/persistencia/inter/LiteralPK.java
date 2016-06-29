package uy.com.uma.logicgame.persistencia.inter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clave primaria de la tabla literales
 *
 * @author Santiago Dalchiele
 */
@Embeddable
class LiteralPK implements Serializable {

	private static final long serialVersionUID = -721197816054438374L;

	@Column
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="idioma", referencedColumnName="id")
	private Idioma idioma;
	
	
	
	public LiteralPK() {		
	}
	
	public LiteralPK(Long id, Idioma idioma) {
		this.id = id;
		this.idioma = idioma;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
}
