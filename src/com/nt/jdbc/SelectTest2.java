package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2 {

	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read input
			sc=new Scanner(System.in);
			String desg1=null, desg2=null,desg3=null;
			if(sc!=null) {
				System.out.println("Enter desg1::");
				desg1=sc.next().toUpperCase();//gives CLERK
				System.out.println("Enter desg2::");
				desg2=sc.next().toUpperCase();//gives MANEGER
				System.out.println("Enter desg3::");
				desg3=sc.next().toUpperCase();//gives SALESMAN
				
			}//if
			//convert the input values as required for the SQL Query('CLERK','MANEGER','SALESMAN')
		
			String cond="('"+desg1+"','"+desg2+"','"+desg3+"')";
			System.out.println(cond);
			//Load jdbc driver class 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection with Db s/w
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
			//create statement object 
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN('CLERK','MANEGER','SALESMAN') ORDER BY JOB;
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN"+cond+" ORDER BY JOB";
			System.out.println(query);
			
			//send execute the ResultSet obj
			if(st!=null)
				rs=st.executeQuery(query);
			//process the resultset
			if(rs!=null) {
				while(rs.next()!=false) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
			}//if
			
		}//try
		catch (SQLException se) {
			se.printStackTrace();
			
		}
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
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
			}catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}//finally

	}//main

}//class
