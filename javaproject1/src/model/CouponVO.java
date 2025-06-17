package model;

public class CouponVO {

	private String couponCode;
	private int value;
	private String productCode;
	private String id;
	public CouponVO() {
		super();
	}
	public CouponVO(String couponCode, int value, String productCode, String id) {
		super();
		this.couponCode = couponCode;
		this.value = value;
		this.productCode = productCode;
		this.id = id;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
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
	@Override
	public String toString() {
		return "CouponVO [couponCode=" + couponCode + ", value=" + value + ", productCode=" + productCode + ", ID=" + id
				+ "]";
	}
	

	
}
