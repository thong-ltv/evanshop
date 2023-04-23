package com.evanshop.dao;

import java.util.ArrayList;

import com.evanshop.model.CustomerModel;
import com.evanshop.model.TokenModel;

public interface ICustomerDao {
	int insert(CustomerModel customerModel);
	boolean checkAcount(String email, String password);
	String getUsername(String email, String password);
	int insertToken(String email, String password, String token);
	TokenModel checkToken(String tokenValue);
	int getId(String email, String password);
}
