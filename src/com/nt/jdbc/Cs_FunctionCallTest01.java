package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_GET_STUD_DETAILS_BY_SNO
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
) RETURN FLOAT AS 
    SAVG FLOAT;
BEGIN
  SELECT SNAME, AVG INTO NAME, SAVG FROM STUDENT WHERE SNO=NO;
  RETURN SAVG;
END FX_GET_STUD_DETAILS_BY_SNO;
*/
public class Cs_FunctionCallTest01 {
	
	private static final String CALL_FUNCTIONS="{?=CALL FX_GET_STUD_DETAILS_BY_SNO(?,?)}";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				CallableStatement cs=con.prepareCall(CALL_FUNCTIONS);){
			
			//read inputs
			int no=0;
			if(sc!=null) {
				System.out.println("Enter Student No::");
				no=sc.nextInt();
				
			}//if
			if(cs!=null) {
				//register return OUT params with JDBC Data types
				cs.registerOutParameter(1,Types.FLOAT);
				cs.registerOutParameter(3, Types.VARCHAR);
				
				//set values
				cs.setInt(2, no);
				//call PL/SQl function
				cs.execute();
				
				//gather result from return OUT params
				System.out.println("Student AVG ::"+cs.getFloat(1));
				System.out.println("Student Name::"+cs.getString(3));
			}//if
		}//try
		catch (SQLException se) {
			if(se.getErrorCode()==1403)
				System.out.println("Student Not found");
			else 
				System.out.println("Unknown DB problems");
			se.printStackTrace();
						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
