/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Railway.dao;

import com.Railway.database.ConnectionFactory;
import com.Railway.dto.TrainDTO;
import com.Railway.ui.SeatsAvailable;
import java.sql.Connection;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import com.Railway.ui.ReserveSeats;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class TrainDAO {
    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs1=null;
    Statement stmt1=null;
    ResultSet rs = null;

    public TrainDAO() {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.createStatement();
            stmt1=con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

  
    
    public ResultSet getProductInfo(){
        try{
            String query="SELECT * FROM currentstocks";
            rs=stmt.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet getTrainName(){
        try{
            String query="SELECT * FROM TRAIN_DETAILS";
            rs=stmt.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    
  
    
     public void deleteTrainDAO(String value ,String type){
        try{
            String query="delete from TRAIN_DETAILS where train_no=? AND type=?";
            pstmt=con.prepareStatement(query);
            pstmt.setString(1,value);
            pstmt.setString(2,type);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted..");
        }catch(SQLException  e){
            e.printStackTrace();
        }
      //  deleteStock();
    }
     public void deletePriceDAO(String source ,String destination ,String type){
        try{
            String query="delete from TRAIN_PRICE where source=? AND destination=? AND type=? ";
            pstmt=con.prepareStatement(query);
            pstmt.setString(1,source);
            pstmt.setString(2,destination);
            pstmt.setString(3,type);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted..");
        }catch(SQLException  e){
            e.printStackTrace();
        }
      //  deleteStock();
    }
  
    
    String productCode;
    public String getTrainCode(String productsName){
        try{
            String query="SELECT train_no FROM TRAIN_DETAILS WHERE train_name='"+productsName+"'";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                productCode=rs.getString("productcode");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return productCode;
    }
    
    
      
    boolean flag=false;
   
    public void addTrainDAO(TrainDTO productdto) {
         try{
                String query = "SELECT * FROM TRAIN_DETAILS WHERE train_no='"+productdto.getProductName()+"'";
                rs=stmt.executeQuery(query);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,"Same Product has already been added!");
                }else{
                    addFunction(productdto);
                }
        }catch(Exception e){
                e.printStackTrace();
        }
            
    }//end of method addUserDTO
    
     public void addPlaceDAO(TrainDTO productdto) {
         try{
                String query = "SELECT * FROM TRAIN_PRICE WHERE source='"+productdto.getSource()+"' AND destination='"+productdto.getDest()+"' AND type='"+productdto.getType()+"'";
                rs=stmt.executeQuery(query);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,"Same Product has already been added!");
                }else{
                    addFunction1(productdto);
                }
        }catch(Exception e){
                e.printStackTrace();
        }
            
    }//end of method addUserDTO
    
    
    public void addFunction(TrainDTO productdto){
        try {
  
            String query1="SELECT * FROM TRAIN_DETAILS";
            rs=stmt.executeQuery(query1);
                    String q = "INSERT INTO TRAIN_DETAILS VALUES(?,?,?,?,?,?)";
                    pstmt = (PreparedStatement) con.prepareStatement(q);
                    pstmt.setString(1, productdto.getProductCode());
                    pstmt.setString(2, productdto.getProductName());
                    pstmt.setString(3,productdto.getType());
                    pstmt.setInt(4,productdto.getQuantity());
                    pstmt.setString(5, productdto.getSource());
                    pstmt.setString(6, productdto.getDest());
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Inserted Successfully! ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

public void addFunction1(TrainDTO productdto){
        try {
  
            String query1="SELECT * FROM TRAIN_PRICE";
            rs=stmt.executeQuery(query1);
                    String q = "INSERT INTO TRAIN_PRICE VALUES(?,?,?,?)";
                    pstmt = (PreparedStatement) con.prepareStatement(q);
                    pstmt.setString(1, productdto.getSource());
                    pstmt.setString(2, productdto.getDest());
                    pstmt.setString(3,productdto.getType());
                    pstmt.setInt(4, (int) productdto.getSellingPrice());
             
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Inserted Successfully! ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

//    addPurchaseDAO
    
    
    public void editTrainDAO(TrainDTO productdto) {
        try {
                String query = "UPDATE TRAIN_DETAILS SET train_name=? ,seats=? ,source=? ,destination=? WHERE train_no=? AND type=?";
                pstmt = (PreparedStatement) con.prepareStatement(query);
                pstmt.setString(1, productdto.getProductName());
                pstmt.setInt(2, productdto.getQuantity());
                pstmt.setString(3, productdto.getSource());
                pstmt.setString(4, productdto.getDest());
                pstmt.setString(5, productdto.getProductCode());
                pstmt.setString(6, productdto.getType());


                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }  
       
    }//end of method editUserDTO
    public void editPriceDAO(TrainDTO productdto) {
        try {
                String query = "UPDATE TRAIN_PRICE SET price=? WHERE source=? AND destination=? AND type=?";
                pstmt = (PreparedStatement) con.prepareStatement(query);
                pstmt.setInt(1, (int) productdto.getSellingPrice());
                pstmt.setString(2, productdto.getSource());
                pstmt.setString(3, productdto.getDest());
                pstmt.setString(4, productdto.getType());
            
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }  
       
    }//end of m
   
    
  
    
  
    
    
    
    
    
        private  static Boolean a=true;
    public boolean sellProductDAO(TrainDTO productDTO,String username){
        int quantity=0;
        String productCode1=productDTO.getProductCode();
        int billNo=productDTO.getBillNo();
        int qty=productDTO.getQuantity();
        String cusname=productDTO.getCusname();
        String date=productDTO.getSellDate();
        String type=productDTO.getType();
        String source=productDTO.getSource();
        String dest=productDTO.getDest();
        int total=productDTO.getTotalRevenue();
        try{
            String query="SELECT * FROM TRAIN_DETAILS WHERE train_no='"+productDTO.getProductCode()+"'AND type='"+type+"'";
            rs=stmt.executeQuery(query);
      
            while(rs.next()){
                productCode=rs.getString("train_no");
                quantity=rs.getInt("seats");
                
                //type=rs.getType("type");
            }
            if(productDTO.getQuantity()>quantity){
                a=false;
                JOptionPane.showMessageDialog(null,"SEATS NOT VACANT");
           
            }else{
                try{
                    a=true;
                    int quantity1=quantity-qty;
                    System.out.println(quantity1);
                    String q="UPDATE TRAIN_DETAILS SET seats= "+quantity1+" WHERE train_no='"+productDTO.getProductCode()+"' AND type='"+type+"'";
                    String trns="INSERT INTO bookings(reservation_no,cus_name,no_of_tickets,type,train_no,date,source,destination,total) VALUES('"+billNo+"','"+cusname+"','"+qty+"','"+type+"','"+productCode1+"','"+date+"','"+source+"','"+dest+"','"+total+"')";
                    stmt.executeUpdate(q);
                    stmt.executeUpdate(trns);
                    JOptionPane.showMessageDialog(null,"SUCCESSFULLY ADDED");
                 }catch(Exception e){
                    e.printStackTrace();
                 }
             }
         }catch(Exception e){
                e.printStackTrace();
         } 
        return a;
    }

    
    
    
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT train_no,train_name,type,seats,source,destination FROM TRAIN_DETAILS ORDER BY train_no";
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }//end of method getQueryResult
    
    
    
    public ResultSet getPriceResult() {
        try {
            rowset= new CachedRowSetImpl();
            String query = "SELECT source,destination,type,price FROM TRAIN_PRICE ORDER BY type";
            rs = stmt.executeQuery(query);
            rowset.populate(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowset;
    }//end of method getQueryResult
   
    
    public ResultSet getQueryResultOfCurrentStocks(String type,String traincode) {
        try {
            String query = "SELECT TRAIN_DETAILS.train_no,TRAIN_DETAILS.train_name,TRAIN_DETAILS.seats FROM TRAIN_DETAILS where type='"+type+"' AND train_no='"+traincode+"'";
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }//end of method getQueryResult
     
    
    
    public ResultSet getBillNo() {
        try {
            rowset= new CachedRowSetImpl();
            String query = "SELECT reservation_no FROM bookings ORDER BY reservation_no DESC LIMIT 1";
            rs = stmt.executeQuery(query);
             rowset.populate(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
           finally {
    try { rs.close(); } catch (Exception e) { /* ignored */ }
    try { con.close(); } catch (Exception e) { /* ignored */ }
}
        return rowset;
    }//end of
    
     
     
     
     
       public int getProductSellingPrice(String source,String dest,String type){
        int sellingPrice = 0;
        try{
            String query="SELECT price FROM TRAIN_PRICE WHERE source='"+source+"' AND destination='"+dest+"' AND type ='"+type+"'";
            rs=stmt.executeQuery(query);
            if(rs.next()){
                sellingPrice=rs.getInt("price");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
  finally {
    try { rs.close(); } catch (Exception e) { /* ignored */ }
    try { con.close(); } catch (Exception e) { /* ignored */ }
}
        return sellingPrice;
    }
     
          
    
       
       public ResultSet getType(String productCode){
        try{
            rowset= new CachedRowSetImpl();
            String query="SELECT type FROM TRAIN_DETAILS WHERE train_no='"+productCode+"'";
            rs=stmt.executeQuery(query);
            rowset.populate(rs);
        }catch(Exception e){
            e.printStackTrace();
        }
  finally {
    try { rs.close(); } catch (Exception e) { /* ignored */ }
    try { con.close(); } catch (Exception e) { /* ignored */ }
}
        return rowset;
    }
  
     
       
       
      public int getBill(){
        int s = 0;
        try{
            String query="SELECT reservation_no FROM bookings ORDER BY reservation_no DESC LIMIT 1";
            rs=stmt.executeQuery(query);
            if(rs.next()){
                 s=rs.getInt("reservation_no");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         finally {
    try { rs.close(); } catch (Exception e) { /* ignored */ }
    try { con.close(); } catch (Exception e) { /* ignored */ }
}
        return s;
    }
    
      public ResultSet getStock() {
        try {
            String query = "SELECT currentstocks.productcode,products.productname,currentstocks.quantity,products.costprice,products.sellingprice FROM currentstocks INNER JOIN products ON currentstocks.productcode=products.productcode WHERE quantity=0";
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }//end of method getQueryResult
     public ResultSet getStockByName(String pcode){
        try {

            String query = "SELECT * FROM bookings WHERE reservation_no = '"+pcode+"'";
;  
                  rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }//end of method getQueryResult
   
    
    public ResultSet getSearchProductsQueryResult(String searchTxt) {
        try {
            String query = "SELECT train_no,train_name , seats , type ,source,destination  FROM TRAIN_DETAILS WHERE train_name LIKE '%"+searchTxt+"%' OR train_no LIKE '%"+searchTxt+"%'";
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
       CachedRowSet rowset= null;
       
    public ResultSet getCode() {
         try {
             rowset= new CachedRowSetImpl();
             String query="select DISTINCT train_no from TRAIN_DETAILS";
             rs = stmt.executeQuery(query);
             rowset.populate(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
   finally {
    try { rs.close(); } catch (Exception e) { /* ignored */ }
    try { con.close(); } catch (Exception e) { /* ignored */ }
}
        return rowset;
    }
    
    
    
    
    
      
     public ResultSet getSource(String type) {
        try {
             rowset= new CachedRowSetImpl();
        String query="select DISTINCT source from TRAIN_DETAILS WHERE train_no='"+type+"'";
            rs = stmt.executeQuery(query);
         rowset.populate(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
          finally {
    try { rs.close(); } catch (Exception e) { /* ignored */ }
    try { con.close(); } catch (Exception e) { /* ignored */ }
}
   
        return rowset;
    }
      
     
      public ResultSet getDest(String type) {
        try {
             rowset= new CachedRowSetImpl();
        String query="select DISTINCT destination from TRAIN_DETAILS WHERE train_no='"+type+"'";
            rs = stmt.executeQuery(query);
         rowset.populate(rs);
       
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
 finally {
    try { rs.close(); } catch (Exception e) { /* ignored */ }
    try { con.close(); } catch (Exception e) { /* ignored */ }
}
        return rowset;
    }
      
      
      
      
    public ResultSet getSearchSalesQueryResult(String searchTxt) {
        try {
            String query = "SELECT * FROM bookings WHERE reservation_no LIKE '%"+searchTxt+"%'";
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
   
   
 /**    
    public ResultSet getProductName(String pcode){
        try {
            String query = "SELECT train_name FROM products WHERE train_no='"+pcode+"'";
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    **/

    
   /** public String getProductsCustomer(int id){
        String cus=null;
        try {
            String query = "SELECT fullname FROM customers INNER JOIN salesreport ON customers.customercode=salesreport.customercode WHERE salesid='"+id+"'";
            rs = stmt.executeQuery(query);
            if(rs.next()){
                cus=rs.getString("fullname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cus;
    }
     
    **/
   
    /**
    public String getSoldDate(int salesid){
        String p=null;
        try {
            String query = "SELECT date FROM salesreport WHERE salesid='"+salesid+"'";
            rs = stmt.executeQuery(query);
            if(rs.next()){
                p=rs.getString("date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
**/
    //start of method DefaultTableModle
    public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData(); //resultset ko metadata
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }//end of method DefaultTableModel

   
}
