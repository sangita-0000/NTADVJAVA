package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest01 {

	public static void main(String[] args) {
		Scanner sc=null;
		Statement st=null;
		ResultSet rs=null;
		Connection con=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			float startSalary=0.0f;
			float endSalary=0.0f;
			if(sc!=null) {
				System.out.println("Enter start range emp slary ");
				startSalary=sc.nextFloat();
				System.out.println("Enter last salary of emp ");
				endSalary=sc.nextFloat();
			}
			//load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
			
			//create Jdbc obj
			if(con!=null)
				st=con.createStatement();
				//prepare SQL Query	
			String query="SELECT EMPNO , ENAME, JOB , SALARY FROM EMP WHERE SAL>="+startSalary+"AND<="+endSalary;
			System.out.println(query);
			if(st!=null)
				rs=st.executeQuery(query);
			//process the result set object
			if(rs!=null) {
				while(rs.next()==true) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
			}//if
			
		}catch (SQLException se) {
			se.printStackTrace();
		}
		catch (ClassNotFoundException cnf) {
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
