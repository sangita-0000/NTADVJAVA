package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class PersonAgeCalculater_MySQL {
	
	private static final String AGE_CALCULATER_QUERY="SELECT (DATEDIFF(NOW(),DOB))/365.25 FROM CUSTOMER_INFO WHERE CNO=?";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con= DriverManager.getConnection("jdbc:mysql:///ntaj414bd1","root","root");
				PreparedStatement ps=con.prepareStatement(AGE_CALCULATER_QUERY);){
		
			//read inputs
			int no=0;
			if(sc!=null) {
				System.out.println("Enter Customer No::");
				no=sc.nextInt();
			}
			//set values Query params
			if(ps!=null) {
				ps.setInt(1, no);
			}
			//execute the SQL Query and get the ResultSet obj
			try(ResultSet rs=ps.executeQuery()){
				if(rs!=null) {
					if(rs.next()) {
					System.out.println("Customer age::"+rs.getFloat(1));
					}
					else {
						System.out.println("Customer Not found");
					}
				}//if
			}//try2
		}//try1
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
