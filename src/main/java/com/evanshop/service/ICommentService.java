package com.evanshop.service;

import java.util.List;

import com.evanshop.model.CommentModel;

public interface ICommentService {
	List<CommentModel> getCommentByProductId(int product_id);
}
