package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.ProductVO;


public class ProductManager {

	public void list() throws Exception {
		ProductDAO pd = new ProductDAO();
		System.out.println("=====도서정보리스트=====");
		ArrayList<ProductVO> _productList = pd.selectAll();
		
		if(_productList.size() <= 0 || _productList == null) {
			System.out.println("책정보리스트에서 오류가 발생했습니다.");
			return;
		}
		for(ProductVO data:_productList) {
			System.out.println(data.toString());
		}
	}
	public void AddProduct() throws Exception {
		Scanner input = new Scanner(System.in);

		ProductDAO pd = new ProductDAO();
		ProductVO pvo = new ProductVO();

		String pcode;
		String pname; 
		int price;
		String gender; 
		String size; 
		String category;
		
		System.out.println("상품 정보 입력");
		System.out.print("상품코드 : ");
		pcode = input.nextLine();
		System.out.print("상품명  : ");
		pname = input.nextLine();
		System.out.print("가격 : ");
		price = Integer.parseInt(input.nextLine());
		System.out.print("사용 가능 성별  : ");
		gender = input.nextLine();
		System.out.print("상품 사이즈  : ");
		size = input.nextLine();
		System.out.print("상품 분류  : ");
		category = input.nextLine();

		pvo.setProductCode(pcode);
		pvo.setProductName(pname);
		pvo.setPrice(price);
		pvo.setGender(gender);
		pvo.setSize(size);
		pvo.setCategory(category);

		pd.insert(pvo);

		System.out.println();
		System.out.println("학과 전체 리스트");
		list();
		System.out.println();
	}
	public void update() throws Exception {
		Scanner input = new Scanner(System.in);
		ProductDAO pd = new ProductDAO();
		ProductVO pvo = new ProductVO();

		String pcode;
		String pname; 
		int price;
		String gender; 
		String size; 
		String category;

		System.out.println("학과 전체 리스트");
		list();
		System.out.println();

		System.out.println("수정할 학과 상품코드 입력");
		System.out.print("상품코드 : ");
		pcode = input.nextLine();

		System.out.println();
		System.out.println("상품 정보 입력");
		System.out.print("상품명  : ");
		pname = input.nextLine();
		System.out.print("가격 : ");
		price = Integer.parseInt(input.nextLine());
		System.out.print("사용 가능 성별  : ");
		gender = input.nextLine();
		System.out.print("상품 사이즈  : ");
		size = input.nextLine();
		System.out.print("상품 분류  : ");
		category = input.nextLine();

		pvo.setProductCode(pcode);
		pvo.setProductName(pname);
		pvo.setPrice(price);
		pvo.setGender(gender);
		pvo.setSize(size);
		pvo.setCategory(category);
		int count = pd.update(pvo);
		if(count == 0) {
			System.out.println("학과정보 수정 오류발생 조치바람");
			return;
		}else {
			System.out.print(" 수정 성공 \n");
		}
		System.out.println();
		System.out.println("학과 전체 리스트");
		list();
		System.out.println();
	}
	public void productDelete() throws Exception {
		Scanner input = new Scanner(System.in);

		ProductDAO pd = new ProductDAO();
		ProductVO pvo = new ProductVO();

		String pcode; // 입력한 일련번호
		System.out.println("상품 전체 리스트");
		list();
		System.out.println();

		System.out.println("삭제할 학과번호 입력");
		System.out.print("상품코드 : ");
		pcode = input.nextLine();
		pvo.setProductCode(pcode);
		int count = pd.deleteByPCODE(pvo);
		if(count == 0) {
			System.out.printf("%s 상품 삭제 문제발생 조치바람\n",pcode);
			return;
		}else {
			System.out.printf("%s 상품 삭제 성공 \n",pcode);
		}
		System.out.println();
		System.out.println("학과 전체 리스트");
		list();
		System.out.println();
	}

	
}
