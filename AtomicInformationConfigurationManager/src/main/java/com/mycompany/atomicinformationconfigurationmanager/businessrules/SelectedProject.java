/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.businessrules;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.ArtefactatomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution.ArtefactdistributionController;
import com.mycompany.atomicinformationconfigurationmanager.entities.distributionrecipient.DistributionrecipientController;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.ProjectController;
import java.io.Serializable;
import java.util.Objects;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Lee Baker
 * 
 * This class is used to hold a reference to currently selected project by the user
 */
@Named("selectedProject")
@Stateful
@SessionScoped
public class SelectedProject implements Serializable {

    @Inject
    private ProjectController projectController;
    @Inject
    private ArtefactController artefactController;
    @Inject
    private ArtefactatomicinformationController artefactatomicinformationController;
    @Inject
    private DistributionrecipientController distributionrecipientController;
    @Inject
    private ArtefactdistributionController artefactdistributionController;
    
            
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        if(project != projectController.getCurrent()){
            projectController.setCurrent(project);
            artefactController.recreateModel();
            artefactatomicinformationController.recreateModel();
            distributionrecipientController.recreateModel();
            artefactatomicinformationController.recreateModel(); 
            artefactdistributionController.recreateModel();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.project);
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
        final SelectedProject other = (SelectedProject) obj;
        if (!Objects.equals(this.project, other.project)) {
            return false;
        }
        return true;
    }
    
    
}
