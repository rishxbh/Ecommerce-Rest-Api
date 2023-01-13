package com.rishabh.website.dto;

import com.rishabh.website.model.Cart;
import com.rishabh.website.model.Product;

public class CartItemDto {
	private int id;
	private int quantity;
	private Product product;
	public CartItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItemDto(Cart cart) {
		this.id = cart.getId();
		this.quantity = cart.getQuantity();
		this.setProduct(cart.getProduct());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
