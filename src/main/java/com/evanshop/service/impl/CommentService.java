package com.evanshop.service.impl;

import java.util.List;

import com.evanshop.dao.ICommentDao;
import com.evanshop.dao.impl.CommentDao;
import com.evanshop.model.CommentModel;
import com.evanshop.service.ICommentService;

public class CommentService implements ICommentService {
	private ICommentDao commentDao;
	
	public CommentService() {
		this.commentDao = new CommentDao();
	}

	@Override
	public List<CommentModel> getCommentByProductId(int product_id) {
		// TODO Auto-generated method stub
		return commentDao.getCommentByProductId(product_id);
	}

}
