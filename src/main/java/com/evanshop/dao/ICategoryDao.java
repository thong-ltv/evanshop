package com.evanshop.dao;

import java.util.List;

import com.evanshop.model.CategoryModel;

public interface ICategoryDao {

	List<CategoryModel> findAll();

	String getCategoryByProductId(int product_id);

}
