package com.evanshop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.evanshop.connect.Connect;
import com.evanshop.dao.ICommentDao;
import com.evanshop.model.CommentModel;
import com.evanshop.model.ProductModel;

public class CommentDao implements ICommentDao {

	private Connect conn;
	
	private CommentModel commentModel;
	
	public CommentDao() {
		this.conn = new Connect();
		this.commentModel = new CommentModel();
	}
	@Override
	public List<CommentModel> getCommentByProductId(int product_id) {
		// TODO Auto-generated method stub
		List<CommentModel> listCommentModel = new ArrayList<CommentModel>();
		String sql = "select customers.username, comments.*\r\n"
				+ "from customers join comments on customers.id = comments.customer_id\r\n"
				+ "where comments.product_id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, product_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String content = rs.getString("content");
				int score = rs.getInt("score");
				int customer_id = rs.getInt("customer_id");
				Date created_at = rs.getDate("created_at");
				String nameCustomer = rs.getString("username");
				commentModel = new CommentModel(id, content, score, customer_id, product_id, created_at, null, nameCustomer);
				
				listCommentModel.add(commentModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCommentModel;
	}

}
