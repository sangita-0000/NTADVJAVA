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

public class LobInsertionTest_Oracle {
	
	private final static String ACTOR_INSERT_QUERY="INSERT INTO ACTOR_INFO01 VALUES(AID_SEQ01.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
				PreparedStatement ps=con.prepareStatement(ACTOR_INSERT_QUERY)){
			//read input values from enduser
			String name=null,photoPath=null,profilePath=null;
			if(sc!=null) {
				System.out.println("Enter Actor name::");
				name=sc.next();
				System.out.println("Enter actor photo Path::");
				photoPath=sc.next().trim().replace("?","");
				System.out.println("Enter actor ProfilePath::");
				profilePath=sc.next().trim().replace("?","");
			}
			//create Stream pointing to the files
			try(InputStream is=new FileInputStream(photoPath);
					Reader reader=new FileReader(profilePath);
					){
				if(ps!=null) {
					//set values to Query params
					ps.setString(1,name);
					ps.setBinaryStream(2, is);
					ps.setCharacterStream(3, reader);
					
					//execute the Query params
					int count=ps.executeUpdate();
					//process the result
					if(count==0)
						System.out.println("Record not inserted");
					else
						System.out.println("Record Inserted");
				}//if
			}//try2
			
		}//try01
		catch (SQLException se) {
			se.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
