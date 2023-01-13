package com.rishabh.website.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishabh.website.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
