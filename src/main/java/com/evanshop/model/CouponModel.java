package com.evanshop.model;

import java.sql.Date;

public class CouponModel {
	private int id;
	private String content;
	private int value;
	private Date expiration_date;
	private Date start_date;
	private String product_name;
	
	public CouponModel() {
		
	}
	
	public CouponModel(int id, String content, int value, Date expiration_date, Date start_date, String product_name) {
		super();
		this.id = id;
		this.content = content;
		this.value = value;
		this.expiration_date = expiration_date;
		this.start_date = start_date;
		this.product_name = product_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
}
