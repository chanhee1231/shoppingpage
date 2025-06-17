package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.JavaProjectClassMain;
import model.CartVO;
import model.CouponVO;
import model.ProductVO;
import model.UserVO;

public class CartManager {

	public void cartList() throws Exception {
		UserVO userId = JavaProjectClassMain.loggedInUser;
		CartDAO cd = new CartDAO();
		CartVO cartVO = new CartVO();
		cartVO.setId(userId.getId());
		ArrayList<CartVO> _cartList = cd.selectById(cartVO);

		if (_cartList.size() <= 0 || _cartList == null) {
			System.out.println("장바구니 출력 오류가 발생했습니다.");
			return;
		}
		for (CartVO data : _cartList) {
			System.out.println(data.toString());
		}
	}

	public void AddCart() throws Exception {
		Scanner input = new Scanner(System.in);

		CartDAO cd = new CartDAO();
		CartVO cvo = new CartVO();
		printProductList();
		String pcode;
		String userId = JavaProjectClassMain.loggedInUser.getId();

		System.out.println("상품 정보 입력");
		System.out.print("상품코드 : ");
		pcode = input.nextLine();

		cvo.setProductCode(pcode);
		cvo.setId(userId);

		cd.insert(cvo, userId);

	}

	public void Delete() throws Exception {
		Scanner input = new Scanner(System.in);

		CartDAO cd = new CartDAO();
		CartVO cvo = new CartVO();

		int cartIndex; // 입력한 일련번호
		System.out.println("상품 전체 리스트");
		cartList();
		System.out.println();

		System.out.println("삭제할 cartIndex 입력");
		cartIndex = Integer.parseInt(input.nextLine());
		cvo.setCartIndex(cartIndex);
		int count = cd.deleteByCartIndex(cvo);
		if (count == 0) {
			System.out.printf("%s 상품 삭제 문제발생 조치바람\n", cartIndex);
			return;
		} else {
			System.out.printf("%s 상품 삭제 성공 \n", cartIndex);
		}
	}

	public void DeleteAll() throws Exception {
		Scanner input = new Scanner(System.in);

		CartDAO cd = new CartDAO();
		CartVO cvo = new CartVO();

		String userId = JavaProjectClassMain.loggedInUser.getId(); // 입력한 일련번호

		cvo.setId(userId);
		int count = cd.deleteAll(cvo);
		if (count == 0) {
			System.out.printf("%s님의 장바구니 초기화 문제발생 조치바람\n", userId);
			return;
		} else {
			System.out.printf("%s님의 장바구니 초기화 성공 \n", userId);
		}
	}

	public void printProductList() throws Exception {
		ProductDAO pd = new ProductDAO();
		ArrayList<ProductVO> productList = pd.selectAll();

		System.out.println("===== 상품 목록 =====");
		for (ProductVO product : productList) {
			System.out.println(product.toString());
		}
		System.out.println("===================");
	}

	public void selectAndPayCart() {
		Scanner scan = new Scanner(System.in);
		String userId = JavaProjectClassMain.loggedInUser.getId();
		if (userId != null) {
		    userId = userId.trim();  // 공백 제거
		}
		CouponVO cpvo = new CouponVO();
		CartDAO cd = new CartDAO();
		CartVO cvo = new CartVO();
		cvo.setId(userId);
		ArrayList<CartVO> cartItems = cd.selectById(cvo);
		System.out.println("== 장바구니 목록 ==");
		for (int i = 0; i < cartItems.size(); i++) {
			System.out.println((i + 1) + ". " + cartItems.get(i));
		}
		System.out.print("결제할 항목 번호 입력 (쉼표로 구분): ");
		String[] input = scan.nextLine().split(",");
		ArrayList<CartVO> selectedItems = new ArrayList<>();
		for (String s : input) {
			int idx = Integer.parseInt(s.trim()) - 1;
			if (idx >= 0 && idx < cartItems.size()) {
				selectedItems.add(cartItems.get(idx));
			} else {
				System.out.println("번호 " + (idx + 1) + " 는 목록에 없습니다.");
			}
		}
		int totalPrice = 0;
		for (CartVO item : selectedItems) {
			System.out.println(item);
			totalPrice += item.getPrice(); // 가격 누적
		}
		System.out.print("쿠폰 사용하시겠습니까?(Y/N) : ");
		String response = scan.nextLine();
		if (!(response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N"))) {
			System.out.println("잘못된 입력입니다. Y 또는 N을 입력해주세요.");
		} else if (response.equalsIgnoreCase("Y")) {
			CouponDAO couponDAO = new CouponDAO();
			cpvo.setId(userId);
			ArrayList<CouponVO> couponList = couponDAO.selectById(cpvo);

			if (couponList.isEmpty()) {
				System.out.println("사용 가능한 쿠폰이 없습니다.");
			} else {
				System.out.println("===== 사용 가능한 쿠폰 목록 =====");
				for (CouponVO coupon : couponList) {
					System.out.println("[" + coupon.getId() + "] " + coupon.getCouponCode() + " - 할인 금액: "
							+ coupon.getValue() + "원");
				}
				System.out.print("사용할 쿠폰 번호 입력 (없으면 0): ");
		        try {
		            String selectedCouponCode = scan.nextLine();
		            CouponVO selectedCoupon = null;

		            for (CouponVO coupon : couponList) {
		                if (coupon.getCouponCode().equals(selectedCouponCode)) {
		                    selectedCoupon = coupon;
		                    break;
		                }
		            }

		            if (selectedCoupon != null) {
		                totalPrice -= selectedCoupon.getValue();
		                if (totalPrice < 0) totalPrice = 0;
		                System.out.println("쿠폰이 적용되었습니다.");
		                CouponVO deleteVO = new CouponVO();
		                deleteVO.setCouponCode(selectedCoupon.getCouponCode());
		                deleteVO.setId(userId);
		                int deletedCount = couponDAO.deleteByCCODE(deleteVO);
		                if (deletedCount > 0) {
		                    System.out.println("쿠폰이 적용되었으며 목록에서 삭제되었습니다. 할인 금액: " + selectedCoupon.getValue() + "원");
		                    CartDAO cartDAO = new CartDAO();
		                    int removedCartCount = 0;
		                    for (CartVO item : selectedItems) {  // selectedItems는 결제할 장바구니 항목 리스트
		                        removedCartCount += cartDAO.deleteAll(item); // CartDAO에 항목 삭제 메서드가 있다고 가정
		                    }
		                    if (removedCartCount > 0) {
		                        System.out.println("선택한 장바구니 항목이 결제 후 삭제되었습니다.");
		                    } else {
		                        System.out.println("장바구니 항목 삭제에 실패했습니다.");
		                    }
		                } else {
		                    System.out.println("쿠폰은 적용되었지만 삭제에 실패했습니다.");
		                }
		                
		            } else if (selectedCouponCode != null) {
		                System.out.println("해당 쿠폰은 존재하지 않습니다.");
		            }
		        } catch (Exception e) {
		            System.out.println("정확한 쿠폰코드만 입력해주세요.");
		        }
		    }
		} else {
		    // 사용자가 N/n 입력한 경우
		}

		System.out.println("총 결제 금액: " + totalPrice + "원");
			}
			
		}
	
	
