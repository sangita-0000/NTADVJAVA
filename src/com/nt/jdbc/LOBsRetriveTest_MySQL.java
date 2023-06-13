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

public class LOBsRetriveTest_MySQL {

	private static final String GET_CRIKETER_INFO="SELECT * FROM CRICKET_INFO WHERE CID=?";
	
	public static void main(String[] args) {

		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414db1","root","root");
				PreparedStatement ps=con.prepareStatement(GET_CRIKETER_INFO);){
			int cid=0;
			if(sc!=null) {
				System.out.println("Enter cricketer id");
				cid=sc.nextInt();
			}
			//set The Query
			if(ps!=null)
				ps.setInt(1, cid);
			//set the execute Query param
			try(ResultSet rs=ps.executeQuery()){
				if(rs!=null) {
					if(rs.next()) {
						int id=rs.getInt(1);
						String name=rs.getString(2);
						try(//read LOBs from resultset as stream
								InputStream is=rs.getBinaryStream(3);
								Reader reader=rs.getCharacterStream(4);
								//create empty destination file using Stream
								OutputStream os=new FileOutputStream("Retrive_photo.jpeg");
								Writer writer=new FileWriter("Retrive_profile.txt");
								){
							//Copy LOBs collected from DB table to destination file
							IOUtils.copy(is, os);
							IOUtils.copy(reader, writer);
							System.out.println("Criket Info :"+id+" "+name+" Lobs Retrive");
						}//try3
					}//if
					else {
						System.out.println("Cricketer Not Found");
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

