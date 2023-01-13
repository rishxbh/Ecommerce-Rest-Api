package com.rishabh.website.service;

import java.util.List;
import java.util.Optional;

import com.rishabh.website.model.Category;

public interface CategoryService {
	public void createCategory(Category category);
	public List<Category> getCategories();
	public void updateCategory(int id, Category category);
	public Optional<Category> findById(int id);
}
