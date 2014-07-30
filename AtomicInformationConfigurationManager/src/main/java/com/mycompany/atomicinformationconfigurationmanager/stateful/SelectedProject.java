/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.stateful;

import com.mycompany.atomicinformationconfigurationmanager.entities.Project;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.inject.Named;

/**
 *
 * @author Lee Baker
 * 
 * This class is used to hold the currently selected project by the user
 * the variable project holds the reference to the Project Entity that has 
 * been selected.
 */
@Named("selectedProject")
@Stateful
public class SelectedProject implements Serializable {


    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    public void select(){
    }
    
}
