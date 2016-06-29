package uy.com.uma.logicgame.persistencia.juego;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import uy.com.uma.logicgame.persistencia.seguridad.Ruta;

/**
 * Representa la tabla juegos
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "juegos")
public class Juego {
	
	@Id
	@Column
	private Integer id;
	
	@Column
	private Long titulo;
	
	@Column
	private Long texto;
	
	@Column
	private Integer costo;
	
	@Column (name="cant_dims")
	private Short cantDims;
	
	@Column (name="cant_valores")
	private Short cantValores;
	
	@Column
	private String solucion;
	
	@OneToMany(mappedBy="juego", cascade=CascadeType.ALL)
	@OrderBy("id")
	private List<Dimension> dimensiones = new ArrayList<Dimension>();
	
	@OneToMany(mappedBy="juego", cascade=CascadeType.ALL)
	@OrderBy("id")
	private List<PistaJuego> pistas = new ArrayList<PistaJuego>();
	
	@OneToMany(mappedBy="juego", cascade=CascadeType.ALL)
	@OrderBy("id")
	private List<JuegoXIdioma> juegosxidioma = new ArrayList<JuegoXIdioma>();
	
	@OneToMany(mappedBy="juego", cascade=CascadeType.ALL)
	private List<Ruta> rutas = new ArrayList<Ruta>();

	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getTitulo() {
		return titulo;
	}
	public void setTitulo(Long titulo) {
		this.titulo = titulo;
	}
	public Long getTexto() {
		return texto;
	}
	public void setTexto(Long texto) {
		this.texto = texto;
	}
	public Integer getCosto() {
		return costo;
	}
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	public List<Dimension> getDimensiones() {
		return dimensiones;
	}
	public void setDimensiones(List<Dimension> dimensiones) {
		this.dimensiones = dimensiones;
	}
	public List<PistaJuego> getPistas() {
		return pistas;
	}
	public void setPistas(List<PistaJuego> pistas) {
		this.pistas = pistas;
	}
	public Short getCantDims() {
		return cantDims;
	}
	public void setCantDims(Short cantDims) {
		this.cantDims = cantDims;
	}
	public Short getCantValores() {
		return cantValores;
	}
	public void setCantValores(Short cantValores) {
		this.cantValores = cantValores;
	}
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}	
	public List<JuegoXIdioma> getJuegosXIdioma() {
		return juegosxidioma;
	}
	public void setJuegosXIdioma(List<JuegoXIdioma> juegosxidioma) {
		this.juegosxidioma = juegosxidioma;
	}
	public List<Ruta> getRutas() {
		return rutas;
	}
	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}	
}
