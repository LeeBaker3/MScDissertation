/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lee Baker
 */
@Entity
@Table(name = "methodofdistribution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Methodofdistribution.findAll", query = "SELECT m FROM Methodofdistribution m"),
    @NamedQuery(name = "Methodofdistribution.findByMethodOfDistributionID", query = "SELECT m FROM Methodofdistribution m WHERE m.methodOfDistributionID = :methodOfDistributionID"),
    @NamedQuery(name = "Methodofdistribution.findByMethod", query = "SELECT m FROM Methodofdistribution m WHERE m.method = :method")})
public class Methodofdistribution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MethodOfDistributionID")
    private Integer methodOfDistributionID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Method")
    private String method;

    public Methodofdistribution() {
    }

    public Methodofdistribution(Integer methodOfDistributionID) {
        this.methodOfDistributionID = methodOfDistributionID;
    }

    public Methodofdistribution(Integer methodOfDistributionID, String method) {
        this.methodOfDistributionID = methodOfDistributionID;
        this.method = method;
    }

    public Integer getMethodOfDistributionID() {
        return methodOfDistributionID;
    }

    public void setMethodOfDistributionID(Integer methodOfDistributionID) {
        this.methodOfDistributionID = methodOfDistributionID;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (methodOfDistributionID != null ? methodOfDistributionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Methodofdistribution)) {
            return false;
        }
        Methodofdistribution other = (Methodofdistribution) object;
        if ((this.methodOfDistributionID == null && other.methodOfDistributionID != null) || (this.methodOfDistributionID != null && !this.methodOfDistributionID.equals(other.methodOfDistributionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Methodofdistribution[ methodOfDistributionID=" + methodOfDistributionID + " ]";
    }
    
}
