/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.model;

import java.io.Serializable;


/**
 *
 * @author Ashiquzzaman
 */

public class RawMatReq implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private int id;
    private String material_type;
    private String material_sub_type;
    private double total_qty;
    private boolean completed;

    public RawMatReq(int id, String material_type, String material_sub_type, double total_qty, boolean completed) {
        this.id = id;
        this.material_type = material_type;
        this.material_sub_type = material_sub_type;
        this.total_qty = total_qty;
        this.completed = completed;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the material_type
     */
    public String getMaterial_type() {
        return material_type;
    }

    /**
     * @param material_type the material_type to set
     */
    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    /**
     * @return the material_sub_type
     */
    public String getMaterial_sub_type() {
        return material_sub_type;
    }

    /**
     * @param material_sub_type the material_sub_type to set
     */
    public void setMaterial_sub_type(String material_sub_type) {
        this.material_sub_type = material_sub_type;
    }

    /**
     * @return the total_qty
     */
    public double getTotal_qty() {
        return total_qty;
    }

    /**
     * @param total_qty the total_qty to set
     */
    public void setTotal_qty(double total_qty) {
        this.total_qty = total_qty;
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
