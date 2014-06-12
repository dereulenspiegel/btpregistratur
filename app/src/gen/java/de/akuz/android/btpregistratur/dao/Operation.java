package de.akuz.android.btpregistratur.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table OPERATION.
 */
public class Operation {

    private Long id;
    private java.util.Date startDate;
    private java.util.Date endDate;
    private String operationNumber;
    private String btpAddress;

    public Operation() {
    }

    public Operation(Long id) {
        this.id = id;
    }

    public Operation(Long id, java.util.Date startDate, java.util.Date endDate, String operationNumber, String btpAddress) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.operationNumber = operationNumber;
        this.btpAddress = btpAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getBtpAddress() {
        return btpAddress;
    }

    public void setBtpAddress(String btpAddress) {
        this.btpAddress = btpAddress;
    }

}
