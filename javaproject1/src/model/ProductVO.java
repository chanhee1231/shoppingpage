package model;

public class ProductVO {

	private String productCode;
	private String productName;
	private int price;
	private String gender;
	private String size;
	private String category;
	public ProductVO() {
		super();
	}
	public ProductVO(String productCode, String productName, int price, String gender, String size, String category) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.gender = gender;
		this.size = size;
		this.category = category;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "ProductVO [productCode=" + productCode + ", productName=" + productName + ", price=" + price
				+ ", gender=" + gender + ", size=" + size + ", category=" + category + "]";
	}

	
	
}
