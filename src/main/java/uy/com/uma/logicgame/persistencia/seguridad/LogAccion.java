package uy.com.uma.logicgame.persistencia.seguridad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import uy.com.uma.logicgame.api.bean.LogAccionDO;

/**
 * Representa la tabla log_acciones
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "log_acciones")
class LogAccion {

	@Column
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="tipo_accion")
	private Short tipoAccion;
	
	@Column(columnDefinition = "bpchar(30)")
	private String ip;
	
	@Column
	private String usuario;
	
	@Column(columnDefinition = "bpchar(256)")
	private String clave;
	
	@Column
	private Short resultado;
	
	@Column(name="fch_registro")
	private Date fchRegistro;

	
	
	public LogAccion() {		
	}
	
	public LogAccion (LogAccionDO la) {
		this.tipoAccion = la.getTipoAccion();
		this.ip = la.getIp();
		this.usuario = la.getUsuario();
		this.clave = la.getClave();
		this.resultado = la.getResultado();
		this.fchRegistro = la.getFchRegistro();
	}
	
	
	
	public static LogAccionDO getLogAccion (LogAccion a) {
		LogAccionDO ret = new LogAccionDO();
		ret.setId(a.getId());
		ret.setTipoAccion(a.getTipoAccion());
		ret.setIp(a.getIp());
		ret.setUsuario(a.getUsuario());
		ret.setClave(a.getClave());
		ret.setResultado(a.getResultado());
		ret.setFchRegistro(a.getFchRegistro());
		return ret;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public Short getTipoAccion() {
		return tipoAccion;
	}
	public void setTipoAccion(Short tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Short getResultado() {
		return resultado;
	}
	public void setResultado(Short resultado) {
		this.resultado = resultado;
	}
	public Date getFchRegistro() {
		return fchRegistro;
	}
	public void setFchRegistro(Date fchRegistro) {
		this.fchRegistro = fchRegistro;
	}	
}
