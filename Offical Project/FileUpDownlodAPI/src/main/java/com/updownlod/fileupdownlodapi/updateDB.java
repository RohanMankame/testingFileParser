package com.updownlod.fileupdownlodapi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class updateDB {

    //CODE FOR ENTWE FILE FROM DATABASE
    public void updateDBnewEntry(String claimNum, String policyNum, String closureDate,String insuredName, String repudiationReason ,String fileName){

        try{
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fileupdown","root","rohan");

            stmt = conn.createStatement();
            String sql = "INSERT INTO searchtable"
                    + " VALUES( "  + "'"+claimNum+ "'" + ","+"'"+policyNum+ "'" + " , "+ "'" + closureDate + "'" + " , " +  "'" +insuredName+ "'"  +" , "+  "'" +repudiationReason+ "'"  +" , "+ "'" +fileName+ "'"  +" );";
            stmt.executeUpdate(sql);

        }  catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    //CODE FOR DELETING FILE FROM DATABASE
    public void updateDBdeleteEntry(String ClaimNum){

        try{
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fileupdown","root","rohan");

            stmt = conn.createStatement();
            String sql = "DELETE FROM searchtable"
                    + " WHERE ClaimNumber=" + "'"+  ClaimNum +"';";
            stmt.executeUpdate(sql);

        }  catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String searchDBClaimFileName(String claimCode) {

        String fileCode = null ;
        UploadResponse responseListData = new UploadResponse();

        try {
            Connection conn = null;
            Statement stmt = null;
            ResultSet results = null;

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fileupdown", "root", "rohan");

            stmt = conn.createStatement();
            String sql = "SELECT * FROM fileupdown.searchtable WHERE ClaimNumber = "+ claimCode;

            results = stmt.executeQuery(sql);
            boolean Records = results.next();
            if(!Records){
                System.out.println("No rows in table");
                stmt.close();
                return fileCode;//ID
            }
            //Display rows in console
            try {
                do{
                    fileCode = results.getString(6);
                }while(results.next());
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileCode;
    }





    //CODE FOR SEARCHING FILES FROM DATABASE
    public List<UploadResponse> searchDBEntryDates(String startDate, String endDate) {

        List<UploadResponse> responseListData = new ArrayList();

        try{
            Connection conn = null;
            Statement stmt = null;
            ResultSet results = null;

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fileupdown","root","rohan");

            stmt = conn.createStatement();
            String sql = "SELECT * FROM fileupdown.searchtable where str_to_date(ClosureDate,'%Y-%m-%d') between " +
                    "str_to_date('"+startDate+"','%Y-%m-%d') and str_to_date('"+endDate+"','%Y-%m-%d');";

            results = stmt.executeQuery(sql);
            boolean Records = results.next(); // moves cursor to next row and returns boolean val
            if(!Records){
                System.out.println("No rows in table");
                stmt.close();
                return responseListData;
            }
            //Display rows in console
            try {
            do{
                String fileCode = results.getString(6);
                UploadResponse response = ResponseDataUtil.ResponseFileData(fileCode);
                responseListData.add(response);
            }while(results.next());
            stmt.close();
            }
            catch (SQLException | IOException | InvalidFormatException e) {
                throw new RuntimeException(e);
            }
        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return responseListData;
    }


    public String searchDBforName(String fileCode) {

        UploadResponse responseListData = new UploadResponse();

        try {
            Connection conn = null;
            Statement stmt = null;
            ResultSet results = null;

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fileupdown", "root", "rohan");

            stmt = conn.createStatement();
            String sql = "SELECT * FROM fileupdown.searchtable WHERE FileName = "+ fileCode;

            results = stmt.executeQuery(sql);
            boolean Records = results.next();
            if(!Records){
                System.out.println("No rows in table");
                stmt.close();
                return null;
            }
            //Display rows in console
            try {
                do{
                    fileCode = results.getString(6);
                }while(results.next());
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileCode;
    }




}




