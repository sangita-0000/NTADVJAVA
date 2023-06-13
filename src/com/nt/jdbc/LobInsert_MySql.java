package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LobInsert_MySql {
	
	private static final String CRICKET_INSERT_QUERY="INSERT INTO CRICKET_INFO(CNAME,PHOTO,PROFILE) VALUES (?,?,?)";
	

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414db1","root","root");
				PreparedStatement ps=con.prepareStatement(CRICKET_INSERT_QUERY);){
			//read input
			String name=null, photPath=null,profilePath=null;
			
			if(sc!=null) {
				System.out.println("Enter Cricketer name::");
				name=sc.next();
				System.out.println("Enter PhotoPath::");
				photPath=sc.next().trim().replace("?", "");
				System.out.println("Enter ProfilePath::");
				profilePath=sc.next().trim().replace("?","");
				
			}
			//create stream pointing to file
			try(InputStream is=new FileInputStream(photPath);
					Reader reader=new FileReader(profilePath)){
				//set Query param values
				if(ps!=null) {
					ps.setString(1,name);
					ps.setBinaryStream(2, is);
					ps.setCharacterStream(3, reader);
					//execute the Query
					int count=ps.executeUpdate();
					//process the result
					if(count==0)
						System.out.println("Record not inserted");
					else
					System.out.println("Record Inserted");
				}
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
