/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.methodofdistribution;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
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
@AttributeOverride (name = "id", column = @Column(name = "MethodOfDistributionID"))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Methodofdistribution.findByEntityActiveAndMethodOfDistributionIDAndIsCurrentVersion", query = "SELECT m FROM Methodofdistribution m WHERE m.id = :methodOfDistributionID AND m.entityActive = :entityActive AND m.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Methodofdistribution.findByEntityActiveAndMethodAndIsCurrentVersion", query = "SELECT m FROM Methodofdistribution m WHERE m.method = :method AND m.entityActive = :entityActive AND m.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Methodofdistribution.findByEntityActiveAndIsCurrentVersion", query = "SELECT m FROM Methodofdistribution m WHERE m.entityActive = :entityActive AND m.isCurrentVersion = :isCurrentVersion"),
    
    @NamedQuery(name = "Methodofdistribution.findAll", query = "SELECT m FROM Methodofdistribution m"),
    @NamedQuery(name = "Methodofdistribution.findByEntityActive", query = "SELECT m FROM Methodofdistribution m WHERE m.entityActive = :entityActive"),
    @NamedQuery(name = "Methodofdistribution.findByMethodOfDistributionID", query = "SELECT m FROM Methodofdistribution m WHERE m.id = :methodOfDistributionID"),
    @NamedQuery(name = "Methodofdistribution.findByMethod", query = "SELECT m FROM Methodofdistribution m WHERE m.method = :method")})
public class Methodofdistribution extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Method")
    private String method;

    public Methodofdistribution() {
    }

    public Methodofdistribution(Integer methodOfDistributionID) {
        this.id = methodOfDistributionID;
    }

    public Methodofdistribution(Integer methodOfDistributionID, String method) {
        this.id = methodOfDistributionID;
        this.method = method;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Methodofdistribution)) {
            return false;
        }
        Methodofdistribution other = (Methodofdistribution) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getMethod();
    }
}
