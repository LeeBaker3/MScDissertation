/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.stateful;

import java.util.jar.Attributes;
import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.naming.Name;

/**
 *
 * @author Lee Baker
 */
@Stateless
@Named("updateArtefactAtomicInformationLinks")

public class UpdateArtefactAtomicInformationLinks {

    public UpdateArtefactAtomicInformationLinks() {
    }
    
    public boolean update = false;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

}
