//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.07.05 a las 12:32:51 PM UYT 
//


package uy.com.uma.logicgame.nucleo.jaxb.conf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="costo-inicial-por-absurdo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="estrategias"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded"&gt;
 *                   &lt;element name="estrategia"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="orden" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *                             &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="manejadores"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="estructura" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="sesiones" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="adm-internacionalizacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="internacionalizacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="juego" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="juego-web" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="seguridad" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="configuracion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="cargador-recursos" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ruta-defecto" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="security-salt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="clave-rol-adm" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="clave-rol-web" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "costoInicialPorAbsurdo",
    "estrategias",
    "manejadores",
    "rutaDefecto",
    "securitySalt",
    "claveRolAdm",
    "claveRolWeb"
})
@XmlRootElement(name = "configuracion")
public class Configuracion {

    @XmlElement(name = "costo-inicial-por-absurdo", required = true)
    protected BigInteger costoInicialPorAbsurdo;
    @XmlElement(required = true)
    protected Configuracion.Estrategias estrategias;
    @XmlElement(required = true)
    protected Configuracion.Manejadores manejadores;
    @XmlElement(name = "ruta-defecto")
    protected short rutaDefecto;
    @XmlElement(name = "security-salt", required = true)
    protected String securitySalt;
    @XmlElement(name = "clave-rol-adm", required = true)
    protected String claveRolAdm;
    @XmlElement(name = "clave-rol-web", required = true)
    protected String claveRolWeb;

    /**
     * Obtiene el valor de la propiedad costoInicialPorAbsurdo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCostoInicialPorAbsurdo() {
        return costoInicialPorAbsurdo;
    }

    /**
     * Define el valor de la propiedad costoInicialPorAbsurdo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCostoInicialPorAbsurdo(BigInteger value) {
        this.costoInicialPorAbsurdo = value;
    }

    /**
     * Obtiene el valor de la propiedad estrategias.
     * 
     * @return
     *     possible object is
     *     {@link Configuracion.Estrategias }
     *     
     */
    public Configuracion.Estrategias getEstrategias() {
        return estrategias;
    }

    /**
     * Define el valor de la propiedad estrategias.
     * 
     * @param value
     *     allowed object is
     *     {@link Configuracion.Estrategias }
     *     
     */
    public void setEstrategias(Configuracion.Estrategias value) {
        this.estrategias = value;
    }

    /**
     * Obtiene el valor de la propiedad manejadores.
     * 
     * @return
     *     possible object is
     *     {@link Configuracion.Manejadores }
     *     
     */
    public Configuracion.Manejadores getManejadores() {
        return manejadores;
    }

    /**
     * Define el valor de la propiedad manejadores.
     * 
     * @param value
     *     allowed object is
     *     {@link Configuracion.Manejadores }
     *     
     */
    public void setManejadores(Configuracion.Manejadores value) {
        this.manejadores = value;
    }

    /**
     * Obtiene el valor de la propiedad rutaDefecto.
     * 
     */
    public short getRutaDefecto() {
        return rutaDefecto;
    }

    /**
     * Define el valor de la propiedad rutaDefecto.
     * 
     */
    public void setRutaDefecto(short value) {
        this.rutaDefecto = value;
    }

    /**
     * Obtiene el valor de la propiedad securitySalt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecuritySalt() {
        return securitySalt;
    }

    /**
     * Define el valor de la propiedad securitySalt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecuritySalt(String value) {
        this.securitySalt = value;
    }

    /**
     * Obtiene el valor de la propiedad claveRolAdm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveRolAdm() {
        return claveRolAdm;
    }

    /**
     * Define el valor de la propiedad claveRolAdm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveRolAdm(String value) {
        this.claveRolAdm = value;
    }

    /**
     * Obtiene el valor de la propiedad claveRolWeb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveRolWeb() {
        return claveRolWeb;
    }

    /**
     * Define el valor de la propiedad claveRolWeb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveRolWeb(String value) {
        this.claveRolWeb = value;
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
     *       &lt;sequence maxOccurs="unbounded"&gt;
     *         &lt;element name="estrategia"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="orden" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
     *                   &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
    @XmlType(name = "", propOrder = {
        "estrategia"
    })
    public static class Estrategias {

        @XmlElement(required = true)
        protected List<Configuracion.Estrategias.Estrategia> estrategia;

        /**
         * Gets the value of the estrategia property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the estrategia property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEstrategia().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Configuracion.Estrategias.Estrategia }
         * 
         * 
         */
        public List<Configuracion.Estrategias.Estrategia> getEstrategia() {
            if (estrategia == null) {
                estrategia = new ArrayList<Configuracion.Estrategias.Estrategia>();
            }
            return this.estrategia;
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
         *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="orden" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
         *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
            "descripcion",
            "clase",
            "orden",
            "costo"
        })
        public static class Estrategia {

            @XmlElement(required = true)
            protected String descripcion;
            @XmlElement(required = true)
            protected String clase;
            protected short orden;
            @XmlElement(required = true)
            protected BigInteger costo;

            /**
             * Obtiene el valor de la propiedad descripcion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescripcion() {
                return descripcion;
            }

            /**
             * Define el valor de la propiedad descripcion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescripcion(String value) {
                this.descripcion = value;
            }

            /**
             * Obtiene el valor de la propiedad clase.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getClase() {
                return clase;
            }

            /**
             * Define el valor de la propiedad clase.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setClase(String value) {
                this.clase = value;
            }

            /**
             * Obtiene el valor de la propiedad orden.
             * 
             */
            public short getOrden() {
                return orden;
            }

            /**
             * Define el valor de la propiedad orden.
             * 
             */
            public void setOrden(short value) {
                this.orden = value;
            }

            /**
             * Obtiene el valor de la propiedad costo.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getCosto() {
                return costo;
            }

            /**
             * Define el valor de la propiedad costo.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setCosto(BigInteger value) {
                this.costo = value;
            }

        }

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
     *         &lt;element name="estructura" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="sesiones" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="adm-internacionalizacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="internacionalizacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="juego" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="juego-web" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="seguridad" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="configuracion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="cargador-recursos" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "estructura",
        "sesiones",
        "admInternacionalizacion",
        "internacionalizacion",
        "juego",
        "juegoWeb",
        "seguridad",
        "configuracion",
        "cargadorRecursos"
    })
    public static class Manejadores {

        @XmlElement(required = true)
        protected String estructura;
        @XmlElement(required = true)
        protected String sesiones;
        @XmlElement(name = "adm-internacionalizacion", required = true)
        protected String admInternacionalizacion;
        @XmlElement(required = true)
        protected String internacionalizacion;
        @XmlElement(required = true)
        protected String juego;
        @XmlElement(name = "juego-web", required = true)
        protected String juegoWeb;
        @XmlElement(required = true)
        protected String seguridad;
        @XmlElement(required = true)
        protected String configuracion;
        @XmlElement(name = "cargador-recursos", required = true)
        protected String cargadorRecursos;

        /**
         * Obtiene el valor de la propiedad estructura.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEstructura() {
            return estructura;
        }

        /**
         * Define el valor de la propiedad estructura.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEstructura(String value) {
            this.estructura = value;
        }

        /**
         * Obtiene el valor de la propiedad sesiones.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSesiones() {
            return sesiones;
        }

        /**
         * Define el valor de la propiedad sesiones.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSesiones(String value) {
            this.sesiones = value;
        }

        /**
         * Obtiene el valor de la propiedad admInternacionalizacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdmInternacionalizacion() {
            return admInternacionalizacion;
        }

        /**
         * Define el valor de la propiedad admInternacionalizacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdmInternacionalizacion(String value) {
            this.admInternacionalizacion = value;
        }

        /**
         * Obtiene el valor de la propiedad internacionalizacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInternacionalizacion() {
            return internacionalizacion;
        }

        /**
         * Define el valor de la propiedad internacionalizacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInternacionalizacion(String value) {
            this.internacionalizacion = value;
        }

        /**
         * Obtiene el valor de la propiedad juego.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJuego() {
            return juego;
        }

        /**
         * Define el valor de la propiedad juego.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJuego(String value) {
            this.juego = value;
        }

        /**
         * Obtiene el valor de la propiedad juegoWeb.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJuegoWeb() {
            return juegoWeb;
        }

        /**
         * Define el valor de la propiedad juegoWeb.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJuegoWeb(String value) {
            this.juegoWeb = value;
        }

        /**
         * Obtiene el valor de la propiedad seguridad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSeguridad() {
            return seguridad;
        }

        /**
         * Define el valor de la propiedad seguridad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSeguridad(String value) {
            this.seguridad = value;
        }

        /**
         * Obtiene el valor de la propiedad configuracion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getConfiguracion() {
            return configuracion;
        }

        /**
         * Define el valor de la propiedad configuracion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setConfiguracion(String value) {
            this.configuracion = value;
        }

        /**
         * Obtiene el valor de la propiedad cargadorRecursos.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCargadorRecursos() {
            return cargadorRecursos;
        }

        /**
         * Define el valor de la propiedad cargadorRecursos.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCargadorRecursos(String value) {
            this.cargadorRecursos = value;
        }

    }

}
