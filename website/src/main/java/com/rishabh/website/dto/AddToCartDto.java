package com.rishabh.website.dto;

public class AddToCartDto {
	
	private int id;
	private int productId;
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public AddToCartDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
