package com.evanshop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.evanshop.connect.Connect;
import com.evanshop.dao.ICustomerDao;
import com.evanshop.model.CustomerModel;
import com.evanshop.model.TokenModel;

public class CustomerDao implements ICustomerDao {
	
	private Connect conn;
	
	private CustomerModel customerModel;
	private TokenModel tokenModel;
	
	private static final String ALGORITHM = "AES";
    private static final String KEY = "thisisakey123456";
	
	public CustomerDao() {
		this.conn = new Connect();
		this.customerModel = new CustomerModel();
		this.tokenModel = new TokenModel();
	}

	@Override
	public int insert(CustomerModel customerModel) {
		int rs = 0;
		// TODO Auto-generated method stub
		if(customerModel != null) {
			String sql = "insert into customers(name, email, address, phone, username, password, level)"
					+ "values (?, ?, ?, ?, ?, ?, ?)";
			
			try {
				PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
				pstmt.setString(1, customerModel.getName());
				pstmt.setString(2, customerModel.getEmail());
				pstmt.setString(3, customerModel.getAddress());
				pstmt.setString(4, customerModel.getPhone());
				pstmt.setString(5, customerModel.getUsername());
				pstmt.setString(6, hashPassword(customerModel.getPassword()));
				pstmt.setString(7, customerModel.getLevel());
				
				rs = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	// Hàm mã hóa mật khẩu bằng thuật toán bcrypt
	public String hashPassword(String password) {
	    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	    return hashedPassword;
	}
	// Hàm kiểm tra mật khẩu đã mã hóa có khớp với mật khẩu ban đầu không
	public boolean checkPassword(String password, String hashedPassword) {
		boolean matched = BCrypt.checkpw(password, hashedPassword);
		return matched;
	}
	
	//check tai khoan dang nhap
	public boolean checkAcount(String email, String password) {
		String sql ="select * from customers";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("email").equals(email) && checkPassword(password, rs.getString("password"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//lay username
	@Override
	public String getUsername(String email, String password) {
		String sql ="select * from customers";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("email").equals(email) && checkPassword(password, rs.getString("password"))) {
					return rs.getString("username");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//lay id_customer
		@Override
		public int getId(String email, String password) {
			String sql ="select * from customers";
			
			try {
				PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getString("email").equals(email) && checkPassword(password, rs.getString("password"))) {
						return rs.getInt("id");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
		
	//ma hoa password
	public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
	//lay lai password
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
	
	//check email co ton tai trong danh sach khong
	public void checkEmail(String email) {
		String sql ="select * from tokens";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("email").equals(email)) {
					
					String sql_delete = "delete from tokens where id = ?";
					PreparedStatement pstmt_delete = conn.getConn().prepareStatement(sql_delete);
					pstmt_delete.setInt(1, rs.getInt("id"));
					
					pstmt_delete.executeUpdate();
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//ham insert tokens
	public int insertToken(String email, String password, String token) {
		//kiem tra email
		this.checkEmail(email);
		
		int insertToken = 0;
		
		String sql = "insert into tokens (email, password, token) values (?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			pstmt.setString(1, email);
			try {
				password = this.encrypt(password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt.setString(2, password);
			pstmt.setString(3, token);
			
			insertToken = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return insertToken;
	}
	
	//check token
	public TokenModel checkToken(String token) {
		
		String sql = "select * from tokens";
		
		try {
			PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("token").equals(token)) {
					tokenModel.setEmail(rs.getString("email"));
					try {
						tokenModel.setPassword(this.decrypt(rs.getString("password")));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return tokenModel;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
