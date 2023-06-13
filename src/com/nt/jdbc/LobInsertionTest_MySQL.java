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

public class LobInsertionTest_MySQL {

	private static final String INSERT_VALUES_QUERY="INSERT INTO ACTOR_INFO01(ANAME,PHOTO,PROFILE)VALUES(?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414bd1","root","root");
				PreparedStatement ps=con.prepareStatement(INSERT_VALUES_QUERY);
				){
			//read input values
			String name=null,photopath=null,profilePath=null;
			if(sc!=null) {
				System.out.println("Enter Actor name::");
				name=sc.next();
				System.out.println("Enter Actor PhotPath::");
				photopath=sc.next().trim().replace("?","");
				System.out.println("Enter Actor ProfilePath::");
				profilePath=sc.next().trim().replace("?","");
			}
			//create Stream pointing to the files
			try(InputStream is=new FileInputStream(photopath);
					Reader reader=new FileReader(profilePath);){
				if(ps!=null) {
					ps.setString(1,name);
					ps.setBinaryStream(2, is);
					ps.setCharacterStream(3, reader);
					//execute the Query
					int count=ps.executeUpdate();
					if(count==0)
						System.out.println("Record Not Inserted");
					else
						System.out.println("Record inserteed");
				}
				
			}
			
		}//try1
		catch (SQLException se) {
			se.printStackTrace();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
