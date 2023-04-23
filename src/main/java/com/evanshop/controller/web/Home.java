package com.evanshop.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.evanshop.connect.Connect;
import com.evanshop.service.ICategoryService;
import com.evanshop.service.ICouponService;
import com.evanshop.service.IProductService;
import com.evanshop.service.impl.CategoryService;
import com.evanshop.service.impl.CouponService;
import com.evanshop.service.impl.ProductService;
import com.evanshop.model.CategoryModel;
import com.evanshop.model.CouponModel;
import com.evanshop.model.ProductModel;

/**
 * Servlet implementation class Home
 */
@WebServlet(urlPatterns = {"/ao-khoac", "/trang-chu",  "/ao-so-mi-nam", "/ao-phong", "/quan-jean", "/quan-tay"})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connect connect;
	
	private ICategoryService categoryService;
	private IProductService productService;
	private ICouponService couponService;
	
	private HttpSession session;
	
    public Home() {
        super();
        // TODO Auto-generated constructor stub
        this.connect = new Connect();
        this.categoryService = new CategoryService();
        this.productService = new ProductService();
        this.couponService = new CouponService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//tao session gio hang, xem so luong trong gio hang nhu the nao
		int quantityCart = 0;
		String username = null;
		session = request.getSession();
		if(session.getAttribute("username") != null) {
			username = (String) session.getAttribute("username");
		}
		
		if(session.getAttribute("quantityCart") == null) {
			session.setAttribute("quantityCart", 0);
			quantityCart = (int) session.getAttribute("quantityCart");
		}else {
			quantityCart = (int) session.getAttribute("quantityCart");
		}
		request.setAttribute("quantityCart", quantityCart);
		request.setAttribute("username", username);
		//end session
		
		// ket noi database
		connect.conn();
		//end ket noi database
		
		//tao list product
		List<ProductModel> products = null;
		
		//lay danh muc va truyen ra views
		List<CategoryModel> categories = categoryService.getCategories();
		if(!categories.isEmpty()) {
			System.out.println("thanh cong!!!");
		}
		List<String> list = new ArrayList<String>();
		for (CategoryModel m : categories) {
			list.add(m.getName());
			System.out.println(m.getName());
		}
		request.setAttribute("listCategories", categories);
		//end lay danh muc
		
		//lay 4 coupons 
		List<CouponModel> coupons = couponService.get4Coupons();
		request.setAttribute("coupons", coupons);
		
		String requestURI = request.getRequestURI();
		request.setAttribute("requestURI", requestURI);
		
		//lay thong tin san pham
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("page", page);
		
		if(request.getRequestURI().equals("/evanshop/ao-khoac") || request.getRequestURI().equals("/evanshop/ao-so-mi-nam")
			|| request.getRequestURI().equals("/evanshop/ao-phong") || request.getRequestURI().equals("/evanshop/ao-phong")
			|| request.getRequestURI().equals("/evanshop/quan-jean") || request.getRequestURI().equals("/evanshop/quan-tay")) {
			
			// lay id_category
			int category_id = Integer.parseInt(request.getParameter("id"));
			
			products = productService.getProductByCategoryId(category_id);
		}else {
			products = productService.getProducts();
		}
		int productsAll = products.size();
		//tao 1 list products gom 6 san pham truyen sang view
		List<ProductModel> pageListProducts = new ArrayList<ProductModel>();
		int start_page = (page - 1) * 6;
		int end_page = start_page + 5;
		if(start_page >= products.size()) {
			pageListProducts = null;
		}else if(end_page >= products.size()) {
			end_page = products.size();
			for(int i = start_page; i < end_page; i++ ) {
				pageListProducts.add(products.get(i));
			}
		}else {
			for(int i = start_page; i <= end_page; i++ ) {
				pageListProducts.add(products.get(i));
			}
		}
		
		//end lay thong tin san pham
		request.setAttribute("products", pageListProducts);
		request.setAttribute("productsAll", productsAll);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/index.jsp");
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
