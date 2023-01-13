package com.rishabh.website.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "wishlist")
public class Wishlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(targetEntity = User.class, fetch  = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product products;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public Wishlist(int id, User user, Date createdDate, Product products) {
		super();
		this.id = id;
		this.user = user;
		this.createdDate = createdDate;
		this.products = products;
	}

	public Wishlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wishlist(User user, Product products) {
		super();
		this.user = user;
		this.products = products;
		this.createdDate = new Date();
	}
	
	
	
}
