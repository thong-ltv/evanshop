package com.evanshop.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.evanshop.dao.ICategoryDao;
import com.evanshop.dao.impl.CategoryDao;
import com.evanshop.model.CategoryModel;
import com.evanshop.service.ICategoryService;

public class CategoryService implements ICategoryService {

//	@Inject
	private ICategoryDao categoryDao;
	
	public CategoryService() {
		this.categoryDao = new CategoryDao();
	}
	
	@Override
	public List<CategoryModel> getCategories() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public String getCategoryByProductId(int product_id) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryByProductId(product_id);
	}

}
