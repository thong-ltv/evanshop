package com.evanshop.dao;

import java.util.List;

import com.evanshop.model.CouponModel;

public interface ICouponDao {
	List<CouponModel> get4Coupons();

	List<CouponModel> getCouponByProductId(int product_id);
}
