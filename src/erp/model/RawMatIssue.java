/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;



public class RawMatIssue implements Serializable {

       private String materialType;
       private String materialSubType;
       private double quantity;
       private boolean completed;

    public RawMatIssue(String materialType, String materialSubType, double quantity, boolean completed) {
        this.materialType = materialType;
        this.materialSubType = materialSubType;
        this.quantity = quantity;
        this.completed = completed;
    }

    /**
     * @return the materialType
     */
    public String getMaterialType() {
        return materialType;
    }

    /**
     * @param materialType the materialType to set
     */
    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    /**
     * @return the materialSubType
     */
    public String getMaterialSubType() {
        return materialSubType;
    }

    /**
     * @param materialSubType the materialSubType to set
     */
    public void setMaterialSubType(String materialSubType) {
        this.materialSubType = materialSubType;
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
       
     
    
}
