package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Select_Test {

	public static void main(String[] args) throws Exception {
		//load jdbc driver class
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Establish the connection
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Sangita");
		//create jdbc statement object
		Statement st=con.createStatement();
		//send and execute Query in DB s/w
		ResultSet rs=st.executeQuery("SELECT * FROM STUDENT");
		//Process the resultSet Object
		while(rs.next()!=false) {
			System.out.println(rs.getInt(1)+"	"+rs.getString(2)+"		"+rs.getString(3)+"	"+rs.getInt(4)+"		"+rs.getString(5));
			
		}//while
		//close jdbc object
		rs.close();
		st.close();
		con.close();
	}//main

}//class
