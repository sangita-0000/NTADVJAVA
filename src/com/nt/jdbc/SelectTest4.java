package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {

	public static void main(String[] args) {
		Connection con=null;
		Scanner sc=null;
		ResultSet rs=null;
		Statement st=null;
		try {
			//Load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
			if(con!=null)
				st=con.createStatement();
			String query="SELECT COUNT(*) FROM EMP";
			if(st!=null)
				rs=st.executeQuery(query);
			if(rs!=null) {
				rs.next();
				System.out.println("Records count::"+rs.getInt("COUNT(*)"));
			}//if
		}catch (SQLException se) {

			se.printStackTrace();
		}catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
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
			try {
				if(sc!=null)
					sc.close();
			}catch (Exception se) {
				se.printStackTrace();
			}
		}
	}

}
