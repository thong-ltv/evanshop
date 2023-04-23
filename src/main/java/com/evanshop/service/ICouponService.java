package com.evanshop.service;

import java.util.List;

import com.evanshop.model.CouponModel;

public interface ICouponService {
	List<CouponModel> get4Coupons();

	List<CouponModel> getCouponByProductId(int product_id);
}
