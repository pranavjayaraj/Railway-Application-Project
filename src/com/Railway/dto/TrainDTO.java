/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Railway.dto;

import com.Railway.dao.TrainDAO;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class TrainDTO {
    private int productId;
    private String productCode;
    private String type;
    private String date;
    private String productName;
    private int quantity;
    private String sellDate;
    private double costPrice;
    private int sellingPrice;
    private int billNo;
    private int userId;
    private Double totalCost;
    private String source;
    private String dest;
    private String cusname;
    private int totalRevenue;

    public String getSource(){
        return source;
    }
    public void setSource(String source)
    {
        this.source = source;
    }
     public String getDest(){
        return dest;
    }
    public void setDest(String dest)
    {
        this.dest = dest;
    }
    public int getProductId() {
        return productId;
    }
  
     public String getCusname(){
        return cusname;
    }
    public void setCusname(String cusname)
    {
        this.cusname = cusname;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

      public String getType()
    {
        return type;
    }
      public void setType(String type)
      {
         this.type=type;
      }
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  
    public String getSellDate() {
        return sellDate;
    }
  
    public void setSellDate(String date) {
        this.sellDate=sellDate;
    }   
    public int getBillNo() {
        return billNo;
    }
 public void setBillNo(int billNo) {
        this.billNo=billNo;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }
    
    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    
    
    public ResultSet getProductsName() {
        ResultSet rs=new TrainDAO().getProductInfo();
        return rs;
    } 

    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    
}
