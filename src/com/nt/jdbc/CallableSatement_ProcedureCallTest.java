package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



public class CallableSatement_ProcedureCallTest {
	
	private static final String CALL_PROCEDURE="{call p_sum_data(?,?,?)}";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
				){
			//read input from end user
			int val1=0,val2=0;
			if(sc!=null) {
				System.out.println("Enter values 1::");
				val1=sc.nextInt();
				System.out.println("Enter values 2::");
				val2=sc.nextInt();
			}
			if(cs!=null) {
				//register OUT params with JDBC type(All out, return params must be register
				cs.registerOutParameter(3,Types.INTEGER);
				//set values  to IN params
				cs.setInt(1, val1);
				cs.setInt(2, val2);
				//cal pl/sql procedure
				cs.execute();
				//gather results from out params
				int result=cs.getInt(3);
				System.out.println("Result ::"+result);
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
