package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest_JDBC {

	
	
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			//read input
			sc=new Scanner(System.in);
			int sno=0;
			String sname=null,sadd=null;
			if(sc!=null) {
				System.out.println("Enetr sno::");
				sno=sc.nextInt();
				System.out.println("Enter sname::");
				sname=sc.next();
				System.out.println("Enter sadd::");
				sadd=sc.next();
			}
			//convert input as required for the SQL Query
			sname="'"+sname+"'";
			sadd="'"+sadd+"'";
			
			//load JDBC driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
			
			//create Statement Object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			//INSERT INTO STUDENT VALUES(100,'sangita','hyd');
			String query="INSERT INTO STUDENT VALUES("+sno+","+sname+","+sadd+")";
			System.out.println(query);
			//send and execute the SQL Query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result 
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(st!=null) {
					st.close();
				}
			}
			catch (SQLException se) {
					se.printStackTrace();
					
			}
			try {
				if(con!=null) {
						con.close();
					}
			}
			catch (SQLException se) {
					se.printStackTrace();
						
			}
			try {
				if(sc!=null) {
					sc.close();
				}
			}
			catch (Exception se) {
					se.printStackTrace();
					
			}
		}
	}

}
