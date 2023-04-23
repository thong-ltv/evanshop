package com.evanshop.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.evanshop.model.CardModel;
import com.evanshop.service.ICardService;
import com.evanshop.service.ICustomerService;
import com.evanshop.service.impl.CardService;
import com.evanshop.service.impl.CustomerService;

/**
 * Servlet implementation class Card
 */
@WebServlet(urlPatterns = {"/card", "/card/insert", "/card/delete", "/card/update"})
public class Card extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ICardService cardService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Card() {
        super();
        // TODO Auto-generated constructor stub
        this.cardService = new CardService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		if(request.getRequestURI().equals("/evanshop/card/insert")) {
//			int id_customer = Integer.parseInt(request.getParameter("id_customer"));
//			int id_product = Integer.parseInt(request.getParameter("id_product"));
//			
//			cardService.insert(id_customer, id_product);
//			
//			RequestDispatcher rd = request.getRequestDispatcher("/views/web/card.jsp");
//			rd.forward(request, response);
//			
//		}else {
//			
//			RequestDispatcher rd = request.getRequestDispatcher("/views/web/card.jsp");
//			rd.forward(request, response);
//		}
		//hien thi cart
		if(request.getRequestURI().equals("/evanshop/card")) {
			HttpSession session = request.getSession();
			// kiem tra nguoi dung da dang nhap chua
			//truong hop chua
			if(session.getAttribute("username") == null) {
				response.sendRedirect(request.getContextPath().concat("/login"));
			}else {
				//truong hop dang nhap roi
				int customer_id = (int) session.getAttribute("idCustomer");
				List<CardModel> listCartModel = new ArrayList<CardModel>();
				listCartModel = cardService.getAllCart(customer_id);
				request.setAttribute("listCartModel", listCartModel);
				
				RequestDispatcher rd = request.getRequestDispatcher("/views/web/card.jsp");
				rd.forward(request, response);
			}
			
		}else if(request.getRequestURI().equals("/evanshop/card/delete")) {
			int cart_id = Integer.parseInt(request.getParameter("cart_id"));
			
			cardService.deleteCartById(cart_id);
			
			response.sendRedirect(request.getContextPath().concat("/card"));
			
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("addCart") != null) {
			try {
//				String price = request.getParameter("priceNew");
				double price = Double.parseDouble(request.getParameter("priceNew"));
				String color = request.getParameter("colorRadios");
				String size = request.getParameter("sizeRadios");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				int idCustomer = Integer.parseInt(request.getParameter("id_customer"));
				int idProduct = Integer.parseInt(request.getParameter("id_product"));
				
				cardService.insertToCart(idProduct, idCustomer, color, size, price, quantity);
				
				response.sendRedirect(request.getContextPath().concat("/trang-chu"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		if(request.getRequestURI().equals("/evanshop/card/update")) {
			int customer_id = Integer.parseInt(request.getParameter("idCustomer"));
			if(request.getParameter("btnUpdateCart") != null) {
				String[] prices =  request.getParameterValues("priceNew");
				String[] colors =  request.getParameterValues("colorProduct");
				String[] sizes =  request.getParameterValues("sizeProduct");
				String[] quantitys =  request.getParameterValues("quantityChange");
				String[] idProducts = request.getParameterValues("idProduct");
				
				cardService.deleteCartByCustomerId(customer_id);
				
				int lengthCart = 0;
				if(idProducts.length > 0) {
					lengthCart = idProducts.length;
					
					for (int i = 0; i < lengthCart; i++) {
						cardService.insertToCart(Integer.parseInt(idProducts[i]), customer_id, colors[i], sizes[i], Double.parseDouble(prices[i]), Integer.parseInt(quantitys[i]));
					}
				}
				
			}
		}
	}

}
