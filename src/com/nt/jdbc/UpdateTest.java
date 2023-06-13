package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		
			Scanner sc=null;
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			
			try {
				//read inputs
				sc=new Scanner(System.in);
				int no=0;
				String sName=null;
				String sAdd=null;
				float avg=0.0f;
				String desg=null;
				if(sc!=null) {
					System.out.println("Enetr existing student number::");
					no=sc.nextInt();
					System.out.println("Enetr existing student Name::");
					sName=sc.next();
					System.out.println("Enetr existing student Addressr::");
					sAdd=sc.next();
					System.out.println("Enetr existing student Avrage::");
					avg=sc.nextFloat();
					System.out.println("Enetr existing student Desgination::");
					desg=sc.next();
				}//if
				sAdd="'"+sAdd+"'";
				//Load jdbc driver Object
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//establish the jdbc connection to DB s/w
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
				//create jdbc statement object
				if(con!=null)
					st=con.createStatement();
				//prepare SQL Query as required for the DB s/w
				String query="UPDATE STUDENT SET SADD="+sAdd+",AVG="+avg+"WHERE SNO="+no;
				System.out.println(query);
				//send and execute the SQL Query in DB s/w
				if(st!=null)
					rs=st.executeQuery(query);
				
				int count=0;
				if(count==0) {
					System.out.println("No records found(s) found for updation");
				}//if
				else {
					System.out.println("No records that are affected::"+count);
				}
					
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
