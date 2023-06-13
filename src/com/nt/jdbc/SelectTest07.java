package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest07 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//Load jdbc driver classs
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the jdbc connection  to DB s/w
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query as required for db s/w
			String query="SELECT AVG(SAL) FROM EMP";
			if(st!=null)
				rs=st.executeQuery(query);
			//execute ResultSet Object
			if(rs!=null) {
				rs.next();
				System.out.println("Avg sal of emp table"+rs.getFloat("AVG(SAL)"));
			}//if
				
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
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
