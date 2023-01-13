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
import com.rishabh.website.dto.ProductDto;
import com.rishabh.website.model.Product;
import com.rishabh.website.model.User;
import com.rishabh.website.model.Wishlist;
import com.rishabh.website.service.AuthService;
import com.rishabh.website.service.WishlistService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponse> addToWishlist(@RequestBody Product product, @RequestParam("token") String token) {
		authService.authenticateToken(token);
		User user = authService.getUser(token);
		Wishlist wishlist = new Wishlist(user, product);
		wishlistService.createWishlist(wishlist);
		return new ResponseEntity<>(new ApiResponse(true, "Added To Wishlist"), HttpStatus.CREATED);
	}
	
	@GetMapping("/")
    public ResponseEntity<List<ProductDto>> getWishList(@RequestParam("token") String token) {
        authService.authenticateToken(token);
        User user = authService.getUser(token);
        List<ProductDto> productDtos = wishlistService.getWishListForUser(user);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{id}") 
	public ResponseEntity<ApiResponse> delete(@PathVariable("id") int id, @RequestParam("token") String token) {
		authService.authenticateToken(token);
        User user = authService.getUser(token);
        wishlistService.delete(id, user);
        return new ResponseEntity<>(new ApiResponse(true, "Deleted from Wishlist"), HttpStatus.OK);
	}
	
}
