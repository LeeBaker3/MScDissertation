package com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.PaginationHelper;
import com.mycompany.atomicinformationconfigurationmanager.stateful.SelectedArtefact;

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

@Named("artefactdistributionController")
@SessionScoped
public class ArtefactdistributionController extends BaseController implements Serializable {

    private Artefactdistribution current;
    private DataModel items = null;
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution.ArtefactdistributionSaveRetrieve ejbSaveRetrieve;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
   
    /*  @Lee Baker
    *   10/08/14
    *   Added to get current selected project
    */
    @Inject
    private SelectedArtefact selectedArtefact;

    public ArtefactdistributionController() {
    }

    public Artefactdistribution getSelected() {
        if (current == null) {
            current = new Artefactdistribution();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ArtefactdistributionSaveRetrieve getSaveRetrieve() {
        return ejbSaveRetrieve;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                
                /* 
                *   10/08/14 @Lee Baker
                *   IDE Code modified to use countEntityActive() instead of count()
                */     
                @Override
                public int getItemsCount() {
                    int localCount;
                    if (selectedArtefact.getArtefact() !=null){
                        localCount = getSaveRetrieve().countEntityActiveAndArtefactIDIsCurrentVersion(selectedArtefact.getArtefact(), true, true);
                    }
                    else {
                        localCount = getSaveRetrieve().countEntityActive(true, true);
                    }
                    return localCount;
                }

                /* 
                *   10/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return artefacts for selected project 
                */
                @Override
                public DataModel createPageDataModel() {
                    if (selectedArtefact.getArtefact() !=null){
                        return  new ListDataModel(getSaveRetrieve().findRangeEntityActiveAndArtefactIDAndIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true, selectedArtefact.getArtefact(), true));
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
        return "List";
    }

    public String prepareView() {
        current = (Artefactdistribution) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Artefactdistribution();
        selectedItemIndex = -1;
        return "Create";
    }
    
    public String prepareCreateFromArtefact() {
        current = new Artefactdistribution();
        selectedItemIndex = -1;
        return "/Faces/artefactdistribution/CreateFromArtefact";
    }

    public String create(String returnMethod) {
        try {
            /*  
            *   10/08/14 @Lee Baker
            *   If a artefact has been selected then create new ArtefactDistribution with a reference to the selected Artefact
            *   and set entityActive when created
            */ 
            if(selectedArtefact.getArtefact()!= null){
                current.setArtefactID(selectedArtefact.getArtefact());
            }
            setEntityActive(current);
            getSaveRetrieve().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactdistributionCreated"));
            
            /*
            *   13/08/14 @Lee Baker
            *   Code modified to return from multiple calling xHtml pages
            */
            if ("prepareCreate".equals(returnMethod)){
                return prepareCreate();
            }
            if ("prepareCreateFromArtefact".equals(returnMethod)){
                return prepareCreateFromArtefact();
            }
            else {
                return prepareCreate();
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Artefactdistribution) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getSaveRetrieve().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactdistributionUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Artefactdistribution) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactdistributionDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    /*  
    *   10/08/14 @Lee Baker
    *   Code added to disable entity instead of destroying it
    */   
    public String disable() {
        current = (Artefactdistribution) getItems().getRowData();
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
            getSaveRetrieve().entityInactive(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactdistributionDisabled"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getSaveRetrieve().count();
        if (selectedArtefact.getArtefact() !=null){
            count = getSaveRetrieve().countEntityActiveAndArtefactIDIsCurrentVersion(selectedArtefact.getArtefact(), true, true);
        }
        else{
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
            if(selectedArtefact.getArtefact()!=null){
                current = getSaveRetrieve().findRangeEntityActiveAndArtefactIDAndIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1}, true, selectedArtefact.getArtefact(), true).get(0);
            }
            current = getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, true).get(0);
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

    public Artefactdistribution getArtefactdistribution(java.lang.Integer id) {
        return ejbSaveRetrieve.find(id);
    }

    @FacesConverter(forClass = Artefactdistribution.class)
    public static class ArtefactdistributionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArtefactdistributionController controller = (ArtefactdistributionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "artefactdistributionController");
            return controller.getArtefactdistribution(getKey(value));
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
            if (object instanceof Artefactdistribution) {
                Artefactdistribution o = (Artefactdistribution) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Artefactdistribution.class.getName());
            }
        }

    }
}
