package com.evanshop.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.evanshop.connect.Connect;
import com.evanshop.dao.ICardDao;
import com.evanshop.model.CardModel;

public class CardDao implements ICardDao {
	private Connect conn;
	private CardModel cardModel;
	
	public CardDao() {
		this.conn = new Connect();
		this.cardModel = new CardModel();
	}

	@Override
	public void insert(int id_customer, int id_product) {
		// TODO Auto-generated method stub
		String sql = "{call add_to_cart(?, ?)}";
		
		try {
			CallableStatement cstmt = conn.getConn().prepareCall(sql);
			cstmt.setInt(1, id_product);
			cstmt.setInt(2, id_customer);
			
			cstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<CardModel> getListCardModel() {
		List<CardModel> listCardModel = null;
		// TODO Auto-generated method stub
		String sql ="select *\r\n"
				+ "from cart as ct \r\n"
				+ "join customers as cs on ct.customer_id = cs.id\r\n"
				+ "join cart_quantity as cq on cq.cart_id = ct.id\r\n"
				+ "join products as pd on pd.id = cq.product_id;";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// tao ra 2 model: ProductModel va CustomerModel
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCardModel;
	}

	@Override
	public void insertToCart(int idProduct, int idCustomer, String color, String size, double price, int quantity) {
		// TODO Auto-generated method stub
		String sql = "{call add_to_cart(?, ?, ?, ?, ?, ?)}";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, idProduct);
			pstmt.setInt(2, idCustomer);
			pstmt.setString(3, color);
			pstmt.setString(4, size);
			pstmt.setDouble(5, price);
			pstmt.setInt(6, quantity);
			
			ResultSet rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<CardModel> getAllCart(int customer_id) {
		// TODO Auto-generated method stub
		List<CardModel> listCardModel = new ArrayList<CardModel>();
		String sql = "select cart_quantity.*, products.short_name, products.image, products.price from cart_quantity \r\n"
				+ "join products on cart_quantity.product_id = products.id\r\n"
				+ "join cart on cart_quantity.cart_id = cart.id where cart.customer_id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, customer_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String image = rs.getString("image");
				String name = rs.getString("short_name");
				double priceOld = rs.getDouble("price");
				double priceNew = rs.getDouble("price_new");
				int quantity = rs.getInt("quantity");
				String color = rs.getString("color");
				String size = rs.getString("size");
				int product_id = rs.getInt("product_id");
				cardModel = new CardModel(id, image, name, priceOld, priceNew, quantity, color, size, product_id);
				
				listCardModel.add(cardModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listCardModel;
	}

	@Override
	public void deleteCartById(int cart_id) {
		// TODO Auto-generated method stub
		String sql = "delete from cart_quantity where id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, cart_id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCartByCustomerId(int customer_id) {
		// TODO Auto-generated method stub
		String sql = "{call deleteCartByCustomerId(?)}";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, customer_id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
