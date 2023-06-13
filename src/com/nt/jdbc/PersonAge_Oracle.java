package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAge_Oracle {

	private static final String AGE_CALCULATER_QUERY="SELECT (SYSDATE-DOB)/365.25 FROM CUSTOMER_INFO WHERE CNO=?";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				PreparedStatement ps=con.prepareStatement(AGE_CALCULATER_QUERY);
				){
			//read inputs
			int no=0;
			if(sc!=null) {
				System.out.println("Enetr Customer ID::");
				no=sc.nextInt();
			}
			//set values to Query params
			if(ps!=null) {
				ps.setInt(1, no);
			}
			//execute the SQL Query and get the resultSet obj
			try(ResultSet rs=ps.executeQuery()){
				if(rs!=null) {
					if(rs.next()) {
						System.out.println("Customer age::"+rs.getFloat(1));
					}
					else {
						System.out.println("Customer not found");
					}
				}//if
			}//try2
			
		}//try1
		catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
