package controller;

import java.util.ArrayList;
import java.util.Scanner;

import main.JavaProjectClassMain;
import model.CartVO;
import model.CouponVO;
import model.UserVO;

public class CouponManager {
	public void couponList() throws Exception {
		UserVO userId = JavaProjectClassMain.loggedInUser;
		CouponDAO cpd = new CouponDAO();
		CouponVO couponVO = new CouponVO();
		couponVO.setId(userId.getId());
		ArrayList<CouponVO> _couponList = cpd.selectById(couponVO);
		
		if (_couponList == null || _couponList.size() <= 0)  {
			System.out.println("쿠픈 목록에서 오류가 발생했습니다.");
			return;
		}
		for(CouponVO data:_couponList) {
			System.out.println(data.toString());
		}
	}
	public void addCoupon() {
		CouponDAO cpd = new CouponDAO();
		CouponVO cpvo = new CouponVO();
		Scanner input = new Scanner(System.in);
		
		String ccode;
		int value;
		String pcode;
		String id;
		
		 System.out.println("추가할 쿠폰 정보 입력");
		 System.out.print("쿠폰코드 : ");
		 ccode = input.nextLine();
		 System.out.print("쿠폰 값 : ");
		 value = Integer.parseInt(input.nextLine());
		 System.out.print("사용 가능 상품코드 : ");
		 pcode = input.nextLine();
		 System.out.print("사용자 ID : ");
		 id = input.nextLine();
		 
		 cpvo.setCouponCode(ccode);
		 cpvo.setValue(value);
		 cpvo.setProductCode(pcode);
		 cpvo.setId(id);

		 cpd.insert(cpvo);
	}

}
