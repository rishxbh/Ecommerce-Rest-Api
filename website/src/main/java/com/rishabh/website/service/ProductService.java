package com.rishabh.website.service;

import com.rishabh.website.dto.ProductDto;
import com.rishabh.website.model.Category;
import com.rishabh.website.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	void createProduct(ProductDto productDto, Category category);

	List<ProductDto> getAllProducts();

	Product findById(int id);

	void updateCategory(int id, ProductDto productDto);
	
	ProductDto convert(Product product);
 	
}
