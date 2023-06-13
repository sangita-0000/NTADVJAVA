package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*CREATE DEFINER=`root`@`localhost` PROCEDURE `P_GET_PROD_DETAILS_BY_PRICE_RANGE`(IN startPrice FLOAT, IN endPrice FLOAT)
BEGIN
SELECT 8 FROM PRODUCT WHERE PRICE>=startPrice and PRICE<=endPrice;

END
*/
public class CS_ProcedureCall_MySQL {

	private static final String CALL_PROCEDURE="{CALL P_GET_PROD_DETAILS_BY_PRICE_RANGE(?,?)}";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414bd1","root","root");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
			
			//read input
			float startPrice=0.0f, endPrice=0.0f;
			if(sc!=null) {
				System.out.println("Enter Start Price::");
				startPrice=sc.nextFloat();
				System.out.println("Enter end price::");
				endPrice=sc.nextFloat();
			}
			if(cs!=null) {
				//set values tio IN params
				cs.setFloat(1,startPrice);
				cs.setFloat(2,endPrice);
				//CALL PL/SQL procedure
				cs.execute();
				try(ResultSet rs=cs.getResultSet()){
					//dispaly records
					while(rs.next()) {
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getInt(4));
						
					}
				}
			}//if
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
