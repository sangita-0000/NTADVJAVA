package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Loging_App {

	public static void main(String[] args) {

		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				Statement st=con.createStatement();){
			//read input
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("Enter UserName::");
				user=sc.next();
				System.out.println("Enter PassWord::");
				pass=sc.next();
				
			}
			//convert input values as required to for SQL Query
			user="'"+user+"'";
			pass="'"+pass+"'";
			//prepare SQL Query
			//SELECT COUNT(*) FROM USER_INFO WHERE UNAME='RAJA' AND PASS='RANI';
			String query="SELECT COUNT(*) FROM USER_INFO WHERE USER="+user+"AND PWD="+pass;
			System.out.println(query);
			try(ResultSet rs=st.executeQuery(query)){
				if(rs!=null) {
					rs.next();
					int count=rs.getInt(1);
					if(count==0)
						System.out.println("invalide Credentials");
					else
						System.out.println("valide credentials::"+count);
					
				}//if
			}//try1
		}//try
		catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
