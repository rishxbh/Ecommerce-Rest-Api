package com.rishabh.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rishabh.website.common.ApiResponse;
import com.rishabh.website.dto.AddToCartDto;
import com.rishabh.website.dto.CartDto;
import com.rishabh.website.model.User;
import com.rishabh.website.service.AuthService;
import com.rishabh.website.service.CartService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token) {
		authService.authenticateToken(token);
		User user = authService.getUser(token);
		cartService.addToCart(addToCartDto, user);
		return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
		authService.authenticateToken(token);
		User user = authService.getUser(token);
		CartDto cartDto = cartService.getCartItems(user);
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteItem(@PathVariable("id") int id, @RequestParam("token") String token) {
		authService.authenticateToken(token);
		User user = authService.getUser(token);
		cartService.deleteItem(id,user);
		return new ResponseEntity<>(new ApiResponse(true, "Deleted from cart"), HttpStatus.OK);
	}
	
}
