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
public class OrderDetail implements Serializable {

    private int id;
    private String clientName;
    private String address;
    private String orderDate;
    private String delevaryDate;
    private String finishedProduct;
    private String productCategory;
    private String desighUrl;
    private double quantity;
    private boolean completed;

    public OrderDetail() {
    }

    public OrderDetail(int id, String clientName, String address, String orderDate, String delevaryDate, String finishedProduct, String productCategory, String desighUrl, double quantity, boolean completed) {
        this.id = id;
        this.clientName = clientName;
        this.address = address;
        this.orderDate = orderDate;
        this.delevaryDate = delevaryDate;
        this.finishedProduct = finishedProduct;
        this.productCategory = productCategory;
        this.desighUrl = desighUrl;
        this.quantity = quantity;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDelevaryDate() {
        return delevaryDate;
    }

    public void setDelevaryDate(String delevaryDate) {
        this.delevaryDate = delevaryDate;
    }

    public String getFinishedProduct() {
        return finishedProduct;
    }

    public void setFinishedProduct(String finishedProduct) {
        this.finishedProduct = finishedProduct;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getDesighUrl() {
        return desighUrl;
    }

    public void setDesighUrl(String desighUrl) {
        this.desighUrl = desighUrl;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
