package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class SelectTest_MySQL02 {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ414BD1","root","root");
				Statement st=con.createStatement();
				){
			//read inputs
			float start=0.0f ,end=0.0f;
			if(sc!=null) {
				System.out.println("Enter start price range::");
				start=sc.nextFloat();
				System.out.println("Enetre end price range::");
				end=sc.nextFloat();
			}//if
			//Prepare SQL query
			String query="SELECT * FROM PRODUCT WHERE PRICE>="+start+"AND PRICE<="+end;
			System.out.println(query);
			//process the resultset
			ResultSet rs=st.executeQuery(query);
			
			if(rs!=null) {
				boolean rsEmpty=true;
				while(rs.next()) {
					rsEmpty=false;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
				}//while
				if(rsEmpty)
					System.out.println("NO records found");
				else
					System.out.println("Records found and display");
			}//if
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
				e.printStackTrace();
		}

	}

}
