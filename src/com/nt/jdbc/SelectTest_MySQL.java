package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest_MySQL {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ414BD1","root","root");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM PRODUCT");
				){
			//process the resultSet
			if(rs!=null) {
				boolean rsEmpty=true;
				while(rs.next()) {
					rsEmpty=false;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
					
				}//while
			}//if
		}//try
			catch (SQLException se) {
				se.printStackTrace();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		
	}

}
