package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ProductVO;


public class ProductDAO {
	private static String selectSQL = "SELECT * FROM PRODUCT";
	private static String insertSQL = "INSERT INTO PRODUCT (PRODUCT_CODE, PRODUCT_NAME, PRICE, GENDER, PRODUCT_SIZE, CATEGORY) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static String updateSQL = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRICE = ?, CATEGORY = ?, PRODUCT_SIZE =?, GENDER = ? WHERE PRODUCT_CODE = ?";
	private static String deleteSQL = "DELETE FROM PRODUCT WHERE PRODUCT_CODE = ?";
	public static ArrayList<ProductVO> selectAll() {
		 ArrayList<ProductVO> productList = new ArrayList<>(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String pcode = rs.getString("PRODUCT_CODE");
				String pname = rs.getString("PRODUCT_NAME");
				int price = rs.getInt("PRICE");
				String gender = rs.getString("GENDER");
				String size = rs.getString("PRODUCT_SIZE");
				String category= rs.getString("CATEGORY");
				ProductVO subject = new ProductVO(pcode, pname, price,gender,size,category);
				productList.add(subject);
			}

		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
			e.printStackTrace();
		} finally {
			DBUtil.DBClose(con, pstmt, rs);
		}
		return productList;
	}
	public int insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(insertSQL);			
			pstmt.setString(1, productVO.getProductCode());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setString(4, productVO.getGender());
			pstmt.setString(5, productVO.getSize());
			pstmt.setString(6, productVO.getCategory());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
			 e.printStackTrace(); 
		} finally {
			DBUtil.DBClose(con, pstmt);
		}
		return count;
	}
	public int update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, productVO.getProductName());
			pstmt.setInt(2, productVO.getPrice());
			pstmt.setString(3, productVO.getGender());
			pstmt.setString(4, productVO.getSize());
			pstmt.setString(5, productVO.getCategory());
			pstmt.setString(6, productVO.getProductCode());			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
			 e.printStackTrace(); 
		} finally {
			DBUtil.DBClose(con, pstmt);
		}
		return count;
	}
	public int deleteByPCODE(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setString(1, productVO.getProductCode());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
			 e.printStackTrace(); 
		} finally {
			DBUtil.DBClose(con, pstmt);
		}
		return count;

	}
}
