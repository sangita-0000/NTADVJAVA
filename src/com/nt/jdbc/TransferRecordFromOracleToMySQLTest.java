package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferRecordFromOracleToMySQLTest {

	private final static String MYSQL_INSERT_PRODUCT_QUERY="INSERT INTO PRODUCT VALUES(?,?,?,?)";
	private final static String ORACLE_SELECT_QUERY="SELECT * FROM PRODUCT";
	
	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				Connection mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj414bd1","root","root");
				Statement st=con.createStatement();
				PreparedStatement ps=mysqlCon.prepareStatement(MYSQL_INSERT_PRODUCT_QUERY);){
			
				//execute the SELECT SQL Query in Oracle Db s/w
			try(ResultSet rs=st.executeQuery(ORACLE_SELECT_QUERY)){
				//process the ResultSet and also insert record to mysql Db s/w
				int count=0;
				if(rs!=null && ps!=null) {
					while(rs.next()) {
					//get each record from oracle db table
					int id=rs.getInt(1);
					String name=rs.getString(2);
					float price=rs.getFloat(3);
					float qty=rs.getFloat(4);
					
					
					//set the above values INSERT SQL Query param values to mysql db table
					ps.setInt(1, id);
					ps.setString(2, name);
					ps.setFloat(3, price);
					ps.setFloat(4, qty);
					
					
					//execute the Query
					int result=ps.executeUpdate();
					if(result==0)
						System.out.println("Record not inserted");
					else
						System.out.println("Record inserted");
					count++;
				}//while
				System.out.println(count+"no of records inserted Oracle db table to mysql db table");
			}//if
			}//try
		}
			catch (SQLException se) {
				
				se.printStackTrace();
			}
			catch (Exception e) {
	
				e.printStackTrace();
			}
		
	}

}
