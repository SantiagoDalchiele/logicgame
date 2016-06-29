package uy.com.uma.logicgame.api.bean;

/**
 * Clase liviana para trasegar datos de los literales
 *
 * @author Santiago Dalchiele
 */
public class LiteralBean {

	private long id;
	private String idioma;
	private String texto;
	
	
	
	public LiteralBean() {		
	}	
	
	public LiteralBean(long id, String idioma, String texto) {
		super();
		this.id = id;
		this.idioma = idioma;
		this.texto = texto;
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}	
}