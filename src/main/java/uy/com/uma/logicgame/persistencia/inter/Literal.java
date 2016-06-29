package uy.com.uma.logicgame.persistencia.inter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Representa la tabla literales
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "literales")
class Literal {

	@EmbeddedId
	private LiteralPK id;
	
	@Column
	private String texto;
	
	

	public Literal() {
	}	
	
	public Literal(LiteralPK id) {
		this.id = id;
	}
	
	public Literal(LiteralPK id, String texto) {
		this(id);
		this.texto = texto;
	}

	
	
	public LiteralPK getId() {
		return id;
	}
	public void setId(LiteralPK id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}	
}
