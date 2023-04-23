package com.evanshop.model;

public class CardModel {
	private int id;
	private String image;
	private String name;
	private double priceOld;
	private double priceNew;
	private int quantity;
	private String color;
	private String size;
	private int product_id;
	
	public CardModel() {
		
	}

	public CardModel(int id, String image, String name, double priceOld, double priceNew, int quantity, String color,
			String size, int product_id) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.priceOld = priceOld;
		this.priceNew = priceNew;
		this.quantity = quantity;
		this.color = color;
		this.size = size;
		this.product_id = product_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPriceOld() {
		return priceOld;
	}

	public void setPriceOld(double priceOld) {
		this.priceOld = priceOld;
	}

	public double getPriceNew() {
		return priceNew;
	}

	public void setPriceNew(double priceNew) {
		this.priceNew = priceNew;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
}
