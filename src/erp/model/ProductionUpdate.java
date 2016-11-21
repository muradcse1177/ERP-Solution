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
public class ProductionUpdate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String updateNo;
    private String updateDate;
    private String updateBy;
    private String issueNo;
    private String issueDate;
    private String jobNo;
    private String jobDate;
    private String itemRef;
    private String ordQty;
    private String upQty;
    private String balQty;
    private String exQty;

    public ProductionUpdate() {
    }

    public ProductionUpdate(String updateNo) {
        this.updateNo = updateNo;
    }

    public String getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(String updateNo) {
        this.updateNo = updateNo;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public String getItemRef() {
        return itemRef;
    }

    public void setItemRef(String itemRef) {
        this.itemRef = itemRef;
    }

    public String getOrdQty() {
        return ordQty;
    }

    public void setOrdQty(String ordQty) {
        this.ordQty = ordQty;
    }

    public String getUpQty() {
        return upQty;
    }

    public void setUpQty(String upQty) {
        this.upQty = upQty;
    }

    public String getBalQty() {
        return balQty;
    }

    public void setBalQty(String balQty) {
        this.balQty = balQty;
    }

    public String getExQty() {
        return exQty;
    }

    public void setExQty(String exQty) {
        this.exQty = exQty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (updateNo != null ? updateNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductionUpdate)) {
            return false;
        }
        ProductionUpdate other = (ProductionUpdate) object;
        if ((this.updateNo == null && other.updateNo != null) || (this.updateNo != null && !this.updateNo.equals(other.updateNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erp.model.ProductionUpdate[ updateNo=" + updateNo + " ]";
    }

}
