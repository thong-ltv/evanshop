package com.evanshop.service;

import java.util.List;

import com.evanshop.model.CardModel;

public interface ICardService {
	void insert(int id_customer, int id_product);
	List<CardModel> getListCardModel();
	void insertToCart(int idProduct, int idCustomer, String color, String size, double price, int quantity);
	List<CardModel> getAllCart(int customer_id);
	void deleteCartById(int cart_id);
	void deleteCartByCustomerId(int customer_id);
}
