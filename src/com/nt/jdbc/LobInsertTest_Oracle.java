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

public class LobInsertTest_Oracle {

	private static final String CRICKETER_INSERT_QUERY="INSERT INTO CRICKET_INFO VALUES(CID_SEQ.NEXTVAL,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				PreparedStatement ps=con.prepareStatement(CRICKETER_INSERT_QUERY);){
			//read input values for end user
			String name=null,photoPath=null,profilePath=null;
			if(sc!=null) {
				System.out.println("Enter Cricketer Name::");
				name=sc.next();
				System.out.println("Enter Cricketer PhotoPath::");
				photoPath=sc.next().trim().replace("?","");
				System.out.println("Enter Cricketer ProfilePath::");
				profilePath=sc.next().trim().replace("?","");
			}//if
			//create Stream pointing to the file
			try(InputStream is=new FileInputStream(photoPath);
					Reader reader=new FileReader(profilePath)){
				if(ps!=null) {
					//set values to SQL Query param
					ps.setString(1,name);
					ps.setBinaryStream(2, is);
					ps.setCharacterStream(3, reader);
					//execute the Query
					int count=ps.executeUpdate();
					//process the result
					if(count==0) {
						System.out.println("Record not inserted");
					}
					else {
						System.out.println("Record insreted");
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
