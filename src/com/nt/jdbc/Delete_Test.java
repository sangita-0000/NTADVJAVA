package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete_Test {

	public static void main(String[] args) {
		Scanner sc=null;
		Statement st=null;
		Connection con=null;
		try {
			//read input 
			sc=new Scanner(System.in);
			String sadd=null;
			if(sc!=null) {
				System.out.println("Enter student address::");
				sadd=sc.next();
			}//if
			//convert input values as required for the SQL Query
			sadd="'"+sadd+"'";
			//Load jdbc driver class 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
			//create JDBC Statement Object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query as DB s/w
			String query="DELETE FROM STUDENT WHERE SADD="+sadd;
			System.out.println(query);
			
			//send and execute the SQL Query in Db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			//process the Result
			if(count==0)
				System.out.println("No recoreds found for delete");
			else
				System.out.println("No of recoreds are affected:"+count);
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {

			e.printStackTrace();
		}finally {
			
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
