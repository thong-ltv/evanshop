package com.evanshop.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.evanshop.model.CommentModel;
import com.evanshop.model.CouponModel;
import com.evanshop.model.ProductModel;
import com.evanshop.model.SizeColorProductModel;
import com.evanshop.service.ICategoryService;
import com.evanshop.service.ICommentService;
import com.evanshop.service.ICouponService;
import com.evanshop.service.ICustomerService;
import com.evanshop.service.IProductService;
import com.evanshop.service.impl.CategoryService;
import com.evanshop.service.impl.CommentService;
import com.evanshop.service.impl.CouponService;
import com.evanshop.service.impl.CustomerService;
import com.evanshop.service.impl.ProductService;

/**
 * Servlet implementation class Product
 */
@WebServlet("/product/detail")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IProductService productService;
	private ProductModel productModel;
	private CommentModel commentModel;
	private ICommentService commentService;
	private ICouponService couponService;
	private CouponModel couponModel;
	private ICustomerService customerService;
	private ICategoryService categoryService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
        this.productService = new ProductService();
        this.productModel = new ProductModel();
        this.commentService = new CommentService();
        this.commentModel = new CommentModel();
        this.couponModel = new CouponModel();
        this.couponService = new CouponService();
        this.customerService = new CustomerService();
        this.categoryService = new CategoryService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int product_id = Integer.parseInt(request.getParameter("id_product"));
		
		try {
			int customer_id = 0;
			if(Integer.parseInt(request.getParameter("id_customer")) != -1) {
				customer_id = Integer.parseInt(request.getParameter("id_customer"));
			}
			request.setAttribute("customer_id", customer_id);
			// lay cac du lieu can thiet de dua len trang product_detail
				//lay ProductModel theo product_id
				productModel = productService.getProductByProductId(product_id);
				request.setAttribute("productModel", productModel);
//				System.out.println("ket qua: " + productModel.getName());
				
				//lay 4 san pham cung loai voi san pham tren
				List<ProductModel> fourProducts = new ArrayList<ProductModel>();
				List<ProductModel> listProducts = new ArrayList<ProductModel>();
				listProducts = productService.getProductByCategoryId(productModel.getCategory_id());
				if(listProducts.size() > 4) {
					fourProducts.add(listProducts.get(0));
					fourProducts.add(listProducts.get(1));
					fourProducts.add(listProducts.get(2));
					fourProducts.add(listProducts.get(3));
				}else {
					for (ProductModel productModel : listProducts) {
						fourProducts.add(productModel);
					}
				}
				request.setAttribute("fourProducts", fourProducts);
				request.setAttribute("listProducts", listProducts);
				//end lay 4 san pham
				
				//lay sizes theo product_id
				ArrayList<String> listSizes = new ArrayList<String>();
				listSizes = productService.getSizesByProductId(product_id);
//				System.out.println("ket qua: " + listSizes.get(1));
				
				//lay colors theo product_id
				ArrayList<String> listColors = new ArrayList<String>();
				listColors = productService.getColorsByProductId(product_id);
//				System.out.println("ket qua: " + listColors.get(0));
				
				//lay CommentModel theo product_id
				// lay name_customer theo product_id
				List<CommentModel> listCommentModel = new ArrayList<CommentModel>();
				listCommentModel = commentService.getCommentByProductId(product_id);
				
				//lấy 2 comment mới nhất
				List<CommentModel> twoNewComment = new ArrayList<CommentModel>();
				if(listCommentModel.size() > 1) {
					twoNewComment.add(listCommentModel.get(listCommentModel.size() - 1));
					twoNewComment.add(listCommentModel.get(listCommentModel.size() - 2));
				}else {
					twoNewComment.add(listCommentModel.get(0));
				} 
				request.setAttribute("twoNewComment", twoNewComment);
				//end lay 2 comment moi nhat
				
				//lay trung binh stars
				double stars = 5;
				double total_stars = 0;
				for (CommentModel commentModel : listCommentModel) {
					total_stars += commentModel.getScore();
				}
				stars = Math.round(total_stars * 10.0) / 10.0;
				request.setAttribute("stars", stars);
				
				//lay so luong danh gia
				int numberComments = listCommentModel.size();
				request.setAttribute("numberComments", numberComments);
//				System.out.println("ket qua: " + listCommentModel.get(0).getContent());
				
				//lay CouponModel theo product_id
				List<CouponModel> listCouponModel = new ArrayList<CouponModel>();
				listCouponModel = couponService.getCouponByProductId(product_id);
				double priceDiscount = productModel.getPrice();
				for (CouponModel couponModel : listCouponModel) {
					priceDiscount = priceDiscount * (100 - couponModel.getValue()) / 100;
				}
				request.setAttribute("priceDiscount", (int) priceDiscount);
				request.setAttribute("listCouponModel", listCouponModel);
//				System.out.println("ket qua: " + listCouponModel.get(0).getContent());
				
				//lay file_url theo product_id
				ArrayList<String> listFileUrls = new ArrayList<String>();
				listFileUrls = productService.getFileUrlsByProductId(product_id);
				request.setAttribute("listFileUrls", listFileUrls);
				//end 
				
				//lay ten danh muc theo san pham
				String category = categoryService.getCategoryByProductId(product_id);
				request.setAttribute("category", category);
				//end
				
				//lay size_color theo product
				List<SizeColorProductModel> listSizeColorProducts = new ArrayList<SizeColorProductModel>();
				listSizeColorProducts = productService.getSizeColorByProductId(product_id);
				request.setAttribute("listSizeColorProducts", listSizeColorProducts);
				
				//lay sizes
				ArrayList<String> sizes = new ArrayList<String>();
				for (SizeColorProductModel sizeColorProductModel : listSizeColorProducts) {
					String nameSize = sizeColorProductModel.getSizeName();
					if(!sizes.contains(nameSize)) {
						sizes.add(nameSize);
					}
				}
				request.setAttribute("sizes", sizes);
				//lay colors
				ArrayList<String> colors = new ArrayList<String>();
				ArrayList<String> colorsEnglish = new ArrayList<String>();
				for (SizeColorProductModel sizeColorProductModel : listSizeColorProducts) {
					String nameColor = sizeColorProductModel.getColorName();
					String colorEnglish = sizeColorProductModel.getColorEnglish();
					if(!colorsEnglish.contains(colorEnglish)) {
						colorsEnglish.add(colorEnglish);
						colors.add(nameColor);
					}
				}
				request.setAttribute("colors", colors);
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/detail_product.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
