package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.pro1.connector.ConnectorFactory;

public class ImagePathRes {

	private static final String UPDATE_BLOB = "UPDATE `restaurants` SET `Image_Path` = ? WHERE `Restaurant_ID` = ?";
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			Connection con = ConnectorFactory.requestConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE_BLOB);
			System.out.println("ID: ");
			int id = scan.nextInt();
			pstmt.setInt(2, id);
			String path = "C:\\Users\\bhuva\\OneDrive\\Desktop\\Projects\\FoodOrderingAppl\\src\\main\\webapp\\assets\\res1.jpg";
			
			FileInputStream fis = new FileInputStream(path);
			pstmt.setBinaryStream(1,fis);
			
			int res = pstmt.executeUpdate();
			System.out.print(res);
			scan.close();
		} catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
