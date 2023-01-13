package com.rishabh.website.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.website.dto.ProductDto;
import com.rishabh.website.exceptions.CustomException;
import com.rishabh.website.model.User;
import com.rishabh.website.model.Wishlist;
import com.rishabh.website.repo.WishlistRepo;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepo wishlistRepo;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public void createWishlist(Wishlist wishlist) {
		wishlistRepo.save(wishlist);
	}

	@Override
	public List<ProductDto> getWishListForUser(User user) {
		List<Wishlist> wishlist = wishlistRepo.findAllByUserOrderByCreatedDateDesc(user);
		List<ProductDto> products = new ArrayList<>();
		for(Wishlist w : wishlist) {
			products.add(productService.convert(w.getProducts()));
		}
		return products;
	}

	@Override
	public void delete(int id, User user) {
		Optional<Wishlist> optionalWishlist = wishlistRepo.findWishlist(user.getId(), id);
		if(optionalWishlist.isEmpty()) {
			throw new CustomException("product id is invalid : " + id);
		}
		Wishlist wishlist = optionalWishlist.get();
		if(wishlist.getUser() != user) {
			throw new CustomException("wishlist does not belong to the user");
		}
		wishlistRepo.delete(wishlist);
	}

}
