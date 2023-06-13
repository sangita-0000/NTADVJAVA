package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest04 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st= null;
		ResultSet rs=null;
		
		try {
			//Load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
			//create statement object
			if(con!=null)
				st=con.createStatement();
		//prepare SQL statement object
			String query="SELECT COUNT(*) FROM EMP";
			//send and execute SQL Query
			if(st!=null)
				rs=st.executeQuery(query);
			if(rs!=null) {
				rs.next();
					System.out.println("Record count::"+rs.getInt("COUNT(*)"));
			}//if
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

}
