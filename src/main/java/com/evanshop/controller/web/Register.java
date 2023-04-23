package com.evanshop.controller.web;

import com.evanshop.model.CustomerModel;
import com.evanshop.service.ICustomerService;
import com.evanshop.service.impl.CustomerService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evanshop.connect.Connect;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connect conn;

	private ICustomerService customerService;
	private CustomerModel customerModel;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        this.customerModel = new CustomerModel();
        this.customerService = new CustomerService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("decorators/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//set UTF-8
		request.setCharacterEncoding("UTF-8");
		String status = "fail";
		
		//nhan du lieu khi nguoi dung dang ki
		if(request.getParameter("submit") != null) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String level = "";
			if(username != null && password != null) {
				customerModel = new CustomerModel(1, name, email, address, phone, username, password, level);
				int st = customerService.insert(customerModel);
				status = (st > 0) ? "success" : "fail";
			}
			if(status.equals("success")) {
				request.setAttribute("statusInsert", status);
				RequestDispatcher rd = request.getRequestDispatcher("decorators/register.jsp");
				rd.forward(request, response);
//				response.sendRedirect(request.getContextPath().concat("/login?statusInsert=" + status));
			}else {
				request.setAttribute("model", customerModel);
				request.setAttribute("statusInsert", status);
//				response.sendRedirect(request.getContextPath().concat("/register?statusInsert=" + status + "&model=" + customerModel));
				RequestDispatcher rd = request.getRequestDispatcher("decorators/register.jsp");
				rd.forward(request, response);
			}
			
		}
		
	}

}
