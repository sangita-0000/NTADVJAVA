package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransforRecordFromOracleToMySQL {
	private final static String MYSQL_INSERT_PRODUCT_QUERY="INSERT INTO PRODUCT VALUES(?,?,?,?)";
	private final static String ORACLE_SELECT_QUERY="SELECT * FROM PRODUCT";

	public static void main(String[] args) {
		try(Connection conOracle=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
		Connection mySQL=DriverManager.getConnection("jdbc:mysql:///ntaj414bd1","root","root");
		Statement st=conOracle.createStatement();
		PreparedStatement ps=mySQL.prepareStatement(MYSQL_INSERT_PRODUCT_QUERY)){
			//execute the select query in Oracle DB s/w
			try(ResultSet rs=st.executeQuery(ORACLE_SELECT_QUERY)){
				//process the REsultSet Object and also insert to MySql Db s/w
				
				int count=0;
				if(rs!=null && ps!=null) {
					while(rs.next()) {
						int id=rs.getInt(1);
						String name=rs.getString(2);
						float price=rs.getFloat(3);
						float qty=rs.getFloat(4);
						//Set the above values Insert sql query param values to mysql
						ps.setInt(1, id);
						ps.setString(2, name);
						ps.setFloat(3, price);
						ps.setFloat(4, qty);
						//execute query
						int result=ps.executeUpdate();
						//process the result
						if(result==0)
							System.out.println("Record not inserted");
						else
							System.out.println("Record Inserted");
						count++;
						
					}//while
					System.out.println(count+"::NO of records copied from Oracle db to MySQL db tools");
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
