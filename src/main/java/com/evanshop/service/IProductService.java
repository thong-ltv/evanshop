package com.evanshop.service;

import java.util.ArrayList;
import java.util.List;

import com.evanshop.model.ProductModel;
import com.evanshop.model.SizeColorProductModel;

public interface IProductService {
	List<ProductModel> getProducts();
	List<ProductModel> getProductByCategoryId(int id);
	ProductModel getProductByProductId(int product_id);
	ArrayList<String> getSizesByProductId(int product_id);
	ArrayList<String> getColorsByProductId(int product_id);
	ArrayList<String> getFileUrlsByProductId(int product_id);
	List<SizeColorProductModel> getSizeColorByProductId(int product_id);
}
