package controller;

import java.util.ArrayList;
import java.util.Scanner;

import main.JavaProjectClassMain;
import model.UserVO;

public class UserManager {

	public void list() throws Exception {
		UserDAO ud = new UserDAO();
		ArrayList<UserVO> _userList = ud.selectAll();

		System.out.println("=====판매품목 리스트=====");
		if (_userList.size() <= 0 || _userList == null) {
			System.out.println("판매품목 리스트에서 오류가 발생했습니다.");
			return;
		}
		for (UserVO data : _userList) {
			System.out.println(data.toString());
		}
	}

	public UserVO login() {
	    Scanner scan = new Scanner(System.in);
	    UserDAO ud = new UserDAO();
	    UserVO user = null;

	    int attempts = 0;

	    while (attempts < 3) {
	        System.out.print("id : ");
	        String id = scan.nextLine();
	        System.out.print("password : ");
	        String password = scan.nextLine();

	        user = ud.login(id, password);

	        if (user != null) {
	            JavaProjectClassMain.loggedInUser = user;
	            System.out.println(user.getName() + "님 환영합니다.");
	            return user;
	        }

	        attempts++;
	        if (attempts < 3) {
	            System.out.println("로그인 실패: 아이디 또는 비밀번호가 틀렸습니다.");
	            System.out.println("남은 시도 횟수: " + (3 - attempts));
	        }
	    }

	    // 3번 시도 실패 후
	    System.out.println("로그인 시도 3회 초과. 초기화면으로 돌아갑니다.");
	    return null;
	}

	public void join(Scanner scan) {
		UserDAO ud = new UserDAO();
		System.out.println("==== 회원가입 ====");
		System.out.print("아이디: ");
		String id = scan.nextLine();
		System.out.print("비밀번호: ");
		String pwd = scan.nextLine();
		System.out.print("이름: ");
		String name = scan.nextLine();

		UserVO newUser = new UserVO();
		newUser.setId(id);
		newUser.setPwd(pwd);
		newUser.setName(name);

		int result = ud.insertUser(newUser);
		if (result > 0) {
			System.out.println("회원가입이 완료되었습니다!");
		} else {
			System.out.println("회원가입 실패! 다시 시도해주세요.");
		}
	}
}
