package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*create or replace PROCEDURE P_GET_STUDENT_BY_SNAME_CAHRS 
(
  NAMECHARS IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
  OPEN DETAILS FOR 
  SELECT SNO,SNAME ,SADD,AVG FROM STUDENT WHERE SNAME LIKE NAMECHARS;
END P_GET_STUDENT_BY_SNAME_CAHRS;
*/
public class Cs_ProcedureCallTest03 {
	private static final String CALL_PROCEDURE="{CALL P_GET_STUDENT_BY_SNAME_CAHRS(?,?)}";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
				){
			//read input values
			String initChars=null;
			if(sc!=null) {
				System.out.println("Enter Student name Initial character::");
				initChars=sc.next();
			}
			if(cs!=null) {
				//register OUT Params with JDBC types
				cs.registerOutParameter(2,OracleTypes.CURSOR);
				
				//set values to IN params
				cs.setString(1, initChars+"%");
				
				//call PL/SQL procedure
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
							System.out.println("Record are Not found");
						else
							System.out.println("Records Are found and displayed");
					}//while
				}//if
			}//try2
			
		}catch (SQLException se) {
			if(se.getErrorCode()==1403)
				System.out.println("Student Number not found");
			else if(se.getErrorCode()==1017)
				System.out.println("Invalide Credential");
			else
				System.out.println("Some DB problem");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
