/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.businessrules;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.Artefactatomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.ArtefactatomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import java.util.ResourceBundle;
import java.util.jar.Attributes;
import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
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
    
    @Inject
    private ArtefactatomicinformationController artefactatomicinformationController;
    @Inject 
    private ArtefactController artefactController;
    
    private boolean update = false;
    private DataModel<Artefactatomicinformation> oldItems;
    private int count;
    private Artefact oldArtefact;
    private Artefact newArtefact;
    
    public DataModel<Artefactatomicinformation> getOldItems() {
        return oldItems;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
    
    public String update() {
        String result = null;
        try {
             if (isUpdate()== true){
                newArtefact = artefactController.getCurrent();
                oldArtefact = artefactController.getOld();
                createDataModel();
             }              
            result = artefactController.update();
            
            if (isUpdate()== true){
                if(result.equals("/Faces/artefact/View")){
                    copyEntities();
                }
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
        setUpdate(false);
        return result;
    }
    
    public void createDataModel() {
        oldItems = new ListDataModel(artefactatomicinformationController.getSaveRetrieve().findByEntityActiveAndArtefactIDAndIsCurrentVersion(true, oldArtefact, true));
        count = oldItems.getRowCount();
    }    
    
    private void copyEntities (){
        for (int i = 0; i < count; i++) {
            getOldItems().setRowIndex(i);
            Artefactatomicinformation oldArtefactatomicinformation = (Artefactatomicinformation)getOldItems().getRowData();       
            artefactatomicinformationController.setCurrent(oldArtefactatomicinformation);
            artefactatomicinformationController.prepareUpdateVersion();
            artefactatomicinformationController.getCurrent().setArtefactID(newArtefact);
            artefactatomicinformationController.update();
        }
    }
}
