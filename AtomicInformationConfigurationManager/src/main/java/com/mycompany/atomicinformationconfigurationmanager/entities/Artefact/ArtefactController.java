package com.mycompany.atomicinformationconfigurationmanager.entities.Artefact;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseController;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.ProjectController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.PaginationHelper;
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
import javax.faces.event.ValueChangeEvent;
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
    private Artefact old;
    private boolean  updating = false; //Set to true when making changes that update the version
    private boolean  itemSelected = false; //Set to true when an item is selected in the List DataTable

    private DataModel items = null;
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactSaveRetrieve ejbSaveRetrieve;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    /*  @Lee Baker
    *   02/08/14
    *   Added to get current selected project
    */
    
    @Inject
    private ProjectController projectController;

    public ArtefactController() {
    }
    
    public boolean isUpdating() {
        return updating;
    }

    public void setUpdating(boolean updating) {
        this.updating = updating;
    }
    
    public Artefact getCurrent() {
        return current;
    }

    public void setCurrent(Artefact current) {
        this.current = current;
    }
    
    public Artefact getOld() {
        return old;
    }

    public void setOld(Artefact old) {
        this.old = old;
    }

    public Artefact getSelected() {
        if (current == null) {
            current = new Artefact();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    public void setSelected(ValueChangeEvent event){
        current = (Artefact) getItems().getRowData();
        itemSelected = true;
    }
    
    private String prepareSelected(String jsfPage){
        try {
            if (itemSelected == false)
                {
                    current = (Artefact) getItems().getRowData();
                }
                itemSelected = false;
                selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
                return jsfPage;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("NoRecordSelectedError"));
            return null;
        }
    }

    private ArtefactSaveRetrieve getSaveRetrieve() {
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
                        if (projectController.getCurrent()!= null){
                            localCount = getSaveRetrieve().countEntityActiveAndProjectIDAndIsCurrentVersion(projectController.getCurrent(), true, true);
                            }
                        else {
                            localCount = getSaveRetrieve().countEntityActive(true, true);
                        }
                    return localCount;
                }
 
                /* 
                *   02/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return artefacts for selected project 
                */
                @Override
                public DataModel createPageDataModel(){
                if (projectController.getCurrent() !=null){
                        return new ListDataModel(getSaveRetrieve().findRangeEntityActiveAndProjectIDAndIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true,projectController.getCurrent(), true));
                    }
                else {
                        return new ListDataModel(getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()},true, true));
                    }
                }
            };
        }                
        return pagination;
    }
         
    public String prepareList() {
        recreateModel();
        itemSelected = false;
        return "List";
    }

    public String prepareView() {
        return prepareSelected("/Faces/artefact/View");
    }

    public String prepareCreate() {
        current = new Artefact();
        current.setIsCurrentVersion(true);
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
            if(projectController.getCurrent() != null){
                current.setProjectID(projectController.getCurrent());
            }
            setEntityActive(current);
            getSaveRetrieve().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String prepareEdit() {
        return prepareSelected("Edit");
    }
    
    /*
    *   24/08/14 @Lee Baker
    *   When recreationg a new artefact copies details of current artefact to a new
    *   new artefact and set the old one to not current.
    */
    public String prepareUpdateVersion(){
        updating = true;
        old = current;
        prepareVersion(old, ejbSaveRetrieve);
        current = new Artefact();
        copy(old, current);
        return "Edit";
    }
    
    public Artefact copy(Artefact oldArtefact, Artefact newArtefact){
        newArtefact.setArtefactMajorVersionNumber(oldArtefact.getArtefactMajorVersionNumber());
        newArtefact.setArtefactMinorVersionNumber(oldArtefact.getArtefactMinorVersionNumber());
        newArtefact.setArtefactName(oldArtefact.getArtefactName());
        return newArtefact;
    }

    public String update() {
        try {
            if(projectController.getCurrent() != null){
                current.setProjectID(projectController.getCurrent());
            }
            
            if (updating == true){
                manageVersion(old, current);
                getSaveRetrieve().create(current);
                updating = false;
            }
            else{
                getSaveRetrieve().edit(current);
            }
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            if (updating == true){
                rollBackVersion(old, ejbSaveRetrieve);
                updating = false;
                current = old;
            }
            return null;
        }
    }
    
    /* 31/08/14 Remarked out never used IDE Code
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
            getSaveRetrieve().remove(current);
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
    */
    
    /*  
    *   02/08/14 @Lee Baker
    *   Code added to delete entity instead of destroying it
    */   
    public String delete(){
        String result;
        result = prepareSelected("List");
        if (result == "List"){
            performDelete();
            recreatePagination();
            recreateModel();
        }
        return result;
    }

    public String deleteAndView() {
        performDelete();
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

    private void performDelete() {
        setEntityInActive(current);
        try {
            getSaveRetrieve().entityInactive(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count;
         if (projectController.getCurrent() != null){
             count = getSaveRetrieve().countEntityActiveAndProjectIDAndIsCurrentVersion(projectController.getCurrent(), true, true);
         }
         else {
             count = getSaveRetrieve().countEntityActive(true, true);
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
            if (projectController.getCurrent() != null){
                current = getSaveRetrieve().findRangeEntityActiveAndProjectIDAndIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true,projectController.getCurrent(),true).get(0);
            }
            else {
                current = getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, true).get(0);
            }
        }
    }

    public DataModel getItems() {
        if (items == null){
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    /* 
    *   End of modified IDE code
    */   
    
    public void recreateModel() {
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
