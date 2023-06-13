package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSelectTest01 {

	private static final String SELECT_QUERY="SELECT * FROM STUDENT";
	
	public static void main(String[] args) {

		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
				PreparedStatement ps=con.prepareStatement(SELECT_QUERY);
				ResultSet rs=ps.executeQuery();){
				if(rs!=null) {
				while(rs.next()){
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getString(5));
				}
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
