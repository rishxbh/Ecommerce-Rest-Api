package com.rishabh.website.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.website.dto.ProductDto;
import com.rishabh.website.exceptions.ProductNotExistException;
import com.rishabh.website.model.Category;
import com.rishabh.website.model.Product;
import com.rishabh.website.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public void createProduct(ProductDto productDto, Category category) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setCategory(category);
		product.setPrice(productDto.getPrice());
		productRepo.save(product);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepo.findAll();
		List<ProductDto> productsDto = new ArrayList<>();
		for(Product p : products) {
			productsDto.add(convert(p));
		}
		return productsDto;
	}
	
	public ProductDto convert(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setDescription(product.getDescription());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setImageUrl(product.getImageUrl());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		return productDto;
	}

	@Override
	public Product findById(int id) {
		Optional<Product> product = productRepo.findById(id);
		if(product.isEmpty()) {
			throw new ProductNotExistException("product ID is invalid : " + id);
		} else {
			return product.get();
		}
	}

	@Override
	public void updateCategory(int id, ProductDto productDto) {
		
		Product product = findById(id);
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setPrice(productDto.getPrice());
		productRepo.save(product);
		
	}
	
	
}
