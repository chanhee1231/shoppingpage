package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserVO;

public class UserDAO {
	private static String selectSQL = "SELECT * FROM USER_INFO";
	private static String LoginSQL = "SELECT * FROM USER_INFO WHERE ID = ? AND PWD = ?";
	private static String InsertUserSQL = "INSERT INTO USER_INFO (ID, PWD, NAME) VALUES (?, ?, ?)";

	public static ArrayList<UserVO> selectAll() {
		ArrayList<UserVO> userList = new ArrayList<>();
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
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String name = rs.getString("NAME");
				UserVO user = new UserVO(id, pwd, name);
				userList.add(user);
			}

		} catch (SQLException e) {
			System.out.println("createstatement 오류발생");
		} finally {
			DBUtil.DBClose(con, pstmt, rs);
		}
		return userList;
	}

	public UserVO login(String id, String pwd) {
		UserVO user = null;
		try {
			Connection con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return null;
			}
			PreparedStatement pstmt = con.prepareStatement(LoginSQL);

			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					user = new UserVO();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public int insertUser(UserVO user) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(InsertUserSQL);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPwd());
            pstmt.setString(3, user.getName());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("회원가입 중 오류 발생");
            e.printStackTrace();
        } finally {
            DBUtil.DBClose(con, pstmt);
        }

        return result;
    }
}
