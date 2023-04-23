package com.evanshop.service;

import java.util.List;

import com.evanshop.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> getCategories();
	String getCategoryByProductId(int product_id);
}
