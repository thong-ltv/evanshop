package com.evanshop.model;

public class ProductModel {
	private int id;
	private String name;
	private String description;
	private double price;
	private int quantity;
	private int category_id;
	private int supplier_id;
	private String image;
	private String short_name;
	private int sell_quantitys;
	
	public ProductModel() {
		
	}
	
	public ProductModel(int id, String name, String description, double price, int quantity, int category_id,
			int supplier_id, String image, String short_name, int sell_quantitys) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.category_id = category_id;
		this.supplier_id = supplier_id;
		this.image = image;
		this.short_name = short_name;
		this.sell_quantitys = sell_quantitys;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getShort_name() {
		return short_name;
	}
	
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public int getSell_quantitys() {
		return sell_quantitys;
	}

	public void setSell_quantitys(int sell_quantitys) {
		this.sell_quantitys = sell_quantitys;
	}
}
