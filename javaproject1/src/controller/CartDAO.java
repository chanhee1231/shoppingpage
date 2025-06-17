package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.JavaProjectClassMain;
import model.CartVO;

public class CartDAO {
	private static String selectByIdSQL = "SELECT C.CART_INDEX, P.PRODUCT_CODE, P.PRODUCT_NAME,C.ID,  P.PRICE  FROM  PRODUCT P "
			+ "	JOIN  CART C ON P.PRODUCT_CODE = C.PRODUCT_CODE WHERE C.ID = ?";
	
	private static String insertSQL = "INSERT INTO CART (CART_INDEX, PRODUCT_CODE,ID) "
			+ "VALUES (CART_SEQ.NEXTVAL, ?, ?)";
	private static String deleteByIndexSQL = "DELETE FROM CART WHERE CART_INDEX= ?";
	private static String deleteAllByIndexSQL = "DELETE FROM CART WHERE ID = ?";
	
	public ArrayList<CartVO> selectById(CartVO cartVO) {
		ArrayList<CartVO> cartList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");

			}
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, cartVO.getId());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int cartIndex = rs.getInt("CART_INDEX");
				String pcode = rs.getString("PRODUCT_CODE");
				String pname = rs.getString("PRODUCT_NAME");
				String userId = rs.getString("ID");
				int price = rs.getInt("PRICE");
				
				CartVO _cartVO = new CartVO(cartIndex,pcode,userId,pname ,price);
				cartList.add(_cartVO);
			}

		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
			e.printStackTrace();
		} finally {
			DBUtil.DBClose(con, pstmt, rs);
		}
		return cartList;
	}
	public int insert(CartVO cartVO,String userId) {
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
			pstmt.setString(1, cartVO.getProductCode());
			pstmt.setString(2, userId);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
			e.printStackTrace();
		} finally {
			DBUtil.DBClose(con, pstmt);
		}
		return count;
	}

	public int deleteByCartIndex(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(deleteByIndexSQL);
			pstmt.setInt(1, cartVO.getCartIndex());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
			e.printStackTrace();
		} finally {
			DBUtil.DBClose(con, pstmt);
		}
		return count;

	}

	public int deleteAll(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(deleteAllByIndexSQL);
			pstmt.setString(1, JavaProjectClassMain.loggedInUser.getId());
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
