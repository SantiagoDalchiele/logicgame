package uy.com.uma.logicgame.persistencia.juego;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa la tabla valores
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "valores")
class Valor {

	@EmbeddedId
	private ValorPK id;
	
	@Column
	private Long valor;
	
	@ManyToOne
	@JoinColumns({@JoinColumn(name="id_juego", referencedColumnName="id_juego", insertable=false, updatable=false),
				@JoinColumn(name="sec_dim", referencedColumnName="sec", insertable=false, updatable=false) })
	private Dimension dimension;
	
	
	
	public ValorPK getId() {
		return id;
	}
	public void setId(ValorPK id) {
		this.id = id;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}	
}
