//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.08.18 a las 10:46:59 AM UYT 
//


package uy.com.uma.logicgame.nucleo.jaxb.juego;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para literal complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="literal"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element name="traduccion"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="idioma" type="{}string2"/&gt;
 *                   &lt;element name="texto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "literal", propOrder = {
    "traduccion"
})
public class Literal {

    @XmlElement(required = true)
    protected List<Literal.Traduccion> traduccion;

    /**
     * Gets the value of the traduccion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the traduccion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTraduccion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Literal.Traduccion }
     * 
     * 
     */
    public List<Literal.Traduccion> getTraduccion() {
        if (traduccion == null) {
            traduccion = new ArrayList<Literal.Traduccion>();
        }
        return this.traduccion;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="idioma" type="{}string2"/&gt;
     *         &lt;element name="texto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idioma",
        "texto"
    })
    public static class Traduccion {

        @XmlElement(required = true)
        protected String idioma;
        @XmlElement(required = true)
        protected String texto;

        /**
         * Obtiene el valor de la propiedad idioma.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdioma() {
            return idioma;
        }

        /**
         * Define el valor de la propiedad idioma.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdioma(String value) {
            this.idioma = value;
        }

        /**
         * Obtiene el valor de la propiedad texto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTexto() {
            return texto;
        }

        /**
         * Define el valor de la propiedad texto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTexto(String value) {
            this.texto = value;
        }

    }

}
