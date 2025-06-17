package main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import controller.CartDAO;
import controller.CartManager;
import controller.CouponManager;
import controller.DBUtil;
import controller.ProductManager;
import controller.UserManager;
import model.UserVO;
import view.AdminMenuChoice;
import view.MemberManagerMenuChoice;
import view.Menu;
import view.UserMenuChoice;

public class JavaProjectClassMain {
	public static UserVO loggedInUser;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection con = DBUtil.getConnection();
		if (con == null) {
			System.out.println("DB 연결 실패ㅐㅐ");
			return;
		}
		System.out.println("DB 연결 성공");
		UserManager um = new UserManager();
		int choice = 0;
		UserVO loggedInUser = null;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.memberManagerMenu();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case MemberManagerMenuChoice.LOGIN:
					loggedInUser = um.login();
					if (loggedInUser == null) {
						System.out.println("프로그램 종료합니다.");
						return;
					}
					switch (loggedInUser.getId()) {
					case "admin":
						adminMain();
						break;
					default:
						userMain();
						break;
					}
					break;
				case MemberManagerMenuChoice.JOIN:
					um.join(scan);
					break;
				case MemberManagerMenuChoice.EXIT:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다. \n프로그램 다시 실행하세요");
				exitFlag = true;
			}
		}
		System.out.println("끝");
	}
	private static void userMain() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.userMenu();
				choice = Integer.parseInt(scan.nextLine());
				ProductManager pm = new ProductManager();
				CartManager cm = new CartManager();
				CouponManager cpm = new CouponManager();
				switch (choice) {
				case UserMenuChoice.PRODUCT_LIST:
					pm.list();
					break;
				case UserMenuChoice.CART_LIST:
					cm.cartList();
					break;
				case UserMenuChoice.ADD_CART:
					cm.AddCart();
					break;
				case UserMenuChoice.DELETE_CART:
					cm.Delete();
					break;
				case UserMenuChoice.CLEAR_CART:
					cm.DeleteAll();
					break;
				case UserMenuChoice.COUPON_LIST:
					cpm.couponList();
					break;
				case UserMenuChoice.PAYMENT:
					cm.selectAndPayCart();
					break;
				case UserMenuChoice.LOGOUT:
					System.out.println("사용자 모드 종료");
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println(" 예외 발생");
				e.printStackTrace();
			}
		}
	}
	private static void adminMain() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.adminMenu();
				choice = Integer.parseInt(scan.nextLine());
				ProductManager pm = new ProductManager();
				UserManager um = new UserManager();
				CouponManager cpm = new CouponManager();
				switch (choice) {
				case AdminMenuChoice.USER_LIST:
					um.list();
					break;
				case AdminMenuChoice.ADD_PRODUCT:
					pm.AddProduct();
					break;
				case AdminMenuChoice.DELETE_PRODUCT:
					pm.productDelete();
					break;
				case AdminMenuChoice.UPDATE_PRODUCT:
					pm.update();
					break;
				case AdminMenuChoice.ADD_COUPON:
					cpm.addCoupon();
					break;
				case AdminMenuChoice.LOGOUT:
					System.out.println("관리자 모드 종료");
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("관리 예외 발생");
			}
		}
	}
}