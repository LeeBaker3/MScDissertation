/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.stateful;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import java.util.Objects;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lee Baker
 * 
 * This class is used to hold a reference to the currently selected artefact by the user
 */
@Named("selectedArtefact")
@Stateful
@SessionScoped
public class SelectedArtefact {
    
    private Artefact artefact;

    public Artefact getArtefact() {
        return artefact;
    }

    public void setArtefact(Artefact artefact) {
        this.artefact = artefact;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.artefact);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SelectedArtefact other = (SelectedArtefact) obj;
        if (!Objects.equals(this.artefact, other.artefact)) {
            return false;
        }
        return true;
    }

}
