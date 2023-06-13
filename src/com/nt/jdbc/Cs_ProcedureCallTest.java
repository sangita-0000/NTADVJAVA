package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Cs_ProcedureCallTest {

	private static final String CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_BY_EMPNO(?,?,?)}";
	
	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
			
			//read inputs
			int no=0;
			if(sc!=null) {
				System.out.println("Enter Emp no::");
				no=sc.nextInt();
			}
			if(cs!=null) {
				//register OUT params with jdbc types
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.FLOAT);
				//set values to IN params
				cs.setInt(1, no);
				//gather result from out params
				System.out.println("EMO NAME::"+cs.getString(2));
				System.out.println("EMP SALARY::"+cs.getFloat(3));
			}//if
		}//try
		
		catch (SQLException se) {
			if(se.getErrorCode()==1403)
				System.out.println("Enter Number not found");
			else if(se.getErrorCode()==1017)
				System.out.println("Invalide credentials");
			else
				System.out.println("Some DB problem");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
