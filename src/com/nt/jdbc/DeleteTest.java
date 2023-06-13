package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			String sadd=null;
			if(sc!=null) {
				System.out.println("Enter Student Address");
				sadd=sc.next();
			}//if
			sadd="'"+sadd+"'";
			//Load jdbc driver Object
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the jdbc connection to DB s/w
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
			//create jdbc statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query as required for the DB s/w
			String query="DELETE FROM STUDENT WHERE SADD="+sadd;
			System.out.println(query);
			//send and execute the SQL Query in DB s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			//process the Result 
			if(count==0)
				System.out.println("No record(S) frond delection");
			else
				System.out.println("No.of records that are effected::"+count);
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
			try {
				if(sc!=null)
					sc.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
