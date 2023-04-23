
package com.evanshop.model;

import java.util.Date;

public class CommentModel {
	private int id;
	private String content;
	private int score;
	private int customer_id;
	private int product_id;
	private Date created_at;
	private Date update_at;
	private String nameCustomer;
	
	public CommentModel() {
		
	}

	public CommentModel(int id, String content, int score, int customer_id, int product_id, Date created_at,
			Date update_at, String nameCustomer) {
		super();
		this.id = id;
		this.content = content;
		this.score = score;
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.created_at = created_at;
		this.update_at = update_at;
		this.nameCustomer = nameCustomer;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}
	
}
