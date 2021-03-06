//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.17 à 03:29:55 PM CET 
//


package org.tmforum.projects.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/*
 * <p>Classe Java pour CustomerAccountTaxExemption complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CustomerAccountTaxExemption">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="issuingJurisdiction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="certificateNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validFor" type="{http://orange.com/api/customer/tmf/v2/model/business}ValidFor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerAccountTaxExemption", propOrder = {
    "issuingJurisdiction",
    "certificateNumber",
    "reason",
    "validFor"
})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity(name = "CustomerAccountTaxExemption")
@Table(name = "CUSTOMER_ACCOUNT_TAX_EXEMPTI_0")
@Inheritance(strategy = InheritanceType.JOINED)
public class CustomerAccountTaxExemption
    implements Serializable
{

    private final static long serialVersionUID = 11L;
    protected String issuingJurisdiction;
    protected String certificateNumber;
    protected String reason;
    protected ValidFor validFor;
    //@org.codehaus.jackson.annotate.JsonIgnore
    @JsonIgnore
    protected Long hjid;

    /*
     * Obtient la valeur de la propriété issuingJurisdiction.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "ISSUING_JURISDICTION", length = 255)
    public String getIssuingJurisdiction() {
        return issuingJurisdiction;
    }

    /*
     * Définit la valeur de la propriété issuingJurisdiction.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuingJurisdiction(String value) {
        this.issuingJurisdiction = value;
    }

    /*
     * Obtient la valeur de la propriété certificateNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "CERTIFICATE_NUMBER", length = 255)
    public String getCertificateNumber() {
        return certificateNumber;
    }

    /*
     * Définit la valeur de la propriété certificateNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateNumber(String value) {
        this.certificateNumber = value;
    }

    /*
     * Obtient la valeur de la propriété reason.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "REASON", length = 255)
    public String getReason() {
        return reason;
    }

    /*
     * Définit la valeur de la propriété reason.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /*
     * Obtient la valeur de la propriété validFor.
     * 
     * @return
     *     possible object is
     *     {@link ValidFor }
     *     
     */
    @ManyToOne(targetEntity = ValidFor.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "VALID_FOR_CUSTOMER_ACCOUNT_T_0")
    public ValidFor getValidFor() {
        return validFor;
    }

    /*
     * Définit la valeur de la propriété validFor.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidFor }
     *     
     */
    public void setValidFor(ValidFor value) {
        this.validFor = value;
    }

    /*
     * Obtient la valeur de la propriété hjid.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @Id
    @Column(name = "HJID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@org.codehaus.jackson.annotate.JsonIgnore
    @JsonIgnore
    public Long getHjid() {
        return hjid;
    }

    /*
     * Définit la valeur de la propriété hjid.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHjid(Long value) {
        this.hjid = value;
    }

}
