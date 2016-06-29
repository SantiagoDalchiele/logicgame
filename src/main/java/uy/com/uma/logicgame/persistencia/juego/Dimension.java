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
 * Representa la tabla dimensiones
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "dimensiones")
class Dimension {
	
	@EmbeddedId
	private DimensionPK id;
	
	@Column(name="id")
	private Long idDim;
	
	@OneToMany(mappedBy="dimension", cascade=CascadeType.ALL)
	@OrderBy("id")
	private List<Valor> valores = new ArrayList<Valor>();
	
	@ManyToOne
	@JoinColumn(name="id_juego", referencedColumnName="id", insertable=false, updatable=false)
	private Juego juego;

	
	
	public DimensionPK getId() {
		return id;
	}
	public void setId(DimensionPK id) {
		this.id = id;
	}
	public Long getIdDim() {
		return idDim;
	}
	public void setIdDim(Long idDim) {
		this.idDim = idDim;
	}
	public List<Valor> getValores() {
		return valores;
	}
	public void setValores(List<Valor> valores) {
		this.valores = valores;
	}
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}	
}
