/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.model;

/**
 *
 * @author Ashiquzzaman
 */
public class ProductReq {
    private String productName;
    private String productCategory;
    private String materialType;
    private String materialSubType;
    private double reqQty;

    public ProductReq(String productName, String productCategory, String materialType, String materialSubType, double reqQty) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.materialType = materialType;
        this.materialSubType = materialSubType;
        this.reqQty = reqQty;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productCategory
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * @param productCategory the productCategory to set
     */
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
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
     * @return the reqQty
     */
    public double getReqQty() {
        return reqQty;
    }

    /**
     * @param reqQty the reqQty to set
     */
    public void setReqQty(double reqQty) {
        this.reqQty = reqQty;
    }
    
}
