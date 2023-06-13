package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest06 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//Load driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection DB s/w
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
			//crate statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL statement object
			String query="SELECT MIN(SAL) FROM EMP";
			//crate resultset object
			if(st!=null)
				rs=st.executeQuery(query);
			//execute resultset object
			if(rs!=null) {
				rs.next();
				System.out.println("Min sal from employee table::"+rs.getInt("MIN(SAL)"));
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
