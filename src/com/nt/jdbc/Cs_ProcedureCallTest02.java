package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/*create or replace PROCEDURE P_GET_EMP_DETAILS_ENAME_CHARS 
(
  NAMECHARS IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
    OPEN DETAILS FOR 
    SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE NAMECHARS;
END P_GET_EMP_DETAILS_ENAME_CHARS;
*/
public class Cs_ProcedureCallTest02 {

	private static final String CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_ENAME_CHARS(?,?)}";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
			
			//read input
			String initChars=null;
			if(sc!=null) {
				System.out.println("Enter Employeee name initial chars::");
				initChars=sc.next();
			}
			if(cs!=null) {
				//register OUT Params with JDBC types
				cs.registerOutParameter(2,OracleTypes.CURSOR);
				
				//set input values to IN params
				cs.setString(1, initChars+"%");
				//call PL/SQL
				cs.execute();
				//gather ResultSet from OUT params
				try(ResultSet rs=(ResultSet)cs.getObject(2)){
					if(rs!=null) {
						boolean isRSEmpty=false;
						while(rs.next()) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
							isRSEmpty=true;
							
						}//while
						if(!isRSEmpty)
							System.out.println("Record are not found");
						else
							System.out.println("Records are found and display");
					}//if
				}//try2
			}//if
		}//try1
		catch (SQLException se) {
			if(se.getErrorCode()==1403)
				System.out.println("Enter number not Found");
			else if(se.getErrorCode()==1017)
				System.out.println("Invalide Credential");
			else
				System.out.println("Some DB problem");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
