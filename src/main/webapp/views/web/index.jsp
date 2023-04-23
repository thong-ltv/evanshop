<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.evanshop.model.ProductModel" %>
 <!-- Products section -->
  <div class="container mt-5">
    <h2>Featured Products</h2>
    <div class="row">
    
    <% List<ProductModel> products = (List<ProductModel>) request.getAttribute("products"); %>
    <% if(products == null){%>
    	<h2>Không tồn tại sản phẩm</h2>
    <%}else { %>
    <% for (int i = 0; i < products.size(); i+=2) { %>
   <div class="col-md-3">
        <div class="card">
          <img src='<c:url value="<%= products.get(i).getImage() %>"/>' class="card-img-top img-fluid d-block align-items-center" alt="...">
          <div class="card-body">
            <h5 class="card-title"><%= products.get(i).getShort_name() %></h5>
            <p class="card-text"><%= products.get(i).getPrice() %></p>
            <% if(session.getAttribute("username") != null) { %>
            	<a href="${request.getContextPath()}/evanshop/product/detail?id_product=<%= products.get(i).getId() %>&id_customer=<%= session.getAttribute("idCustomer") %>" class="btn btn-primary">Chi tiết sản phẩm</a>
            <% } else { %>
            	<a href="${request.getContextPath()}/evanshop/product/detail?id_product=<%= products.get(i).getId() %>&id_customer=<%= -1 %>" class="btn btn-primary">Chi tiết sản phẩm</a>
            <% } %>
          </div>
        </div>
        <% if(i + 1 < products.size()){%>
    	<div class="card mt-3">
          <img src='<c:url value="<%= products.get(i+1).getImage() %>"/>' class="card-img-top img-fluid d-block align-items-center" alt="...">
          <div class="card-body">
            <h5 class="card-title"><%= products.get(i+1).getShort_name() %></h5>
            <p class="card-text"><%= products.get(i+1).getPrice() %></p>
            <% if(session.getAttribute("username") != null) { %>
            	<a href="${request.getContextPath()}/evanshop/product/detail?id_product=<%= products.get(i+1).getId() %>&id_customer=<%= session.getAttribute("idCustomer") %>" class="btn btn-primary">Chi tiết sản phẩm</a>
            <% } else { %>
            	<a href="${request.getContextPath()}/evanshop/product/detail?id_product=<%= products.get(i+1).getId() %>&id_customer=<%= -1 %>" class="btn btn-primary">Chi tiết sản phẩm</a>
            <% } %>
            
          </div>
        </div>
    <%}else { %>
    <%} %>
        
      </div>
<% } %>
    <%} %>
      <div class="col-md-3">
        <div class="card">
          <div class="card-header">
            Tin tức sản phẩm khuyến mãi mới nhất!!!
          </div>
          <ul class="list-group list-group-flush">
          <c:forEach var="couponModel" items="${coupons}">
          		<li class="list-group-item">
	              <div class="d-flex justify-content-between">
	                <h5 class="mb-1">${couponModel.getProduct_name()}</h5>
	                <span class="badge bg-danger">New</span>
	              </div>
	              <p class="mb-1">${couponModel.getContent().concat(" từ ").concat(couponModel.getStart_date().getDate())
	              .concat(" đến ").concat(couponModel.getExpiration_date().getDate()).concat("/")
	              .concat(couponModel.getExpiration_date().getMonth()).concat("/")
	              .concat(couponModel.getExpiration_date().getYear()+1900)}</p>
	            </li> 
          </c:forEach>
          </ul>
        </div>
     
      
    </div>
  <nav aria-label="Page navigation example" class="m-3">
  <ul class="pagination">
  	<% float productsAll = Float.parseFloat(request.getAttribute("productsAll").toString()); %>
  	<% int page_index = (int) request.getAttribute("page"); %>
   <% int number_page = (int) Math.ceil(productsAll/6); %>
	<% if(number_page > 3) { %>
		<li class="page-item"><a class="page-link" href="${request.getContextPath()}${requestURI}?page=<%= page_index - 1 %>">Trước</a></li>
	<% } %>
    <% for(int j = 1; j <= number_page; j++) { %>
    	<% if(j == page_index) { %>
    		<li class="page-item active"><a class="page-link" href="${request.getContextPath()}${requestURI}?page=<%= j %>"><%= j %></a></li>
    	<% }else { %>
    	<li class="page-item"><a class="page-link" href="${request.getContextPath()}${requestURI}?page=<%= j %>"><%= j %></a></li>
    	<% } %>
    <%} %>
    <% if(number_page > 3) { %>
		<li class="page-item"><a class="page-link" href="${request.getContextPath()}${requestURI}?page=<%= page_index + 1 %>">Tiếp</a></li>
	<% } %>
    
  </ul>
</nav>

  </div>