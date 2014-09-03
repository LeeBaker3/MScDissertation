package com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.PaginationHelper;
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
import sun.misc.VM;

@Named("artefactdistributionController")
@SessionScoped
public class ArtefactdistributionController extends BaseController implements Serializable {

    private Artefactdistribution current;
    private boolean  itemSelected = false; //Set to true when an item is selected in the List DataTable
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
    private ArtefactController artefactController;
    
    private Boolean selectedFromArtefact = false;

    public ArtefactdistributionController() {
    }

    public Artefactdistribution getSelected() {
        if (current == null) {
            current = new Artefactdistribution();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    public void setSelected(ValueChangeEvent event){
        current = (Artefactdistribution) getItems().getRowData();
        itemSelected = true;
    }
    
    private String prepareSelected(String jsfPage){
        try {
            if (itemSelected == false)
                {
                    current = (Artefactdistribution) getItems().getRowData();
                }
                itemSelected = false;
                selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
                return jsfPage;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("NoRecordSelectedError"));
            return null;
        }
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
                    if (artefactController.getCurrent() !=null){
                        localCount = getSaveRetrieve().countEntityActiveAndArtefactIDIAndsCurrentVersion(artefactController.getCurrent(), true, true);
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
                    if (artefactController.getCurrent() !=null){
                        return  new ListDataModel(getSaveRetrieve().findRangeEntityActiveAndArtefactIDAndIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true, artefactController.getCurrent(), true));
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
        return "/Faces/artefactdistribution/List";
    }

    public String prepareView() {
        return prepareSelected("/Faces/artefactdistribution/View");
    }

    public String prepareCreate() {
        current = new Artefactdistribution();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        return "/Faces/artefactdistribution/Create";
    }
    
    public String prepareCreateFromArtefact() {
        current = new Artefactdistribution();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        selectedFromArtefact = true;
        return "/Faces/artefactdistribution/CreateFromArtefact";
    }

    public String create(String returnMethod) {
        try {
            /*  
            *   10/08/14 @Lee Baker
            *   If a artefact has been selected then create new ArtefactDistribution with a reference to the selected Artefact
            *   and set entityActive when created
            */ 
            if(selectedFromArtefact==true){
                current.setArtefactID(artefactController.getCurrent());
                selectedFromArtefact=false;
            }
            setEntityActive(current);
            getSaveRetrieve().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactdistributionCreated"));
            
            /*
            *   13/08/14 @Lee Baker
            *   Code modified to return from multiple calling xhtml pages
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
            selectedFromArtefact=false;
            return null;
        }
    }

    public String prepareEdit() {
        return prepareSelected("Edit");
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
    
    /* 31/08/14 Remarked out never used IDE Code
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
    */
    
    /*  
    *   10/08/14 @Lee Baker
    *   Code added to delete entity instead of destroying it
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactdistributionDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getSaveRetrieve().count();
        if (artefactController.getCurrent() !=null){
            count = getSaveRetrieve().countEntityActiveAndArtefactIDIAndsCurrentVersion(artefactController.getCurrent(), true, true);
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
            if(artefactController.getCurrent()!=null){
                current = getSaveRetrieve().findRangeEntityActiveAndArtefactIDAndIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1}, true, artefactController.getCurrent(), true).get(0);
            }
            current = getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, true).get(0);
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
