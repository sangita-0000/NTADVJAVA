package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSInsertTest {
	private final static String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?,?)";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
				PreparedStatement ps=con.prepareStatement(STUDENT_INSERT_QUERY);){
			int count=0;
			if(sc!=null) {
				System.out.println("Enetr student count::");
				count=sc.nextInt();
			}//if
			if(sc!=null && ps!=null) {
				for(int i=1;i<=count;++i) {
					//read each student details
					System.out.println("Enter"+i+"Student details::");
					System.out.println("Enter student number::");
					int no=sc.nextInt();
					
					System.out.println("Enetr student name::");
					String name=sc.next();
					
					System.out.println("Enetr student address::");
					String sadd=sc.next();
					
					System.out.println("Enter studnet Avg::");
					float avg=sc.nextFloat();
					
					System.out.println("Enetr studnet Desg::");
					String desg=sc.next();
					
					//set each student details to pre-complied SQL Query as query param
					ps.setInt(1,no);
					ps.setString(2, name);
					ps.setString(3, sadd);
					ps.setFloat(4, avg);
					ps.setString(5, desg);
					
					//execute the Query
					int result=ps.executeUpdate();
					//process the result 
					if(result==0)
						System.out.println(i+"Student record is not inserted");
					else
						System.out.println(i+"Student record is inserted");
					
				}//for
			}//if
			
		}//try
		catch (SQLException se) {
			se.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
