package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsert_Test {
	
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				PreparedStatement ps=con.prepareStatement(STUDENT_INSERT_QUERY);){
			
			int count=0;
			if(sc!=null) {
				System.out.println("Enter count Number");
				count=sc.nextInt();
			}
			if(sc!=null && ps!=null) {
				for(int i=1;i<=count;i++) {
					System.out.println("Enter student number::");
					int sno=sc.nextInt();
					System.out.println("Enetr Student Name::");
					String sname=sc.next();
					System.out.println("Enetr Student Address::");
					String sadd=sc.next();
					System.out.println("Enter Student Average::");
					float avg=sc.nextFloat();
					//set each student details to pre complied query
					ps.setInt(1, sno);
					ps.setString(2, sname);
					ps.setString(3, sadd);
					ps.setFloat(4, avg);
					
				//if
				//execute the Query
				int result=ps.executeUpdate();
				//process the result 
				if(result==0)
					System.out.println(i+"Student record not inserted");
				else
					System.out.println(i+"Student records inserted");
				}
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}


	}
}
