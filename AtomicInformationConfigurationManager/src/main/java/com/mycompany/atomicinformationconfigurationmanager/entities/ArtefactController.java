package com.mycompany.atomicinformationconfigurationmanager.entities;

import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.PaginationHelper;
import com.mycompany.atomicinformationconfigurationmanager.stateful.SelectedProject;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import sun.misc.VM;

@Named("artefactController")
@SessionScoped
public class ArtefactController extends BaseController implements Serializable {

    private Artefact current;
    private DataModel items = null;
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.ArtefactFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @Inject 
    private SelectedProject selectedProject;   

    public ArtefactController() {
    }

    public Artefact getSelected() {
        if (current == null) {
            current = new Artefact();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ArtefactFacade getFacade() {
        return ejbFacade;
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
                        if (selectedProject != null){
                            localCount = getFacade().countEntityActiveAndProjectID(selectedProject.getProject(), true);
                            }
                        else {
                            localCount = getFacade().countEntityActive(true);
                        }
                    return localCount;
                }
 
                /* 
                *   02/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return artefacts for selected project 
                */
                @Override
                public DataModel createPageDataModel(){
                        
                if (selectedProject !=null){
                        return new ListDataModel(getFacade().findByEntityActiveAndProjectID(selectedProject.getProject(), true));
                    }
                else {
                        return new ListDataModel(getFacade().findRangeEntityActive(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()},true));
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
            */
            if(selectedProject != null){
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
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
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

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
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
         if (selectedProject != null){
             count = getFacade().countEntityActiveAndProjectID(selectedProject.getProject(), true);
         }
         else {
             count = getFacade().countEntityActive(true);
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
            if (selectedProject != null){
                current = getFacade().findRangeEntityActiveAndProjectID(new int[]{selectedItemIndex, selectedItemIndex + 1},true,selectedProject.getProject()).get(0);
            }
            else {
                current = getFacade().findRangeEntityActive(new int[]{selectedItemIndex, selectedItemIndex + 1},true).get(0);
            }
            
        }
    }

    public DataModel getItems() {
            items = getPagination().createPageDataModel();
        return items;
    }

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
        return JsfUtil.getSelectItems(ejbFacade.findAllEntityActive(true), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllEntityActive(true), true);
    }

    public Artefact getArtefact(java.lang.Integer id) {
        return ejbFacade.find(id);
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
