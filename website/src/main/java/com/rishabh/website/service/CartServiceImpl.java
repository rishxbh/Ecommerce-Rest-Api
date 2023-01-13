package com.rishabh.website.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.website.dto.AddToCartDto;
import com.rishabh.website.dto.CartDto;
import com.rishabh.website.dto.CartItemDto;
import com.rishabh.website.exceptions.CustomException;
import com.rishabh.website.model.Cart;
import com.rishabh.website.model.Product;
import com.rishabh.website.model.User;
import com.rishabh.website.repo.CartRepo;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public void addToCart(AddToCartDto addToCartDto, User user) {
		// TODO Auto-generated method stub
		Product product = productService.findById(addToCartDto.getProductId());
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(addToCartDto.getQuantity());
		cart.setCreatedDate(new Date());
		cartRepo.save(cart);
	}

	@Override
	public CartDto getCartItems(User user) {
		// TODO Auto-generated method stub
		List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDto> cartItems = new ArrayList<>();
		double totalCost = 0;
		for(Cart cart : cartList) {
			CartItemDto cartItemDto = new CartItemDto(cart);
			totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
			cartItems.add(cartItemDto);
		}
		CartDto cartDto = new CartDto(); 
		cartDto.setCartItems(cartItems);
		cartDto.setTotalCost(totalCost);
		return cartDto;
	}

	@Override
	public void deleteItem(int id, User user) {
		// TODO Auto-generated method stub
		Optional<Cart> optionalCart = cartRepo.findById(id);
		if(optionalCart.isEmpty()) {
			throw new CustomException("cart item id is invalid : " + id);
		}
		
		Cart cart = optionalCart.get();
		
		if(cart.getUser() != user) {
			throw new CustomException("cart doesn't belongs to user");
		}
		cartRepo.delete(cart);
	}

}
