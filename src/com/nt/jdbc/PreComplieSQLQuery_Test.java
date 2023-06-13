package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreComplieSQLQuery_Test {

	private static final String GET_ALL_STUDENT_DETAIL="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		try(//establish the Connection
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				//create preparedStatement object
				PreparedStatement ps=con.prepareStatement(GET_ALL_STUDENT_DETAIL);
				//execute pre-compiled SQL Query
				ResultSet rs=ps.executeQuery();){
			//process the result set object
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
			}//if
			
		}//try
		catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}

}
