//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.08.27 a las 03:36:46 PM UYT 
//


package uy.com.uma.logicgame.nucleo.jaxb.juego;

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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="texto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="dimensiones"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded"&gt;
 *                   &lt;element name="dimension"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="valores"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence maxOccurs="unbounded"&gt;
 *                                       &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
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
 *         &lt;element name="pistas-del-juego"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded"&gt;
 *                   &lt;element name="pista-del-juego"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="texto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="pistas"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence maxOccurs="unbounded"&gt;
 *                                       &lt;element name="pista"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;complexContent&gt;
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                               &lt;sequence&gt;
 *                                                 &lt;element name="id-dimension1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                                 &lt;element name="id-valor1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                                 &lt;element name="id-dimension2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                                 &lt;element name="id-valor2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                                 &lt;element name="afirma-niega" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                                               &lt;/sequence&gt;
 *                                             &lt;/restriction&gt;
 *                                           &lt;/complexContent&gt;
 *                                         &lt;/complexType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
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
    "id",
    "titulo",
    "texto",
    "costo",
    "dimensiones",
    "pistasDelJuego"
})
@XmlRootElement(name = "juego")
public class Juego {

    @XmlElement(required = true)
    protected BigInteger id;
    @XmlElement(required = true)
    protected String titulo;
    @XmlElement(required = true)
    protected String texto;
    @XmlElement(required = true)
    protected BigInteger costo;
    @XmlElement(required = true)
    protected Juego.Dimensiones dimensiones;
    @XmlElement(name = "pistas-del-juego", required = true)
    protected Juego.PistasDelJuego pistasDelJuego;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
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

    /**
     * Obtiene el valor de la propiedad dimensiones.
     * 
     * @return
     *     possible object is
     *     {@link Juego.Dimensiones }
     *     
     */
    public Juego.Dimensiones getDimensiones() {
        return dimensiones;
    }

    /**
     * Define el valor de la propiedad dimensiones.
     * 
     * @param value
     *     allowed object is
     *     {@link Juego.Dimensiones }
     *     
     */
    public void setDimensiones(Juego.Dimensiones value) {
        this.dimensiones = value;
    }

    /**
     * Obtiene el valor de la propiedad pistasDelJuego.
     * 
     * @return
     *     possible object is
     *     {@link Juego.PistasDelJuego }
     *     
     */
    public Juego.PistasDelJuego getPistasDelJuego() {
        return pistasDelJuego;
    }

    /**
     * Define el valor de la propiedad pistasDelJuego.
     * 
     * @param value
     *     allowed object is
     *     {@link Juego.PistasDelJuego }
     *     
     */
    public void setPistasDelJuego(Juego.PistasDelJuego value) {
        this.pistasDelJuego = value;
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
     *         &lt;element name="dimension"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="valores"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence maxOccurs="unbounded"&gt;
     *                             &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "dimension"
    })
    public static class Dimensiones {

        @XmlElement(required = true)
        protected List<Juego.Dimensiones.Dimension> dimension;

        /**
         * Gets the value of the dimension property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dimension property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDimension().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Juego.Dimensiones.Dimension }
         * 
         * 
         */
        public List<Juego.Dimensiones.Dimension> getDimension() {
            if (dimension == null) {
                dimension = new ArrayList<Juego.Dimensiones.Dimension>();
            }
            return this.dimension;
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
         *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="valores"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence maxOccurs="unbounded"&gt;
         *                   &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "id",
            "valores"
        })
        public static class Dimension {

            @XmlElement(required = true)
            protected String id;
            @XmlElement(required = true)
            protected Juego.Dimensiones.Dimension.Valores valores;

            /**
             * Obtiene el valor de la propiedad id.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getId() {
                return id;
            }

            /**
             * Define el valor de la propiedad id.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setId(String value) {
                this.id = value;
            }

            /**
             * Obtiene el valor de la propiedad valores.
             * 
             * @return
             *     possible object is
             *     {@link Juego.Dimensiones.Dimension.Valores }
             *     
             */
            public Juego.Dimensiones.Dimension.Valores getValores() {
                return valores;
            }

            /**
             * Define el valor de la propiedad valores.
             * 
             * @param value
             *     allowed object is
             *     {@link Juego.Dimensiones.Dimension.Valores }
             *     
             */
            public void setValores(Juego.Dimensiones.Dimension.Valores value) {
                this.valores = value;
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
             *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
                "valor"
            })
            public static class Valores {

                @XmlElement(required = true)
                protected List<String> valor;

                /**
                 * Gets the value of the valor property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the valor property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getValor().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getValor() {
                    if (valor == null) {
                        valor = new ArrayList<String>();
                    }
                    return this.valor;
                }

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
     *       &lt;sequence maxOccurs="unbounded"&gt;
     *         &lt;element name="pista-del-juego"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="texto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="pistas"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence maxOccurs="unbounded"&gt;
     *                             &lt;element name="pista"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;complexContent&gt;
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                     &lt;sequence&gt;
     *                                       &lt;element name="id-dimension1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                                       &lt;element name="id-valor1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                                       &lt;element name="id-dimension2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                                       &lt;element name="id-valor2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                                       &lt;element name="afirma-niega" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *                                     &lt;/sequence&gt;
     *                                   &lt;/restriction&gt;
     *                                 &lt;/complexContent&gt;
     *                               &lt;/complexType&gt;
     *                             &lt;/element&gt;
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
        "pistaDelJuego"
    })
    public static class PistasDelJuego {

        @XmlElement(name = "pista-del-juego", required = true)
        protected List<Juego.PistasDelJuego.PistaDelJuego> pistaDelJuego;

        /**
         * Gets the value of the pistaDelJuego property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pistaDelJuego property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPistaDelJuego().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Juego.PistasDelJuego.PistaDelJuego }
         * 
         * 
         */
        public List<Juego.PistasDelJuego.PistaDelJuego> getPistaDelJuego() {
            if (pistaDelJuego == null) {
                pistaDelJuego = new ArrayList<Juego.PistasDelJuego.PistaDelJuego>();
            }
            return this.pistaDelJuego;
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
         *         &lt;element name="texto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="pistas"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence maxOccurs="unbounded"&gt;
         *                   &lt;element name="pista"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;complexContent&gt;
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                           &lt;sequence&gt;
         *                             &lt;element name="id-dimension1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                             &lt;element name="id-valor1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                             &lt;element name="id-dimension2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                             &lt;element name="id-valor2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                             &lt;element name="afirma-niega" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
            "texto",
            "pistas"
        })
        public static class PistaDelJuego {

            @XmlElement(required = true)
            protected String texto;
            @XmlElement(required = true)
            protected Juego.PistasDelJuego.PistaDelJuego.Pistas pistas;

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

            /**
             * Obtiene el valor de la propiedad pistas.
             * 
             * @return
             *     possible object is
             *     {@link Juego.PistasDelJuego.PistaDelJuego.Pistas }
             *     
             */
            public Juego.PistasDelJuego.PistaDelJuego.Pistas getPistas() {
                return pistas;
            }

            /**
             * Define el valor de la propiedad pistas.
             * 
             * @param value
             *     allowed object is
             *     {@link Juego.PistasDelJuego.PistaDelJuego.Pistas }
             *     
             */
            public void setPistas(Juego.PistasDelJuego.PistaDelJuego.Pistas value) {
                this.pistas = value;
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
             *         &lt;element name="pista"&gt;
             *           &lt;complexType&gt;
             *             &lt;complexContent&gt;
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                 &lt;sequence&gt;
             *                   &lt;element name="id-dimension1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *                   &lt;element name="id-valor1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *                   &lt;element name="id-dimension2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *                   &lt;element name="id-valor2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *                   &lt;element name="afirma-niega" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
                "pista"
            })
            public static class Pistas {

                @XmlElement(required = true)
                protected List<Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista> pista;

                /**
                 * Gets the value of the pista property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the pista property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getPista().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista }
                 * 
                 * 
                 */
                public List<Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista> getPista() {
                    if (pista == null) {
                        pista = new ArrayList<Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista>();
                    }
                    return this.pista;
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
                 *         &lt;element name="id-dimension1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                 *         &lt;element name="id-valor1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                 *         &lt;element name="id-dimension2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                 *         &lt;element name="id-valor2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                 *         &lt;element name="afirma-niega" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
                    "idDimension1",
                    "idValor1",
                    "idDimension2",
                    "idValor2",
                    "afirmaNiega"
                })
                public static class Pista {

                    @XmlElement(name = "id-dimension1", required = true)
                    protected String idDimension1;
                    @XmlElement(name = "id-valor1", required = true)
                    protected String idValor1;
                    @XmlElement(name = "id-dimension2", required = true)
                    protected String idDimension2;
                    @XmlElement(name = "id-valor2", required = true)
                    protected String idValor2;
                    @XmlElement(name = "afirma-niega")
                    protected boolean afirmaNiega;

                    /**
                     * Obtiene el valor de la propiedad idDimension1.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIdDimension1() {
                        return idDimension1;
                    }

                    /**
                     * Define el valor de la propiedad idDimension1.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIdDimension1(String value) {
                        this.idDimension1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad idValor1.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIdValor1() {
                        return idValor1;
                    }

                    /**
                     * Define el valor de la propiedad idValor1.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIdValor1(String value) {
                        this.idValor1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad idDimension2.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIdDimension2() {
                        return idDimension2;
                    }

                    /**
                     * Define el valor de la propiedad idDimension2.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIdDimension2(String value) {
                        this.idDimension2 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad idValor2.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIdValor2() {
                        return idValor2;
                    }

                    /**
                     * Define el valor de la propiedad idValor2.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIdValor2(String value) {
                        this.idValor2 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad afirmaNiega.
                     * 
                     */
                    public boolean isAfirmaNiega() {
                        return afirmaNiega;
                    }

                    /**
                     * Define el valor de la propiedad afirmaNiega.
                     * 
                     */
                    public void setAfirmaNiega(boolean value) {
                        this.afirmaNiega = value;
                    }

                }

            }

        }

    }

}
