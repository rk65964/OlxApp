package com.olx.service;

import java.util.List;

import com.olx.dto.Category;

public interface CategoryService {

	List<Category> getAllCategories();

	Category getCategoryById(long parseLong);

}
