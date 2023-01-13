package com.rishabh.website.service;

import com.rishabh.website.dto.AddToCartDto;
import com.rishabh.website.dto.CartDto;
import com.rishabh.website.model.User;

public interface CartService {

	void addToCart(AddToCartDto addToCartDto, User user);

	CartDto getCartItems(User user);

	void deleteItem(int id, User user);

}
