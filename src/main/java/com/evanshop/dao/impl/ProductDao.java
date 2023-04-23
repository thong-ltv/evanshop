package com.evanshop.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.evanshop.connect.Connect;
import com.evanshop.dao.IProductDao;
import com.evanshop.model.ProductModel;
import com.evanshop.model.SizeColorProductModel;

public class ProductDao implements IProductDao {

	private Connect conn;
	
	private ProductModel productModel;
	private SizeColorProductModel sizeColorProductModel;
	
	public ProductDao() {
		this.conn = new Connect();
		this.productModel = new ProductModel();
		this.sizeColorProductModel = new SizeColorProductModel();
	}
	
	@Override
	public List<ProductModel> findAll() {
		// TODO Auto-generated method stub
		List<ProductModel> listProducts = new ArrayList<ProductModel>();
		
		String sql = "select * from products";
//		String sql = "{call get_six_products(?) }";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
//			CallableStatement cstms = conn.getConn().prepareCall(sql);
//			cstms.setInt(1, page);
			
			ResultSet rs = pstmt.executeQuery();
//			ResultSet rs = cstms.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				int category_id = rs.getInt("category_id");
				int supplier_id = rs.getInt("supplier_id");
				String image = rs.getString("image");
				String short_name = rs.getString("short_name");
				productModel = new ProductModel(id, name, description, price, quantity, category_id, supplier_id, image, short_name, 0);
				
				listProducts.add(productModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listProducts;
	}
	
	//get product theo category_id
	public List<ProductModel> getProductByCategoryId(int id){
		List<ProductModel> listProducts = new ArrayList<ProductModel>();
		
		String sql = "select * from products where category_id = ?";
//		String sql = "{call get_six_products(?) }";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, id);
//			CallableStatement cstms = conn.getConn().prepareCall(sql);
//			cstms.setInt(1, page);
			
			ResultSet rs = pstmt.executeQuery();
//			ResultSet rs = cstms.executeQuery();
			
			while(rs.next()) {
				int id_product = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				int category_id = rs.getInt("category_id");
				int supplier_id = rs.getInt("supplier_id");
				String image = rs.getString("image");
				String short_name = rs.getString("short_name");
				productModel = new ProductModel(id_product, name, description, price, quantity, category_id, supplier_id, image, short_name, 0);
				
				listProducts.add(productModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listProducts;
	}

	@Override
	public ProductModel getProductByProductId(int product_id) {
		// TODO Auto-generated method stub
		ProductModel productModel = new ProductModel();
		String sql = "select * from products where id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, product_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id_product = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				int category_id = rs.getInt("category_id");
				int supplier_id = rs.getInt("supplier_id");
				String image = rs.getString("image");
				String short_name = rs.getString("short_name");
				int sell_quantitys = rs.getInt("sell_quantitys");
				productModel = new ProductModel(id_product, name, description, price, quantity, category_id, supplier_id, image, short_name, sell_quantitys);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productModel;
	}

	@Override
	public ArrayList<String> getSizesByProductId(int product_id) {
		// TODO Auto-generated method stub
		ArrayList<String> listSizes = new ArrayList<String>();
		String sql = "select size_name from product_sizes where product_id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, product_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				listSizes.add(rs.getString("size_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listSizes;
	}

	@Override
	public ArrayList<String> getColorsByProductId(int product_id) {
		// TODO Auto-generated method stub
		ArrayList<String> listColors = new ArrayList<String>();
		String sql = "select colors_name from products_colors where product_id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, product_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				listColors.add(rs.getString("colors_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listColors;
	}

	@Override
	public ArrayList<String> getFileUrlsByProductId(int product_id) {
		// TODO Auto-generated method stub
		ArrayList<String> listFileUrls = new ArrayList<String>();
		String sql = "select file_url from image_details where product_id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, product_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				listFileUrls.add(rs.getString("file_url"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listFileUrls;
	}

	@Override
	public List<SizeColorProductModel> getSizeColorByProductId(int product_id) {
		// TODO Auto-generated method stub
		List<SizeColorProductModel> listSizeColorProducts = new ArrayList<SizeColorProductModel>();
		String sql = "select size_color.*, product_sizes.size_name, product_sizes.description, products_colors.colors_name, products_colors.color_english\r\n"
				+ "from product_sizes \r\n"
				+ "join size_color on product_sizes.id = size_color.size_id\r\n"
				+ "join products_colors on products_colors.id = size_color.color_id\r\n"
				+ "where product_sizes.product_id = ? and products_colors.product_id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, product_id);
			pstmt.setInt(2, product_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id_product = rs.getInt("id");
				int quantity = rs.getInt("quantity");
				String sizeName = rs.getString("size_name");
				String descriptionSize = rs.getString("description");
				String colorName = rs.getString("colors_name");
				String colorEnglish = rs.getString("color_english");
				sizeColorProductModel = new SizeColorProductModel(id_product, quantity, sizeName, descriptionSize, colorName, colorEnglish);
				
				listSizeColorProducts.add(sizeColorProductModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listSizeColorProducts;
	}

}
