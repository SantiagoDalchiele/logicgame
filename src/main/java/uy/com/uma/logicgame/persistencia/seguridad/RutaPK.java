package uy.com.uma.logicgame.persistencia.seguridad;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * Clave primaria de la tabla rutas
 *
 * @author Santiago Dalchiele
 */
public class RutaPK implements Serializable {

	private static final long serialVersionUID = -3934068262329416137L;
	
	@Column
	private Short id;
	
	@Column
	private Integer nivel;
	
	
	
	public RutaPK() {		
	}
	
	public RutaPK (Short id, Integer nivel) {
		this.id = id;
		this.nivel = nivel;
	}

	
	
	public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}	
}