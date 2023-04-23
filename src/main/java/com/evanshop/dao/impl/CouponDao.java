package com.evanshop.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.evanshop.connect.Connect;
import com.evanshop.dao.ICouponDao;
import com.evanshop.model.CommentModel;
import com.evanshop.model.CouponModel;
import com.mysql.cj.protocol.Resultset;

public class CouponDao implements ICouponDao {

	private Connect conn;
	
	private CouponModel couponModel;
	
	public CouponDao() {
		this.conn = new Connect();
		this.couponModel = new CouponModel();
	}
	
	@Override
	public List<CouponModel> get4Coupons() {
		// TODO Auto-generated method stub
		// lay 4 records sau cung trong bang coupons
		List<CouponModel> listCoupons = new ArrayList<CouponModel>();
		String sql = "select cp.id, cp.content, cp.value, cp.expiration_date, cp.start_date, concat(left(pd.name, 20), \"...\") as product_name\r\n"
				+ "from coupons as cp\r\n"
				+ "join coupon_product as cpd on cp.id = cpd.coupon_id\r\n"
				+ "join products as pd on cpd.product_id = pd.id\r\n"
				+ "order by cp.id desc\r\n"
				+ "limit 4";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			Date start_date = null;
			Date expiration_date = null;
			
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String content = rs.getString("content");
				int value = rs.getInt("value");
				Date expiration_d = rs.getDate("expiration_date");
				Date start_d = rs.getDate("start_date");
				String product_name = rs.getString("product_name");
				//chuyen yyyy-mm-dd sang dd-mm-yyyy
//				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // định dạng đầu vào
//				SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy"); // định dạng đầu ra
				
				try {
					String String_start_date = String.format("%1$td-%1$tm-%1$tY", start_d); // chuyển đổi đối tượng Date thành định dạng dd-mm-yyyy
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date date = sdf.parse(String_start_date);
					start_date = new java.sql.Date(date.getTime());
					String String_expiration_date = String.format("%1$td-%1$tm-%1$tY", expiration_d); // chuyển đổi đối tượng Date thành định dạng dd-mm-yyyy
					SimpleDateFormat sdf_expiration = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date date_exp = sdf.parse(String_expiration_date);
					expiration_date = new java.sql.Date(date_exp.getTime());
				} catch (Exception e) {
					// TODO: handle exception
				}
				couponModel = new CouponModel(id, content, value, expiration_date, start_date, product_name);
				
				listCoupons.add(couponModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCoupons;
	}

	@Override
	public List<CouponModel> getCouponByProductId(int product_id) {
		// TODO Auto-generated method stub
		List<CouponModel> listCouponModel = new ArrayList<CouponModel>();
		String sql = "select coupons.*\r\n"
				+ "from coupons join coupon_product on coupons.id = coupon_product.coupon_id\r\n"
				+ "where coupon_product.product_id = ?";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setInt(1, product_id);
			
			ResultSet rs = pstmt.executeQuery();
			Date start_date = null;
			Date expiration_date = null;
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String content = rs.getString("content");
				int value = rs.getInt("value");
				Date expiration_d = rs.getDate("expiration_date");
				Date start_d = rs.getDate("start_date");
//				String product_name = rs.getString("product_name");
				//chuyen yyyy-mm-dd sang dd-mm-yyyy
//				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // định dạng đầu vào
//				SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy"); // định dạng đầu ra
				
				try {
					String String_start_date = String.format("%1$td-%1$tm-%1$tY", start_d); // chuyển đổi đối tượng Date thành định dạng dd-mm-yyyy
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date date = sdf.parse(String_start_date);
					start_date = new java.sql.Date(date.getTime());
					String String_expiration_date = String.format("%1$td-%1$tm-%1$tY", expiration_d); // chuyển đổi đối tượng Date thành định dạng dd-mm-yyyy
					SimpleDateFormat sdf_expiration = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date date_exp = sdf.parse(String_expiration_date);
					expiration_date = new java.sql.Date(date_exp.getTime());
				} catch (Exception e) {
					// TODO: handle exception
				}
				couponModel = new CouponModel(id, content, value, expiration_date, start_date, "");
				
				listCouponModel.add(couponModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listCouponModel;
	}

}
