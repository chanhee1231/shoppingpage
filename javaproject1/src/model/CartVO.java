package model;

public class CartVO {

	private int cartIndex;
	private String productCode;
	private String id;
	private String productName;
	private int price;

	public CartVO() {
		super();
	}

	public CartVO(int cartIndex, String productCode, String id, String productName, int price) {
		super();
		this.cartIndex = cartIndex;
		this.productCode = productCode;
		this.id = id;
		this.productName = productName;
		this.price = price;
	}

	public int getCartIndex() {
		return cartIndex;
	}

	public void setCartIndex(int cartIndex) {
		this.cartIndex = cartIndex;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "CartVO [cartIndex=" + cartIndex + ", productCode=" + productCode + ", productName=" + productName + ", id="
				+ id + ", price=" + price + "]";
	}
	
	
	
	
	
}
