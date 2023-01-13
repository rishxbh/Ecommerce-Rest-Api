package com.rishabh.website.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.website.model.Category;
import com.rishabh.website.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public void createCategory(Category category) {
		categoryRepo.save(category);
	}

	@Override
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public void updateCategory(int id, Category updatedCategory) {
		Category category = categoryRepo.findById(id).get();
		category.setCategoryName(updatedCategory.getCategoryName());
		category.setDescription(updatedCategory.getDescription());
		category.setImgaeUrl(updatedCategory.getImgaeUrl());
		categoryRepo.save(category);
	}

	@Override
	public Optional<Category> findById(int id) {
		return categoryRepo.findById(id);
	}
	
}
