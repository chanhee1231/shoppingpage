package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CartVO;
import model.CouponVO;
import model.ProductVO;


public class CouponDAO {
	private static String selectByUserIdSQL = "SELECT * FROM COUPON WHERE ID = ?";
	private static String insertSQL = "INSERT INTO COUPON (COUPON_CODE,VALUE, PRODUCT_CODE ,ID) VALUES(?,?,?,?)";
	private static String deleteSQL = "DELETE FROM COUPON WHERE COUPON_CODE = ? AND ID = ?";

	public ArrayList<CouponVO> selectById(CouponVO couponVO) {
		ArrayList<CouponVO> couponList = new ArrayList<CouponVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
			}
			pstmt = con.prepareStatement(selectByUserIdSQL);
			pstmt.setString(1, couponVO.getId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String ccode = rs.getString("COUPON_CODE");
				int value = rs.getInt("VALUE");			
				String pcode = rs.getString("PRODUCT_CODE");
				String userId = rs.getString("ID");
				CouponVO _couponVO = new CouponVO(ccode, value, pcode, userId);
				couponList.add(_couponVO); 
			} else {
			}

		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
		} finally {
			DBUtil.DBClose(con, pstmt, rs);
		}
		return couponList;
	}
	public int insert(CouponVO couponVO) {
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
			pstmt.setString(1, couponVO.getCouponCode());
			pstmt.setInt(2, couponVO.getValue());
			pstmt.setString(3, couponVO.getProductCode());
			pstmt.setString(4, couponVO.getId());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
			e.printStackTrace();
		} finally {
			DBUtil.DBClose(con, pstmt);
		}
		return count;
	}
	public int deleteByCCODE(CouponVO couponVO) {
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
			pstmt.setString(1, couponVO.getCouponCode());
			pstmt.setString(2, couponVO.getId());
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
