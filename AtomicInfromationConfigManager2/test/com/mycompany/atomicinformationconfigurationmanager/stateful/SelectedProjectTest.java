/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.stateful;

import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lee Baker
 */
public class SelectedProjectTest {
    
    public SelectedProjectTest() {
    }
    
    private SelectedProject selectedProject;
    private Project project;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("* SelectedProject JUnit4Test: Start");
    }
    
    @AfterClass
    public  static void  tearDownClass() throws Exception {
        System.out.println("* SelectedProject JUnit4Test: Finish");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetProject(){
        System.out.println("* SelectedProject JUnit4Test: Test getProject Method");
        int projectID = 001;
        int versionNumber = 1;
        
        selectedProject = new SelectedProject();
        project = new Project(projectID, versionNumber, true, true);
        project.setProjectName("Test getProject Method");
        project.setProjectReference("001");
        selectedProject.setProject(project);
        
        assertEquals(project, this.selectedProject.getProject());
    }

    @Test
    public void testSetProject() throws Exception {

    }
    
}
