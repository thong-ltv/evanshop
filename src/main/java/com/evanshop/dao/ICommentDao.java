package com.evanshop.dao;

import java.util.List;

import com.evanshop.model.CommentModel;

public interface ICommentDao {
	List<CommentModel> getCommentByProductId(int product_id);
}
