package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PreCompiledSQL_Test01 {
	
	private final static String SOME_EMP_DETAILS="SELECT EMPNO,ENAME,SAL,JOB,DEPTNO FROM EMP WHERE DEPTNO=? AND JOB=?";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				//establish the connection
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				//Create preparedStatement Object 
				PreparedStatement ps=con.prepareStatement(SOME_EMP_DETAILS);
				//execute Precompiled SQL Query
				){
			//
			int deptno;
			String job=null;
			if(sc!=null) {
				System.out.println("Enetr empno::");
				deptno=sc.nextInt();
				System.out.println("Enter Job::");
				job=sc.next();
				
				/*
				 * if(ps!=null) { ps.setInt(1, deptno); ps.setString(2, job); }
				 */
				
			}
			job="'"+job+"'";
			
			try(ResultSet rs=ps.executeQuery()){
			//process the result set object
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3)+" "+rs.getString(4)+" "+rs.getInt(5));
				}
			}
			}
				
		}catch (Exception e) {
		e.printStackTrace();
		}
	}
}
