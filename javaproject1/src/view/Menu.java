package view;

public class Menu {

	// 학과 메뉴
	public static void memberManagerMenu() {
		System.out.println();
		System.out.println("===============");
		System.out.println("1. 로그인");
		System.out.println("2. 회원	가입");
		System.out.println("3. 프로그램 종료");
		System.out.println("===============");
		System.out.print("번호 선택 : ");
	}
	public static void userMenu() {
		System.out.println("================== menu ===========");
		System.out.println("1. 상품 목록 출력    	2. 장바구니 보기");
		System.out.println("3. 장바구니 담기  		4. 장바구니 삭제");
		System.out.println("5. 장바구니 초기화  		6. 쿠폰 목록확인");
		System.out.println("7. 결제하기	    	8. 로그 아웃");
		System.out.println("===================================");
	}
	//관리자 메뉴 메소드
	public static void adminMenu() {
		System.out.println("====== menu ======");
		System.out.println("1. 사용자 목록 확인");
		System.out.println("2. 판매 상품 추가");
		System.out.println("3. 판매 상품 삭제");
		System.out.println("4. 판매 상품 수정");
		System.out.println("5. 쿠폰 지급");
		System.out.println("6. 로그 아웃");
		System.out.println("==================");
	}
	
}
