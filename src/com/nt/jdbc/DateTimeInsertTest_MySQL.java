package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsertTest_MySQL {

	private final static String INSERT_CUSTOMER_QUERY="INSERT INTO CUSTOMER_INFO(CNAME,BILLAMT,DOB,TOP,ORDER_DATE_TIME) VALUES(?,?,?,?,?)";
	
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414bd1","root","root");
				PreparedStatement ps=con.prepareStatement(INSERT_CUSTOMER_QUERY);
				Scanner sc=new Scanner(System.in);){
			//read inputs
			String name=null,sdob=null,stop=null,sorderdt=null;
			float billamt=0.0f;
			if(sc!=null) {
				System.out.println("Enter customer name::");
				name=sc.next();
				System.out.println("Enetr customer bill amonunt");
				billamt=sc.nextFloat();
				System.out.println("Enter customer DOB(dd-MM-yyyy)::");
				sdob=sc.next();
				System.out.println("Enetr customer TOP(hh:mm:ss)");
				stop=sc.next();
				System.out.println("Enter Order Time(dd/MM/yyyy hh:mm:ss)::");
				sc.next();
				sorderdt=sc.nextLine();
			}
			//convert String DOB to java.sql.Date class obj
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=sdf.parse(sdob);
			java.sql.Date sqdob=new java.sql.Date(udob.getTime());//util date to sql date
			
			//convert String TOP (Time of purchase) to java.sql.Time obj
			java.sql.Time sqtop=java.sql.Time.valueOf(stop);
			
			//convert String Order date time to java.sql.TimeStamp obj
			SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			java.util.Date uorderdt=sdf1.parse(sorderdt);
			java.sql.Timestamp sqorderdt=new java.sql.Timestamp(uorderdt.getTime());//Util date to Timestamp obj
			
			//set values SQL Query params
			if(ps!=null) {
				ps.setString(1,name);
				ps.setFloat(2, billamt);
				ps.setDate(3,sqdob);
				ps.setTime(4, sqtop);
				ps.setTimestamp(5, sqorderdt);
				
				//execute the SQL Query
				int count=ps.executeUpdate();
				if(count==0)
					System.out.println("Recored not inserted");
				else
					System.out.println("Record Inserted");
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
