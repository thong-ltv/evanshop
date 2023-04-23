package com.evanshop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.evanshop.dao.ICategoryDao;
import com.evanshop.model.CategoryModel;
import com.evanshop.connect.Connect;

public class CategoryDao implements ICategoryDao {

//	@Inject
	private Connect conn;
	
//	@Inject
	private CategoryModel categoryModel;
	
	public CategoryDao() {
		this.conn = new Connect();
		this.categoryModel = new CategoryModel();
	}
	
	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		List<CategoryModel> listCategories = new ArrayList<CategoryModel>();
		
		String sql = "select * from categories";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String code = rs.getString("code");
				categoryModel = new CategoryModel(id, name, code);
//				categoryModel.setId(id);
//				categoryModel.setName(name);
//				categoryModel.setCode(code);
				
				listCategories.add(categoryModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCategories;
	}

	@Override
	public String getCategoryByProductId(int product_id) {
		// TODO Auto-generated method stub
		
		String category = null;
		String sql = "select categories.name \r\n"
				+ "from categories join products on categories.id = products.category_id\r\n"
				+ "where products.id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, product_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				category = rs.getString("name");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return category;
	}

}
