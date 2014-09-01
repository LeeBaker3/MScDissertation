/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities;

import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.ProjectController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.PaginationHelper;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;
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
public class ProjectControllerTest {
    
    public ProjectControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetSelected() {
        System.out.println("getSelected");
        ProjectController instance = new ProjectController();
        Project expResult = null;
        Project result = instance.getSelected();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPagination() {
        System.out.println("getPagination");
        ProjectController instance = new ProjectController();
        PaginationHelper expResult = null;
        PaginationHelper result = instance.getPagination();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrepareList() {
        System.out.println("prepareList");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.prepareList();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrepareView() {
        System.out.println("prepareView");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.prepareView();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrepareCreate() {
        System.out.println("prepareCreate");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.prepareCreate();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreate() {
        System.out.println("create");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.create();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrepareEdit() {
        System.out.println("prepareEdit");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.prepareEdit();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.update();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
    /*  Destroy Methods No Longer Used 31/08/14
    @Test
    public void testDestroy() {
        System.out.println("destroy");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.destroy();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDestroyAndView() {
        System.out.println("destroyAndView");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.destroyAndView();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    */
    
    @Test
    public void testDelete() {
        System.out.println("delete");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.delete();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteAndView() {
        System.out.println("deleteAndView");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.deleteAndView();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetItems() {
        System.out.println("getItems");
        ProjectController instance = new ProjectController();
        DataModel expResult = null;
        DataModel result = instance.getItems();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testNext() {
        System.out.println("next");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.next();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrevious() {
        System.out.println("previous");
        ProjectController instance = new ProjectController();
        String expResult = "";
        String result = instance.previous();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetItemsAvailableSelectMany() {
        System.out.println("getItemsAvailableSelectMany");
        ProjectController instance = new ProjectController();
        SelectItem[] expResult = null;
        SelectItem[] result = instance.getItemsAvailableSelectMany();
        assertArrayEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetItemsAvailableSelectOne() {
        System.out.println("getItemsAvailableSelectOne");
        ProjectController instance = new ProjectController();
        SelectItem[] expResult = null;
        SelectItem[] result = instance.getItemsAvailableSelectOne();
        assertArrayEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetProject() {
        System.out.println("getProject");
        Integer id = null;
        ProjectController instance = new ProjectController();
        Project expResult = null;
        Project result = instance.getProject(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
