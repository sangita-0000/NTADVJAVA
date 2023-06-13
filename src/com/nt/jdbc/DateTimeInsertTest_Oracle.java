package com.nt.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsertTest_Oracle {
	private static final String INSERT_CUSTOMER_QUERY="INSERT INTO CUSTOMER_INFO VALUES(CNO_SEQ.NEXTVAL,?,?,?,?,?))";

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				PreparedStatement ps=con.prepareStatement(INSERT_CUSTOMER_QUERY);
				Scanner sc=new Scanner(System.in);){
			//read input values
			String name=null,sdob=null,stop=null,sorderdt=null;
			float billamt=0.0f;
			
			if(sc!=null) {
				System.out.println("Enter Customer name::");
				name=sc.next();
				System.out.println("Enter Customer date of birth(dd-MM-yyyy)::");
				sdob=sc.next();
				System.out.println("Enter customer TOP(hh:mm:ss)::");
				stop=sc.next();
				System.out.println("Enter customer Order date(dd/MM/yyyy hh:mm:ss::");
				sc.next();
				sorderdt=sc.nextLine();
				
			}
			//Convert String DOB to java.sql.Date class object
			//SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=new SimpleDateFormat("dd-MM-yyyy").parse(sdob);
			java.sql.Date sqdob=new Date(udob.getTime());
			
			//convert String TOP(time of purchase) to java.sql.Time obj
			java.sql.Time sqtop=java.sql.Time.valueOf(stop);
			
			//Convert String Order date time to java.sql.TimeStamp obj
			SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			java.util.Date uorderdt=sdf1.parse(sorderdt);
			java.sql.Timestamp sqorderdt=new java.sql.Timestamp(uorderdt.getTime());//util date  to sql timestamp object
			
			//set values SQL Query params
			if(ps!=null) {
				ps.setString(1, name);
				ps.setFloat(2, billamt);
				ps.setDate(3, sqdob);
				ps.setTime(4, sqtop);
				ps.setTimestamp(5, sqorderdt);
				
				//execute the sql query
				int count=ps.executeUpdate();
				if(count==0)
					System.out.println("Record not insert");
				else
					System.out.println("Record insert");
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
