/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ashiquzzaman
 */

public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private String materialType;

    private String materialSubType;
    private double quantity;
    private double pricePerUnit;
    private String unit;

    public Inventory() {
    }


    public Inventory(String materialType_, String materialSubType_, double quantity_, double pricePerUnit_, String unit_) {

        this.materialType = materialType_;
        this.materialSubType = materialSubType_;
        this.quantity = quantity_;  
        this.pricePerUnit = pricePerUnit_;
        this.unit = unit_;
    }


    @Override
    public String toString() {
        return "erp.model.Inventory[ materialType=" + materialType + " ]";
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
     * @return the pricePerUnit
     */
    public double getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * @param pricePerUnit the pricePerUnit to set
     */
    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
}
