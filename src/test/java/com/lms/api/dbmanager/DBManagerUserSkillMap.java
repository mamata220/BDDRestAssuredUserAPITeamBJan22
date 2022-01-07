package com.lms.api.dbmanager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBManagerUserSkillMap {

	
	String url = "jdbc:postgresql://localhost:5432/LMS_DB1";
    String user = "postgres";
    String password = "*****";
    Statement stmt;
    Connection con;
    ResultSet rs;
    private String query;
  
    
    public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}	
	
    public void createConnection() throws SQLException {
    	con=DriverManager.getConnection(url,user,password);
    	System.out.println("Connection Created ");    	
    }
	
    public ResultSet executeQuery() throws SQLException {
    	stmt=con.createStatement();
    	rs = stmt.executeQuery(query);
       	System.out.println("Query Executed ");
    	return rs;
    }
    public void closeConnection() throws SQLException {
        con.close();
        System.out.println("Connection Closed ");  
    }
    
    
    public void dbvalidation(String responseBody, String id) throws SQLException{
		
		String query = "select * from tbl_lms_userskill_map where user_skill_id ='" + id  + "'";
		setQuery(query);
		createConnection();
		ResultSet rs = executeQuery();
		
		boolean dbValidated = true;
		
		while (rs.next()) {
			String user_skill_id = rs.getString(1);
			 String user_id = rs.getString(2);
			 String skill_id = rs.getString(3);
			 String months_of_exp = rs.getString(4);
			String creation_time = rs.getString(5);		        
		     String last_mod_time=rs.getString(6);
			   
		        ArrayList<String> resultlist= new ArrayList<String>();
		        resultlist.add(user_skill_id);
		        resultlist.add(user_id);
		        resultlist.add(skill_id);
		        resultlist.add(months_of_exp);
		        resultlist.add(creation_time);
		        resultlist.add(last_mod_time);
		        System.out.println("Record from Database : " + resultlist);
		
			
			if (responseBody.contains(user_skill_id)) {
			  dbValidated = true;	
			}
			else {
				dbValidated = false;
				//break;
			}		
		}	
		
		if (dbValidated) {
			System.out.println(" DB validation success ");
		}
		else {
			System.out.println(" DB validation failed !! ");
		}
		
		
		// last step
		closeConnection();
		
	}
  

   
}

