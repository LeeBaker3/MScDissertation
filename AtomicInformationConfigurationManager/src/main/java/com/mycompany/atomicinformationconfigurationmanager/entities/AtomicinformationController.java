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

@Named("atomicinformationController")
@SessionScoped
public class AtomicinformationController extends BaseController implements Serializable {

    private Atomicinformation current;
    private DataModel items = null;
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.AtomicinformationSaveRetrieve ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @Inject
    private  SelectedProject selectedProject;
    

    public AtomicinformationController() {
    }

    public Atomicinformation getSelected() {
        if (current == null) {
            current = new Atomicinformation();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AtomicinformationSaveRetrieve getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                
                /* 
                *   08/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return atomic infromation for selected project 
                */
                @Override
                public int getItemsCount() {
                    int localCount;
                    if (selectedProject.getProject()!=null){
                        localCount = getFacade().countEntityActiveAndProjectID(true, selectedProject.getProject());
                    }
                    else{
                        localCount = getFacade().countEntityActive(true);
                    }
                    return localCount;
                }
                
                /* 
                *   08/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return atomic infromation for selected project 
                */
                @Override
                public DataModel createPageDataModel() {
                    
                    if (selectedProject.getProject() != null){
                        return  new  ListDataModel(getFacade().findRangeEntityActiveAndProjectID(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true, selectedProject.getProject()));
                    }
                    else{
                      return new ListDataModel(getFacade().findRangeEntityActive(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true));  
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
        current = (Atomicinformation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Atomicinformation();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            /*  
            *   02/08/14 @Lee Baker
            *   If a project has been selected then create new AtomicInfromation with a reference selected Project
            */
            if(selectedProject.getProject() !=null){
                current.setProjectID(selectedProject.getProject());
            }
            setEntityActive(current);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Atomicinformation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Atomicinformation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /*  
    *   08/08/14 @Lee Baker
    *   Code added to disable entity instead of destroying it
    */   
    public String disbale() {
        current = (Atomicinformation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        perfromDisable();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String disableAndView() {
        perfromDisable();
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

    private void perfromDisable() {
        setEntityInActive(current);
        try {
            getFacade().entityInactive(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationDisabled"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    
    private void updateCurrentItem() {
        int count;
        if (selectedProject.getProject() != null){
             count = getFacade().countEntityActiveAndProjectID(true, selectedProject.getProject());
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
            if (selectedProject.getProject() != null){
                current = getFacade().findRangeEntityActiveAndProjectID(new int[]{selectedItemIndex, selectedItemIndex + 1},true, selectedProject.getProject()).get(0);
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
        return JsfUtil.getSelectItems(ejbFacade.findAllEntityActive(true), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllEntityActive(true), true);
    }

    public Atomicinformation getAtomicinformation(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Atomicinformation.class)
    public static class AtomicinformationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AtomicinformationController controller = (AtomicinformationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "atomicinformationController");
            return controller.getAtomicinformation(getKey(value));
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
            if (object instanceof Atomicinformation) {
                Atomicinformation o = (Atomicinformation) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Atomicinformation.class.getName());
            }
        }

    }

}
