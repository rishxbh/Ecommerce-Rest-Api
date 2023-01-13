package com.rishabh.website.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishabh.website.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
