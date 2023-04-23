package com.evanshop.service.impl;

import java.util.ArrayList;

import com.evanshop.dao.ICustomerDao;
import com.evanshop.dao.impl.CustomerDao;
import com.evanshop.model.CustomerModel;
import com.evanshop.model.TokenModel;
import com.evanshop.service.ICustomerService;

public class CustomerService implements ICustomerService {
	
	private ICustomerDao customerDao;
	
	public CustomerService() {
		this.customerDao = new CustomerDao();
	}

	@Override
	public int insert(CustomerModel customerModel) {
		return customerDao.insert(customerModel);
	}

	@Override
	public boolean checkAcount(String email, String password) {
		// TODO Auto-generated method stub
		return customerDao.checkAcount(email, password);
	}

	@Override
	public String getUsername(String email, String password) {
		// TODO Auto-generated method stub
		return customerDao.getUsername(email, password);
	}

	@Override
	public int insertToken(String email, String password, String token) {
		// TODO Auto-generated method stub
		return customerDao.insertToken(email, password, token);
	}

	@Override
	public TokenModel checkToken(String tokenValue) {
		// TODO Auto-generated method stub
		return customerDao.checkToken(tokenValue);
	}

	@Override
	public int getId(String email, String password) {
		// TODO Auto-generated method stub
		return customerDao.getId(email, password);
	}

}
