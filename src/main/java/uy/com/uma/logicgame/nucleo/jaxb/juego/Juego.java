//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.08.18 a las 10:46:59 AM UYT 
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
 *         &lt;element name="titulo" type="{}literal"/&gt;
 *         &lt;element name="texto" type="{}literal"/&gt;
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
 *                             &lt;element name="nro" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *                             &lt;element name="id" type="{}literal"/&gt;
 *                             &lt;element name="valores"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence maxOccurs="unbounded"&gt;
 *                                       &lt;element name="valor"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;complexContent&gt;
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                               &lt;sequence&gt;
 *                                                 &lt;element name="nro" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *                                                 &lt;element name="id" type="{}literal"/&gt;
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
 *                             &lt;element name="texto" type="{}literal"/&gt;
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
 *                                                 &lt;element name="dimension1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *                                                 &lt;element name="valor1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *                                                 &lt;element name="dimension2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *                                                 &lt;element name="valor2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
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
    protected Literal titulo;
    @XmlElement(required = true)
    protected Literal texto;
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
     *     {@link Literal }
     *     
     */
    public Literal getTitulo() {
        return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link Literal }
     *     
     */
    public void setTitulo(Literal value) {
        this.titulo = value;
    }

    /**
     * Obtiene el valor de la propiedad texto.
     * 
     * @return
     *     possible object is
     *     {@link Literal }
     *     
     */
    public Literal getTexto() {
        return texto;
    }

    /**
     * Define el valor de la propiedad texto.
     * 
     * @param value
     *     allowed object is
     *     {@link Literal }
     *     
     */
    public void setTexto(Literal value) {
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
     *                   &lt;element name="nro" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
     *                   &lt;element name="id" type="{}literal"/&gt;
     *                   &lt;element name="valores"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence maxOccurs="unbounded"&gt;
     *                             &lt;element name="valor"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;complexContent&gt;
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                     &lt;sequence&gt;
     *                                       &lt;element name="nro" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
     *                                       &lt;element name="id" type="{}literal"/&gt;
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
         *         &lt;element name="nro" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
         *         &lt;element name="id" type="{}literal"/&gt;
         *         &lt;element name="valores"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence maxOccurs="unbounded"&gt;
         *                   &lt;element name="valor"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;complexContent&gt;
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                           &lt;sequence&gt;
         *                             &lt;element name="nro" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
         *                             &lt;element name="id" type="{}literal"/&gt;
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
            "nro",
            "id",
            "valores"
        })
        public static class Dimension {

            protected short nro;
            @XmlElement(required = true)
            protected Literal id;
            @XmlElement(required = true)
            protected Juego.Dimensiones.Dimension.Valores valores;

            /**
             * Obtiene el valor de la propiedad nro.
             * 
             */
            public short getNro() {
                return nro;
            }

            /**
             * Define el valor de la propiedad nro.
             * 
             */
            public void setNro(short value) {
                this.nro = value;
            }

            /**
             * Obtiene el valor de la propiedad id.
             * 
             * @return
             *     possible object is
             *     {@link Literal }
             *     
             */
            public Literal getId() {
                return id;
            }

            /**
             * Define el valor de la propiedad id.
             * 
             * @param value
             *     allowed object is
             *     {@link Literal }
             *     
             */
            public void setId(Literal value) {
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
             *         &lt;element name="valor"&gt;
             *           &lt;complexType&gt;
             *             &lt;complexContent&gt;
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                 &lt;sequence&gt;
             *                   &lt;element name="nro" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
             *                   &lt;element name="id" type="{}literal"/&gt;
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
                "valor"
            })
            public static class Valores {

                @XmlElement(required = true)
                protected List<Juego.Dimensiones.Dimension.Valores.Valor> valor;

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
                 * {@link Juego.Dimensiones.Dimension.Valores.Valor }
                 * 
                 * 
                 */
                public List<Juego.Dimensiones.Dimension.Valores.Valor> getValor() {
                    if (valor == null) {
                        valor = new ArrayList<Juego.Dimensiones.Dimension.Valores.Valor>();
                    }
                    return this.valor;
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
                 *         &lt;element name="nro" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
                 *         &lt;element name="id" type="{}literal"/&gt;
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
                    "nro",
                    "id"
                })
                public static class Valor {

                    protected short nro;
                    @XmlElement(required = true)
                    protected Literal id;

                    /**
                     * Obtiene el valor de la propiedad nro.
                     * 
                     */
                    public short getNro() {
                        return nro;
                    }

                    /**
                     * Define el valor de la propiedad nro.
                     * 
                     */
                    public void setNro(short value) {
                        this.nro = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad id.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Literal }
                     *     
                     */
                    public Literal getId() {
                        return id;
                    }

                    /**
                     * Define el valor de la propiedad id.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Literal }
                     *     
                     */
                    public void setId(Literal value) {
                        this.id = value;
                    }

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
     *                   &lt;element name="texto" type="{}literal"/&gt;
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
     *                                       &lt;element name="dimension1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
     *                                       &lt;element name="valor1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
     *                                       &lt;element name="dimension2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
     *                                       &lt;element name="valor2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
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
         *         &lt;element name="texto" type="{}literal"/&gt;
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
         *                             &lt;element name="dimension1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
         *                             &lt;element name="valor1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
         *                             &lt;element name="dimension2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
         *                             &lt;element name="valor2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
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
            protected Literal texto;
            @XmlElement(required = true)
            protected Juego.PistasDelJuego.PistaDelJuego.Pistas pistas;

            /**
             * Obtiene el valor de la propiedad texto.
             * 
             * @return
             *     possible object is
             *     {@link Literal }
             *     
             */
            public Literal getTexto() {
                return texto;
            }

            /**
             * Define el valor de la propiedad texto.
             * 
             * @param value
             *     allowed object is
             *     {@link Literal }
             *     
             */
            public void setTexto(Literal value) {
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
             *                   &lt;element name="dimension1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
             *                   &lt;element name="valor1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
             *                   &lt;element name="dimension2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
             *                   &lt;element name="valor2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
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
                 *         &lt;element name="dimension1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
                 *         &lt;element name="valor1" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
                 *         &lt;element name="dimension2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
                 *         &lt;element name="valor2" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
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
                    "dimension1",
                    "valor1",
                    "dimension2",
                    "valor2",
                    "afirmaNiega"
                })
                public static class Pista {

                    protected short dimension1;
                    protected short valor1;
                    protected short dimension2;
                    protected short valor2;
                    @XmlElement(name = "afirma-niega")
                    protected boolean afirmaNiega;

                    /**
                     * Obtiene el valor de la propiedad dimension1.
                     * 
                     */
                    public short getDimension1() {
                        return dimension1;
                    }

                    /**
                     * Define el valor de la propiedad dimension1.
                     * 
                     */
                    public void setDimension1(short value) {
                        this.dimension1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad valor1.
                     * 
                     */
                    public short getValor1() {
                        return valor1;
                    }

                    /**
                     * Define el valor de la propiedad valor1.
                     * 
                     */
                    public void setValor1(short value) {
                        this.valor1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad dimension2.
                     * 
                     */
                    public short getDimension2() {
                        return dimension2;
                    }

                    /**
                     * Define el valor de la propiedad dimension2.
                     * 
                     */
                    public void setDimension2(short value) {
                        this.dimension2 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad valor2.
                     * 
                     */
                    public short getValor2() {
                        return valor2;
                    }

                    /**
                     * Define el valor de la propiedad valor2.
                     * 
                     */
                    public void setValor2(short value) {
                        this.valor2 = value;
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
