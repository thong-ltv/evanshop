package com.evanshop.service.impl;

import java.util.List;

import com.evanshop.dao.ICardDao;
import com.evanshop.dao.impl.CardDao;
import com.evanshop.model.CardModel;
import com.evanshop.service.ICardService;

public class CardService implements ICardService {
	
	private ICardDao cardDao;
	
	public CardService () {
		this.cardDao = new CardDao();
	}

	@Override
	public void insert(int id_customer, int id_product) {
		// TODO Auto-generated method stub
		cardDao.insert(id_customer, id_product);
	}

	@Override
	public List<CardModel> getListCardModel() {
		// TODO Auto-generated method stub
		return cardDao.getListCardModel();
	}

	@Override
	public void insertToCart(int idProduct, int idCustomer, String color, String size, double price, int quantity) {
		// TODO Auto-generated method stub
		cardDao.insertToCart(idProduct, idCustomer, color, size, price, quantity);
	}

	@Override
	public List<CardModel> getAllCart(int customer_id) {
		// TODO Auto-generated method stub
		return cardDao.getAllCart(customer_id);
	}

	@Override
	public void deleteCartById(int cart_id) {
		// TODO Auto-generated method stub
		cardDao.deleteCartById(cart_id);
	}

	@Override
	public void deleteCartByCustomerId(int customer_id) {
		// TODO Auto-generated method stub
		cardDao.deleteCartByCustomerId(customer_id);
	}

}
