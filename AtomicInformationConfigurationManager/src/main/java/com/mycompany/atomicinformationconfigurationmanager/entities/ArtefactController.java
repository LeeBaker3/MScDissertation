package com.mycompany.atomicinformationconfigurationmanager.entities;

import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.PaginationHelper;
import com.mycompany.atomicinformationconfigurationmanager.stateful.SelectedArtefact;
import com.mycompany.atomicinformationconfigurationmanager.stateful.SelectedProject;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import sun.misc.VM;

@Named("artefactController")
@SessionScoped
public class ArtefactController extends BaseController implements Serializable {

    private Artefact current;
    private Artefact oldArtefact;

    private DataModel items = null;
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.ArtefactSaveRetrieve ejbSaveRetrieve;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    /*  @Lee Baker
    *   02/08/14
    *   Added to get current selected project
    */
    @Inject 
    private SelectedProject selectedProject; 

    public ArtefactController() {
    }
    
    public Artefact getCurrent() {
        return current;
    }

    public void setCurrent(Artefact current) {
        this.current = current;
    }

    public Artefact getSelected() {
        if (current == null) {
            current = new Artefact();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ArtefactSaveRetrieve getFacade() {
        return ejbSaveRetrieve;
    }
         
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                /* 
                *   03/08/14 @Lee Baker
                *   IDE Code modified to use countEntityActive() instead of count()
                */   
                @Override
                public int getItemsCount() {
                    int localCount;
                        if (selectedProject.getProject() != null){
                            localCount = getFacade().countEntityActiveAndProjectIDAndIsCurrentVersion(selectedProject.getProject(), true, true);
                            }
                        else {
                            localCount = getFacade().countEntityActive(true, true);
                        }
                    return localCount;
                }
 
                /* 
                *   02/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return artefacts for selected project 
                */
                @Override
                public DataModel createPageDataModel(){
                if (selectedProject.getProject() !=null){
                        return new ListDataModel(getFacade().findRangeEntityActiveAndProjectIDAndIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true,selectedProject.getProject(), true));
                    }
                else {
                        return new ListDataModel(getFacade().findRangeEntityActiveIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()},true, true));
                    }
                }
            };
        }                
        return pagination;
    }
         
    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Artefact) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Artefact();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            /*  
            *   02/08/14 @Lee Baker
            *   If a project has been selected then create new Artefact with a reference selected Project
            *   and set entityActive when created
            */ 
            if(selectedProject.getProject() != null){
                current.setProjectID(selectedProject.getProject());
            }
            setEntityActive(current);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Artefact) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return prepareUpdate();
    }
    
    public String prepareUpdate(){
        oldArtefact = current;
        prepareVersion(oldArtefact, ejbSaveRetrieve);
        return "Edit";
    }

    public String update() {
        try {
            manageVersion(oldArtefact, current);

            if(selectedProject.getProject() != null){
                current.setProjectID(selectedProject.getProject());
            }
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            rollBackVersion(oldArtefact, ejbSaveRetrieve);
            return null;
        }
    }

    public String destroy() {
        current = (Artefact) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }
    
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }
    
    /*  
    *   02/08/14 @Lee Baker
    *   Code added to disable entity instead of destroying it
    */   
    public String disable(){
        current = (Artefact) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDisable();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String disableAndView() {
        performDisable();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDisable() {
        setEntityInActive(current);
        try {
            getFacade().entityInactive(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactDisabled"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count;
         if (selectedProject.getProject() != null){
             count = getFacade().countEntityActiveAndProjectIDAndIsCurrentVersion(selectedProject.getProject(), true, true);
         }
         else {
             count = getFacade().countEntityActive(true, true);
         }
        
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            if (selectedProject.getProject() != null){
                current = getFacade().findRangeEntityActiveAndProjectIDAndIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true,selectedProject.getProject(),true).get(0);
            }
            else {
                current = getFacade().findRangeEntityActiveIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, true).get(0);
            }
        }
    }

    public DataModel getItems() {
            items = getPagination().createPageDataModel();
        return items;
    }
    /* 
    *   End of modified IDE code
    */   
    
    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbSaveRetrieve.findAllEntityActiveIsCurrentVersion(true, true), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbSaveRetrieve.findAllEntityActiveIsCurrentVersion(true, true), true);
    }

    public Artefact getArtefact(java.lang.Integer id) {
        return ejbSaveRetrieve.find(id);
    }

    @FacesConverter(forClass = Artefact.class)
    public static class ArtefactControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArtefactController controller = (ArtefactController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "artefactController");
            return controller.getArtefact(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Artefact) {
                Artefact o = (Artefact) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Artefact.class.getName());
            }
        }

    }

}
