package com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.Artefactatomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseController;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.ProjectController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.PaginationHelper;
import com.mycompany.atomicinformationconfigurationmanager.businessrules.SelectedProject;
import java.io.Serializable;
import java.util.ResourceBundle;
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

@Named("atomicinformationController")
@SessionScoped
public class AtomicinformationController extends BaseController implements Serializable {

    private Atomicinformation current;
    private Atomicinformation old;
    private boolean updating = false;
    private boolean  itemSelected = false; //Set to true when an item is selected in the List DataTable
    
    private DataModel items = null;
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.AtomicinformationSaveRetrieve ejbSaveRetrieve;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @Inject
    private ProjectController projectController;
    

    public AtomicinformationController() {
    }

    public Atomicinformation getCurrent() {
        return current;
    }

    public void setCurrent(Atomicinformation current) {
        this.current = current;
    }

    public Atomicinformation getOld() {
        return old;
    }

    public void setOld(Atomicinformation old) {
        this.old = old;
    }

    public boolean isUpdating() {
        return updating;
    }

    public void setUpdating(boolean updating) {
        this.updating = updating;
    }

    public Atomicinformation getSelected() {
        if (current == null) {
            current = new Atomicinformation();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    public void setSelected(ValueChangeEvent event){
        current = (Atomicinformation) getItems().getRowData();
        itemSelected = true;
    }
    
    private String prepareSelected(String jsfPage){
        try {
            if (itemSelected == false)
                {
                    current = (Atomicinformation) getItems().getRowData();
                }
                itemSelected = false;
                selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
                return jsfPage;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("NoRecordSelectedError"));
            return null;
        }
    }

    private AtomicinformationSaveRetrieve getSaveRetrieve() {
        return ejbSaveRetrieve;
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
                    if (projectController.getCurrent()!=null){
                        localCount = getSaveRetrieve().countEntityActiveAndProjectIDAndIsCurrentVersion(true, projectController.getCurrent(), true);
                    }
                    else{
                        localCount = getSaveRetrieve().countEntityActive(true, true);
                    }
                    return localCount;
                }
                
                /* 
                *   08/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return atomic infromation for selected project 
                */
                @Override
                public DataModel createPageDataModel() {
                    
                    if (projectController.getCurrent() != null){
                        return  new  ListDataModel(getSaveRetrieve().findRangeEntityActiveAndProjectIDAndISCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true, projectController.getCurrent(), true));
                    }
                    else{
                      return new ListDataModel(getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true, true));  
                    }
                    
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        itemSelected = false;
        return "/Faces/atomicinformation/List";
    }

    public String prepareView() {
        if (itemSelected == false)
        {
            current = (Atomicinformation) getItems().getRowData();
        }
        itemSelected = false;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/Faces/atomicinformation/View";
    }

    public String prepareCreate() {
        current = new Atomicinformation();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        return "/Faces/atomicinformation/Create";
    }
    
    /*  @Lee baker
    *   10/08/2014 Added for navigation from  artefactatomicinformation/Create/xhtml
    */
    public String prepareCreateFormArtefactAtomicInformation() {
        current = new Atomicinformation();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        return "/Faces/atomicinformation/CreateFromArtefactAtomicInformation";
    }
    
    public String create(String returnMethod) {
        try {
            /*  
            *   02/08/14 @Lee Baker
            *   If a project has been selected then create new AtomicInfromation with a reference selected Project
            */
            if(projectController.getCurrent() !=null){
                current.setProjectID(projectController.getCurrent());
            }
            setEntityActive(current);
            getSaveRetrieve().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationCreated"));
            
            /*
            *   13/08/14 @Lee Baker
            *   Code modified to return from multiple calling xHtml pages
            */
            if ("prepareCreate".equals(returnMethod)){
                return prepareCreate();
            }
            if ("prepareCreateFormArtefactAtomicInformation".equals(returnMethod)){
                return prepareCreateFormArtefactAtomicInformation();
            }
            else{
               return prepareCreate(); 
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        return prepareSelected("/Faces/atomicinformation/Edit");
    }

    /*
    *   24/08/14 @Lee Baker
    *   When recreationg a new atomicinformation copies details of current atomicinformation to a new
    *   new atomicinformation and set the old one to not current.
    */
     public String prepareUpdateVersion(){
        updating = true;
        old = current;
        prepareVersion(old, ejbSaveRetrieve);
        current = new Atomicinformation();
        copy(old, current);
        return "/Faces/atomicinformation/Edit";
    }
     
    public Atomicinformation copy(Atomicinformation oldAtomicinformation, Atomicinformation newAtomicinformation){
        newAtomicinformation.setContent(oldAtomicinformation.getContent());
        newAtomicinformation.setTypeOfAtomicInformationID(oldAtomicinformation.getTypeOfAtomicInformationID());
        newAtomicinformation.setProjectID(oldAtomicinformation.getProjectID());
        return newAtomicinformation;
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
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationUpdated"));
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
    
    /*   31/08/14 Remarked out never used IDE Code
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
            getSaveRetrieve().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    */

    /*  
    *   08/08/14 @Lee Baker
    *   Code added to disable entity instead of destroying it
    */   
    public String delete() {
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    
    private void updateCurrentItem() {
        int count;
        if (projectController.getCurrent() != null){
             count = getSaveRetrieve().countEntityActiveAndProjectIDAndIsCurrentVersion(true, projectController.getCurrent(), true);
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
                current = getSaveRetrieve().findRangeEntityActiveAndProjectIDAndISCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, projectController.getCurrent(), true).get(0);
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

    public Atomicinformation getAtomicinformation(java.lang.Integer id) {
        return ejbSaveRetrieve.find(id);
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
