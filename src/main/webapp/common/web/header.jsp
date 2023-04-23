<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%-- <%@ page import="javax.servlet.http.HttpSession" %> --%>
  <!-- Navigation bar -->
  <nav class=" container navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <a class="navbar-brand fs-3" href="${request.getContextPath()}/evanshop/trang-chu">Evan Shop</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse " id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${request.getContextPath()}/evanshop/trang-chu">Trang chủ</a>
          </li>
          <%-- <li class="nav-item">
            <a class="nav-link" href="${request.getContextPath()}/evanshop/trang-chu">Shop</a>
          </li> --%>
          <%-- <li class="nav-item">
            <a class="nav-link" href="${request.getContextPath()}/evanshop/trang-chu">Liên hệ</a>
          </li> --%>
          <li class="nav-item ms-2">
              <div class="cart-container">
                <a href="#">
                  <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="rgba(255,255,255,.55)" class="bi bi-cart" viewBox="0 0 16 16">
                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                  </svg>
                </a>
                <div class="cart-info position-absolute z-index-1">
                <c:choose>
				    <c:when test="${username != null}">
				        <c:if test="${quantityCart == 0}">
                		<p>Your cart is currently empty.</p>
	                	</c:if>
	                	<c:if test="${quantityCart > 0}">
	                		<p>Your cart is full</p>
	                	</c:if>
				    </c:when>
				    <c:otherwise>
				    	<a href="${request.getContextPath()}/evanshop/login" class="fs-5">Please login your account!!!</a>
				    </c:otherwise>
				</c:choose>
                	
                </div>
              </div>
              
            <!-- </div>             -->
          </li>
          <c:choose>
				<c:when test="${username != null}">
				   <li class="nav-item ms-4">
			           <h5 class="text-light">${username}</h5>
			        </li>
			        <!-- <li class="ms-4 text-white">
			        	<a href="#"><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="#fff" class="bi bi-person" viewBox="0 0 16 16">
  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
</svg></a>
			        </li> -->
			        <li>
			        <div class="dropdown ms-4">
						  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
						    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="#fff" class="bi bi-person" viewBox="0 0 16 16">
  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
</svg>
						  </button>
						  <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
						    <li><a class="dropdown-item" href="${request.getContextPath()}/evanshop/logout">Đăng xuất</a></li>
						    <li><hr class="dropdown-divider"></li>
						  </ul>
						</div>
			        </li>
				 </c:when>
				<c:otherwise>
				    <li class="nav-item ms-4">
			            <a href="${request.getContextPath()}/evanshop/register" class="nav-link d-inline-block float-start fs-6">
			              Đăng kí
			            </a>
			            <a href="${request.getContextPath()}/evanshop/login" class="nav-link d-inline-block float-start fs-6">
			              | Đăng nhập
			            </a>
			          </li>
				</c:otherwise>
			</c:choose>
        </ul>
      </div>
    </div>
  </nav>

  <!-- search input -->
  <div class="container input-group p-4">
    <input type="text" class="form-control form-control-sm" placeholder="Tìm kiếm">
    <button class="btn btn-outline-secondary" type="button">
      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
      </svg>
    </button>
  </div>

  <!-- list products -->
  <div class="container">
    <div class="row">
      <div class="col">
        <ul class="nav nav-pills justify-content-center" role="tablist">
        	<c:forEach var="listCategory" items="${listCategories}">
    			<li class="nav-item" role="presentation">
            		<a class="nav-link text-dark" href="${request.getContextPath()}/evanshop/${listCategory.getCode()}?page=1&id=${listCategory.getId()}" >${listCategory.getName()}</a>
          		</li>
			</c:forEach>
          
          <!-- <li class="nav-item" role="presentation">
            <a class="nav-link text-dark" href="#profile" role="tab" data-bs-toggle="pill">Profile</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="nav-link text-dark" href="#messages" role="tab" data-bs-toggle="pill">Messages</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="nav-link text-dark" href="#settings" role="tab" data-bs-toggle="pill">Settings</a>
          </li> -->
        </ul>
      </div>
    </div>
  </div>
