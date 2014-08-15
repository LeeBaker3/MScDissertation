/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities;

import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import java.io.Serializable;
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
    
    private com.mycompany.atomicinformationconfigurationmanager.entities.BaseSaveRetrieve ejbSaveRetrieve;
    
    public void setEntityActive(BaseEntity entity){
        entity.entityActive = true;
    }
    
    public void setEntityInActive(BaseEntity entity){
        entity.entityActive = false;
    }
}
