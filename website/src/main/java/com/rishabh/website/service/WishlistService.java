package com.rishabh.website.service;

import java.util.List;

import com.rishabh.website.dto.ProductDto;
import com.rishabh.website.model.User;
import com.rishabh.website.model.Wishlist;

public interface WishlistService {

	void createWishlist(Wishlist wishlist);

	List<ProductDto> getWishListForUser(User user);

	void delete(int id, User user);

}
