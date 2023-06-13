package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class LOBsRetriveTest_Oracle {

	private static final String GET_CRICKETER_QUERY="SELECT * FROM CRICKET_INFO WHERE CID=?";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Oracle");
				PreparedStatement ps=con.prepareStatement(GET_CRICKETER_QUERY);){
			
			int aid=0;
			if(sc!=null) {
				System.out.println("Enter Cricketer ID");
				aid=sc.nextInt();
			}
			//set values to Query params
			if(ps!=null)
				ps.setInt(1, aid);
			//execute the Query
			try(ResultSet rs=ps.executeQuery()){
				if(rs!=null) {
					if(rs.next()) {
						int id=rs.getInt(1);
						String name=rs.getString(2);
						try(//read LOBS from ResultSet as stream
								InputStream is=rs.getBinaryStream(3);
								Reader reader=rs.getCharacterStream(4);
								//create empty destination file using Stream
								OutputStream os=new FileOutputStream("Reteive_photo.jepg");
								Writer writer=new FileWriter("retrive_profile.txt")
										){
							//copy LOB collected from Db table to destination file
							IOUtils.copy(is,os);
							IOUtils.copy(reader,writer);
							System.out.println("Cricketer Info::"+id+" "+name+" LOBs are retrived");
						}//try3
					}//if
					else {
						System.out.println("Cricketer Info Not Found");
					}
						
				}//if
			}//try2
		}//try1
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
