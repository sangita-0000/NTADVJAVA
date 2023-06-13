package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest02 {
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//red inputs
			sc=new Scanner(System.in);
			String desg1=null , desg2=null, desg3=null,desg4=null;
			if(sc!=null) {
				System.out.println("Enetr desg1::");
				desg1=sc.next().toUpperCase();
				System.out.println("Enetr desg2::");
				desg2=sc.next().toUpperCase();
				System.out.println("Enetr desg3::");
				desg3=sc.next().toUpperCase();
				
			}//if
			//convert the input values as required for the SQL Query ('CLERK','MANAGER','SALESMAN');
			String code="('"+desg1+" ','"+desg2+"','"+desg3+"')";
			System.out.println(code);
			//Load jdbc driver class(optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection with DB s/w
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
			//create statement object 
			if(con!=null) 
				st=con.createStatement();
			//prepare SQL Query
			//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN('CLERK','MANAGER',"SALESMAN');
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN"+code+"ORDER BY JOB";
			System.out.println(query);
			
			//send execute SQL Query in Db s/w
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSet obj
			if(rs!=null) {
				while(rs.next()!=false) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
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
			try {
				if(sc!=null)
					sc.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
