package com.evanshop.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import org.mindrot.jbcrypt.BCrypt;

import com.evanshop.connect.Connect;
import com.evanshop.model.TokenModel;
import com.evanshop.service.ICustomerService;
import com.evanshop.service.impl.CustomerService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connect conn;
	private ICustomerService customerService;
	private TokenModel tokenModel;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
        this.customerService = new CustomerService();
        this.tokenModel = new TokenModel();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tokenValue = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		    	 if (cookie.getName().equals("token")) {
		             tokenValue = cookie.getValue();
		             break;
		         }
		    }
		}
		
		tokenModel = customerService.checkToken(tokenValue);
		
		request.setAttribute("tokenModel", tokenModel);

		RequestDispatcher rd = request.getRequestDispatcher("/decorators/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("submit") != null) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String rememberMe = request.getParameter("rememberMe");
			boolean checkReme = false;
			if(rememberMe != null) {
				checkReme = true;
			}
			
			//check password
			boolean checkAcount = customerService.checkAcount(email, password);
			String username = customerService.getUsername(email, password);
			int idCustomer = customerService.getId(email, password);
			if(checkAcount) {
//				RequestDispatcher rd = request.getRequestDispatcher("/trang-chu");
//				rd.include(request, response);
				if(checkReme) {
					//tao token lưu vào cookie
					 String token = TokenGenerator.generateToken(email, System.currentTimeMillis());
			         Cookie cookie = new Cookie("token", token);
			         cookie.setMaxAge(60 * 60); // Cookie expires after 1 week
			         cookie.setPath(request.getContextPath().concat("/login"));
			         response.addCookie(cookie);
			         
			         //them token vao database
			         int insertToken = customerService.insertToken(email, password, token);
				}
				   
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				if(idCustomer != 0) {
					session.setAttribute("idCustomer", idCustomer);
				}
				String loginFromCart = "false";
				String checkLoginFromCart = request.getParameter("loginFromCart");
				if(checkLoginFromCart == null) {
					checkLoginFromCart = "false";
				}
				if(!checkLoginFromCart.equals(loginFromCart)) {
					int product_id = Integer.parseInt(request.getParameter("product_id"));
					response.sendRedirect(request.getContextPath().concat("/product/detail?id_product=" + product_id + "&id_customer=" + idCustomer));
				}else {
					response.sendRedirect(request.getContextPath().concat("/trang-chu"));
				}
				
			}else {
//				doGet(request, response);
				response.sendRedirect(request.getContextPath().concat("/login?statusLogin=" + "fail"));
			}
		}
		
		if(request.getParameter("addCart") != null) {
			request.setAttribute("loginFromCart", "true");
			request.setAttribute("product_id", request.getParameter("id_product"));
			RequestDispatcher rd = request.getRequestDispatcher("/decorators/login.jsp");
			rd.forward(request, response);
		}
	}
	

}
