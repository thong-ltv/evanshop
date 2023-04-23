package com.evanshop.service.impl;

import java.util.List;

import com.evanshop.dao.ICouponDao;
import com.evanshop.dao.impl.CouponDao;
import com.evanshop.model.CouponModel;
import com.evanshop.service.ICouponService;

public class CouponService implements ICouponService {

	private ICouponDao couponDao;
	
	public CouponService() {
		this.couponDao = new CouponDao();
	}
	
	@Override
	public List<CouponModel> get4Coupons() {
		// TODO Auto-generated method stub
		return couponDao.get4Coupons();
	}

	@Override
	public List<CouponModel> getCouponByProductId(int product_id) {
		// TODO Auto-generated method stub
		return couponDao.getCouponByProductId(product_id);
	}

}
