package com.evanshop.model;

public class SizeColorProductModel {
	private int id;
	private int quantity;
	private String sizeName;
	private String descriptionSize;
	private String colorName;
	private String colorEnglish;
	
	public SizeColorProductModel () {
		
	}

	public SizeColorProductModel(int id, int quantity, String sizeName, String descriptionSize, String colorName, String colorEnglish) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.sizeName = sizeName;
		this.descriptionSize = descriptionSize;
		this.colorName = colorName;
		this.colorEnglish = colorEnglish;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getDescriptionSize() {
		return descriptionSize;
	}

	public void setDescriptionSize(String descriptionSize) {
		this.descriptionSize = descriptionSize;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorEnglish() {
		return colorEnglish;
	}

	public void setColorEnglish(String colorEnglish) {
		this.colorEnglish = colorEnglish;
	}
	
}
