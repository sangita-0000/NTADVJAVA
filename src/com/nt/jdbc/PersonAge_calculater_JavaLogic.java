package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class PersonAge_calculater_JavaLogic {

	private static final String GET_DOB_BY_CNO="SELECT DOB FROM CUSTOMER_INFO WHERE CNO=?";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414bd1","root","root");
				PreparedStatement ps=con.prepareStatement(GET_DOB_BY_CNO);){
			
			//read inputs
			int no=0;
			if(sc!=null) {
				System.out.println("Enter Customer ID::");
				no=sc.nextInt();
			}
			//set values Query params
			if(ps!=null) {
				ps.setInt(1, no);
			}
			//execute the SQL Query and get the resultSet
			try(ResultSet rs=ps.executeQuery()){
				if(rs!=null) {
					if(rs.next()) {
						java.util.Date udob=rs.getDate(1);
						java.util.Date sysDate=new Date();
						long ageInMs=sysDate.getTime()-udob.getTime();
						System.out.println("Customer Age::"+(ageInMs/(1000.0f*60.0f*60.0f*24.0f*365.25f)));
					}
					else {
						System.out.println("customer not fount");
					}
				}
			}
		}
		catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
