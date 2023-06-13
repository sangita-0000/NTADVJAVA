package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreCompiledSQL_Query_Test {

	private final static String EMP_DETAILLS="SELECT EMPNO,ENAME,JOB,DEPTNO FROM EMP";
	public static void main(String[] args) {
		
		try(//establish the connection between jdbc application in Db s/w
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				//create PreparedStatement object
				PreparedStatement ps=con.prepareStatement(EMP_DETAILLS);
				//execute the PreCompiled Query
				ResultSet rs=ps.executeQuery();){
			//process the ResultSet
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				}
			}
			
		}catch (Exception e) {
		
			e.printStackTrace();
		}

	}

}
