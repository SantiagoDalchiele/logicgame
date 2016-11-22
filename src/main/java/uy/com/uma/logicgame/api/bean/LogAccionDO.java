package uy.com.uma.logicgame.api.bean;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import uy.com.uma.comun.util.UtilWeb;

/**
 * Clase liviana para trasegar datos del log de acciones
 *
 * @author Santiago Dalchiele
 */
public class LogAccionDO {

	private long id;	
	private short tipoAccion;
	private String ip;
	private String usuario;
	private String clave;
	private short resultado;
	private Date fchRegistro;
	
	
	
	public LogAccionDO() {
	}
	
	public LogAccionDO(HttpServletRequest req, short tipoAccion) {
		this.ip = UtilWeb.getClientIpAddr(req);
		this.fchRegistro = new Date();
		this.tipoAccion = tipoAccion;
	}
	
	public LogAccionDO(short tipoAccion, String ip, String usuario, String clave, short resultado, Date fchRegistro) {
		this.tipoAccion = tipoAccion;
		this.ip = ip;
		this.usuario = usuario;
		this.clave = clave;
		this.resultado = resultado;
		this.fchRegistro = fchRegistro;
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public short getTipoAccion() {
		return tipoAccion;
	}
	public void setTipoAccion(short tipoAccion) {
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
	public short getResultado() {
		return resultado;
	}
	public void setResultado(short resultado) {
		this.resultado = resultado;
	}
	public Date getFchRegistro() {
		return fchRegistro;
	}
	public void setFchRegistro(Date fchRegistro) {
		this.fchRegistro = fchRegistro;
	}	
}
