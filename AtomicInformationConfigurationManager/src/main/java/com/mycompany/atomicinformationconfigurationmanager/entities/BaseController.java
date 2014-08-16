/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities;

import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author Lee Baker
 */
@Named("baseController")
@SessionScoped
public class BaseController implements Serializable{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void setEntityActive(BaseEntity entity){
        entity.entityActive = true;
    }
    
    public void setEntityInActive(BaseEntity entity){
        entity.entityActive = false;
    }
    
    
    public void updateVersionNumber(BaseEntity oldEntity, BaseEntity newEntity){
        newEntity.setVersionNumber(oldEntity.getVersionNumber() + 1);
    }
    
    /*  16/08/2014 @Lee Baker
    *   Copies properties of the one object to another
    *   and sets the id to null so it can be set when pushed to the database
    *   and increaments the version number.
    */
    public void cloneEntity(BaseEntity oldEntity, BaseEntity newEntity){
        try {
            newEntity = (BaseEntity) oldEntity.clone();
            updateVersionNumber(oldEntity, newEntity);
            newEntity.setId(null);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
