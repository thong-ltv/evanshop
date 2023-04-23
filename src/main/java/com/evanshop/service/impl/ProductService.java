package com.evanshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.evanshop.dao.IProductDao;
import com.evanshop.dao.impl.ProductDao;
import com.evanshop.model.ProductModel;
import com.evanshop.model.SizeColorProductModel;
import com.evanshop.service.IProductService;

public class ProductService implements IProductService {

	private IProductDao productDao;
	
	public ProductService() {
		this.productDao = new ProductDao();
	}
	@Override
	public List<ProductModel> getProducts() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}
	@Override
	public List<ProductModel> getProductByCategoryId(int id) {
		// TODO Auto-generated method stub
		return productDao.getProductByCategoryId(id);
	}
	@Override
	public ProductModel getProductByProductId(int product_id) {
		// TODO Auto-generated method stub
		return productDao.getProductByProductId(product_id);
	}
	@Override
	public ArrayList<String> getSizesByProductId(int product_id) {
		// TODO Auto-generated method stub
		return productDao.getSizesByProductId(product_id);
	}
	@Override
	public ArrayList<String> getColorsByProductId(int product_id) {
		// TODO Auto-generated method stub
		return productDao.getColorsByProductId(product_id);
	}
	@Override
	public ArrayList<String> getFileUrlsByProductId(int product_id) {
		// TODO Auto-generated method stub
		return productDao.getFileUrlsByProductId(product_id);
	}
	@Override
	public List<SizeColorProductModel> getSizeColorByProductId(int product_id) {
		// TODO Auto-generated method stub
		return productDao.getSizeColorByProductId(product_id);
	}

}
