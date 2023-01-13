package com.rishabh.website.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishabh.website.common.ApiResponse;
import com.rishabh.website.dto.ProductDto;
import com.rishabh.website.model.Category;
import com.rishabh.website.service.CategoryService;
import com.rishabh.website.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
		Optional<Category> category = categoryService.findById(productDto.getCategoryId());
		if(!category.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false,"category doesn't exist"), HttpStatus.BAD_REQUEST);
		} else {
			productService.createProduct(productDto, category.get());
			return new ResponseEntity<>(new ApiResponse(true,"product created"), HttpStatus.CREATED);
		}
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getProducts() {
		List<ProductDto> products =  productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PutMapping("update/{productId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("productId") int id, @RequestBody ProductDto productDto) {
		Optional<Category> category = categoryService.findById(productDto.getCategoryId());
    	if (productService.findById(id) == null) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "product does not exists"), HttpStatus.NOT_FOUND);
        } else if(!category.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false,"category doesn't exist"), HttpStatus.BAD_REQUEST);
		}
        productService.updateCategory(id, productDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }
	
}
