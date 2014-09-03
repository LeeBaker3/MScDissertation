/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.businessrules;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.ArtefactatomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution.ArtefactdistributionController;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.AtomicinformationController;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Lee Baker
 * 
 * This class is used to hold a reference to the currently selected artefact by the user
 */
@Named("returnToJSFPage")
@Stateless
public class ReturnToJSFPage {
    
    @Inject
    private ArtefactatomicinformationController artefactatomicinformationController;
    @Inject
    private ArtefactdistributionController artefactdistributionController;
    @Inject
    private AtomicinformationController atomicinformationController;
    
    public String returnToArtefactView(){
        artefactatomicinformationController.recreateModel();
        artefactdistributionController.recreateModel();
        return "/Faces/artefact/View";
    }
    
    public String returnToArtefactAtomicinformationCreate(){
        atomicinformationController.recreateModel();
        return "/Faces/artefactatomicinformation/CreateFromArtefact";
    }
}
