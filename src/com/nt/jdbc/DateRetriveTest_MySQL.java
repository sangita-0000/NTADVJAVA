package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetriveTest_MySQL {

	private final static String GET_CUSTOMER_QUERY="SELECT * FROM CUSTOMER_INFO";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414bd1","root","root");
				PreparedStatement ps=con.prepareStatement(GET_CUSTOMER_QUERY);
				ResultSet rs=ps.executeQuery();){
			
			//process the resultSet
			if(rs!=null) {
				while(rs.next()) {
					int cno=rs.getInt(1);
					String cname=rs.getString(2);
					float billamt=rs.getFloat(3);
					java.sql.Date sqdob=rs.getDate(4);
					java.sql.Time sqtop=rs.getTime(5);
					java.sql.Timestamp sqorderdatetime=rs.getTimestamp(6);
					
					//convert java.sql.Date class obj to String date values in the required pattern
					SimpleDateFormat sdf=new SimpleDateFormat("MMM-yyyy-dd");
					String sdob=sdf.format(sqdob);
					
					//convert java.sql.Time class obj to String time value in the required pattern
					long ms1=sqtop.getTime();
					java.util.Date utop=new java.util.Date(ms1);
					SimpleDateFormat sdf1=new SimpleDateFormat("mm:hh:ss");
					String stop=sdf1.format(utop);
					
					
					//convert java.sql.Timestamp class obj to String date ,time values in the required pattern
				
					long ms2=sqorderdatetime.getTime();
					java.util.Date uorderdatetime=new java.util.Date(ms2);
					SimpleDateFormat sdf2=new SimpleDateFormat("mm:hh:ss yyyy-dd-MMM");
					String sorderdatetime=sdf2.format(uorderdatetime);
					System.out.println("cno:"+cno+"cname:"+cname+"bill amount:"+billamt+"DOB:"+sdob+"TOP:"+stop+"OrderdateTime:"+sorderdatetime);
					
				}//while
			}//if
			
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
