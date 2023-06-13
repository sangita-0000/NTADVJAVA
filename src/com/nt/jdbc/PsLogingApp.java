package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLogingApp {
	
	private static final String AUTH_QUERY="SELECT COUNT(*) FROM USER_INFO WHERE UNAME=? AND PWD=?";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
				PreparedStatement ps=con.prepareStatement(AUTH_QUERY);
				){
			//read input 
			String user=null,pwd=null;
			if(sc!=null) {
				System.out.println("Enetr username::");
				user=sc.next();
				System.out.println("Enetr password::");
				pwd=sc.next();
			}
			//set values to query param
			ps.setString(1, user);
			ps.setString(2, pwd);
			
			//send and execute the SQL Query in db s/w
			try(ResultSet rs=ps.executeQuery();){
				if(rs!=null) {
					rs.next();
					int count=rs.getInt(1);
					if(count==0)
						System.out.println("Invalid Credentials");
					else
						System.out.println("Valide Credentials");
				}//if
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
