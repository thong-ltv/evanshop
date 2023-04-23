<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<body>
    <div class="super_container">
        <div class="single_product">
            <div class="container" style=" background-color: #fff; padding: 11px;">
                <div class="row">
                    <div class="col-lg-2 order-lg-1 order-2">
                        <ul class="image_list">
                            <li data-image="https://res.cloudinary.com/dxfq3iotg/image/upload/v1565713229/single_4.jpg"><img src="<c:url value="${productModel.getImage()}"/>" alt=""></li>
                            <c:forEach var = "imageDetail" items = "${listFileUrls }">
                            	<li data-image="https://res.cloudinary.com/dxfq3iotg/image/upload/v1565713228/single_2.jpg"><img src="<c:url value="${imageDetail}"/>" alt=""></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="col-lg-4 order-lg-2 order-1">
                        <div class="image_selected"><img src="<c:url value="${productModel.getImage()}"/>" alt=""></div>
                    </div>
                    <div class="col-lg-6 order-3">
                        <div class="product_description">
                        <form action="
                        <% if(session.getAttribute("username") != null) { %>
			            	${request.getContextPath()}/evanshop/card/insert" method="post"
			            <% } else { %>
			            	${request.getContextPath()}/evanshop/login" method="post">
			            <% } %>
                            <nav>
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                                    <li class="breadcrumb-item"><a href="#">${category}</a></li>
                                    <li class="breadcrumb-item active">Chi tiết sản phẩm</li>
                                </ol>
                            </nav>
                            <div class="product_name">${productModel.getName()}</div>
                            <div class="product-rating"><span class="">${stars} Stars &</span> <span class="rating-review">${productModel.getSell_quantitys()} Đã bán & ${numberComments} Đánh giá</span></div>
                            <div> <span class="product_price">$ ${priceDiscount}</span> <strike class="product_discount"> <span style='color:black'>$ ${productModel.getPrice()}<span> </strike> </div>
                            <input type="hidden" name="priceNew" value="${priceDiscount}">
                            <input type="hidden" name="id_customer" value="${customer_id}">
                            <input type="hidden" name="id_product" value="${productModel.getId()}">
                            <!-- <div> <span class="product_saved">You Saved:</span> <span style='color:black'>₹ 2,000<span> </div> -->
                            <c:forEach var="coupon" items="${listCouponModel}">
                            	<p>${coupon.getContent()}</p>
                            </c:forEach>
                            <hr class="singleline">
                            
                            <div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <h5>Màu sắc:</h5>
                                    </div>
                                    <div class="col-md-9">
                                    	<c:forEach var="color" items="${colors}">
			                            	 <div class="form-check">
                                            <input class="form-check-input" type="radio" name="colorRadios" id="exampleRadio1" value="${color }">
                                            <label class="form-check-label" for="exampleRadio1">
                                              ${color}
                                            </label>
                                          </div> 
                                          
                                          <div class="row my-3 ms-4">
			                                    <div class="col-md-3">
			                                        <h6>Kích thước: </h6>
			                                    </div>
			                                    <div class="col-md-9">
			                                    	<c:forEach var="size" items="${listSizeColorProducts}">
			                                    		<c:if test="${color == size.getColorName() && size.getQuantity() > 0 }">
			                                              		
			                                              <div class="form-check">
						                            	
				                                            <input class="form-check-input" type="radio" name="sizeRadios" id="exampleRadio1" value="${size.getSizeName() }">
				                                            <label class="form-check-label" for="exampleRadio1">
																${size.getSizeName() }
				                                            </label>
				                                           </div> 
			                                             </c:if>	
						                            </c:forEach> 
			                                    </div>
			                                </div>
			                                <hr class="singleline col-md-5">
			                            </c:forEach> 
			                            
                                    </div>
                                </div>
                            </div>
                            <hr class="singleline">
                            <div class="order_info d-flex flex-row">
                            </div>
                            <div class="row">
                                <div class="col-xs-6" style="margin-left: 13px;">
                                    <!-- <div class="product_quantity "> <span class="text-dark fw-bold">QTY: </span> <input class="text-dark fw-bold" id="quantity_input" name="quantity_product" type="text" pattern="[0-9]*" value="1">
                                        <div class="quantity_buttons">
                                            <div id="quantity_inc_button" class="quantity_inc quantity_control">+</div>
                                            <div id="quantity_dec_button" class="quantity_dec quantity_control">-</div>
                                        </div>
                                    </div> -->
                                    <label>QTY: </label>
                                    <input type="number" name="quantity" id="" value="1" min="1" max="10" class="my-4 text-center ms-3">
                                </div>
                                <div class="col-xs-6"> <button type="submit" class="btn btn-primary shop-button" name="addCart">Thêm vào giỏ hàng</button> <button type="submit" class="btn btn-success shop-button" name="buyCart">Mua ngay</button>
                                    <!-- <div class="product_fav"><i class="fas fa-heart"></i></div> -->
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
				
                <div class="row row-underline">
                    <div class="col-md-6"> <span class=" deal-text">Mô tả sản phẩm</span> </div>
                    <div class="col-md-6"> <a href="#" data-abc="true"> <span class="ml-auto view-all"></span> </a> </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="col-md-12">
                            <tbody>
                                <tr class="row mt-10">
                                    <td class="col-md-4"><span class="p_specification">Tên sản phẩm :</span> </td>
                                    <td class="col-md-8">
                                        <ul>
                                            <li>${productModel.getName()}</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr class="row mt-10">
                                    <td class="col-md-4"><span class="p_specification">Mô tả sản phẩm:</span> </td>
                                    <td class="col-md-8">
                                        <ul>
                                            <li>${productModel.getDescription()}</li>
                                        </ul>
                                    </td>
                                </tr>
                                 <tr class="row mt-10">
                                    <td class="col-md-4"><span class="p_specification">Danh mục:</span> </td>
                                    <td class="col-md-8">
                                        <ul>
                                            <li>${category}</li>
                                        </ul>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="container mt-4">
                    <h2>Đánh giá sản phẩm</h2>
                    <hr>
                    <div class="row">
                      <div class="col-md-8">
                      	<c:forEach var="comment" items="${twoNewComment}">
                      		<div class="card mb-3">
	                          <div class="card-body">
	                            <h5 class="card-title">${comment.getNameCustomer()}</h5>
	                            <h6 class="card-subtitle mb-2 text-muted">${comment.getScore()}/5 stars</h6>
	                            <p class="card-text">${comment.getContent()}</p>
	                            <small class="text-muted">Posted on ${comment.getCreated_at()}</small>
	                          </div>
	                        </div>
                      	</c:forEach>
                      	<c:if test="${twoNewComment.size()} > 2">
                      		<a href="#" >Xem thêm</a>
                      	</c:if>
                      </div>
                      <div class="col-md-4">
                        <h5>Viết đánh giá</h5>
                        <hr>
                        <form action="#">
                          <div class="mb-3">
                            <label for="name" class="form-label">Tên</label>
                            <input type="text" class="form-control" id="name" required>
                          </div>
                          <div class="mb-3">
                            <label for="rating" class="form-label">Đánh giá</label>
                            <select class="form-select" id="rating" required>
                              <option value="" selected disabled hidden>Chọn đánh giá bạn của bạn nha!!!</option>
                              <option value="5">5 stars</option>
                              <option value="4">4 stars</option>
                              <option value="3">3 stars</option>
                              <option value="2">2 stars</option>
                              <option value="1">1 star</option>
                            </select>
                          </div>
                          <div class="mb-3">
                            <label for="comment" class="form-label">Nội dung</label>
                            <textarea class="form-control" id="comment" rows
                            ="5" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Gửi</button>
                      </form>
                    </div>
                                      

                <div class="row row-underline">
                    <div class="col-md-6"> <span class=" deal-text">Các sản phẩm liên quan</span> </div>
                    <div class="col-md-6"> <a href="#" data-abc="true"> <span class="ml-auto view-all"></span> </a> </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row padding-2">
                        	<c:forEach var="productItem" items="${fourProducts }">
                        		<div class="col-md-3 padding-0 border border-dark">
	                                <div class="bbb_combo">
	                                    <div class="bbb_combo_image"><img class="bbb_combo_image" src='<c:url value="${productItem.getImage() }"/>' alt=""></div>
	                                   <!--  <div class="d-flex flex-row justify-content-start"> <strike style="color:red;"> <span class="fs-10" style="color:black;">₹ 32,000<span> </span></span></strike> <span class="ml-auto"><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i></span> </div> -->
	                                    <div class="d-flex flex-row justify-content-start ms-4" style=" margin-bottom: 13px; "> <span style="margin-top: -4px;">$ ${productItem.getPrice()}</span> </div> <span>${productItem.getShort_name() }</span>
	                                    <a href="#">Chi tiết</a>
	                                </div>
	                            </div>
                        	</c:forEach>
                        	<c:if test="${listProducts.size() > 4 }">
                        	      <a href="#">Xem thêm</a>
                        	</c:if>
                      
                         	 <!-- <div class="col-md-2 text-center"> <span class="vertical-line"></span> </div> -->
                        
                            <!-- <div class="col-md-5 padding-0">
                                <div class="bbb_combo">
                                    <div class="bbb_combo_image"><img class="bbb_combo_image" src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560924153/alcatel-smartphones-einsteiger-mittelklasse-neu-3m.jpg" alt=""></div>
                                    <div class="d-flex flex-row justify-content-start"> <strike style="color:red;"> <span class="fs-10" style="color:black;">₹ 32,000<span> </span></span></strike> <span class="ml-auto"><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i></span> </div>
                                    <div class="d-flex flex-row justify-content-start" style=" margin-bottom: 13px; "> <span style="margin-top: -4px;">₹30,000</span> <span class="ml-auto fs-10">23 Reviews</span> </div> <span>Acer laptop with 10GB RAM + 500 GB Hard Disk</span>
                                    <a href="#">Chi tiết</a>
                                </div>
                            </div> -->
                        </div>
                    </div>
                   <!--  <div class="col-md-2 text-center"> <span class="vertical-line"></span> </div> -->
                    <!-- <div class="col-md-5" style=" margin-left: -27px;">
                        <div class="row padding-2">
                            <div class="col-md-5 padding-0">
                                <div class="bbb_combo">
                                    <div class="bbb_combo_image"><img class="bbb_combo_image" src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560924153/alcatel-smartphones-einsteiger-mittelklasse-neu-3m.jpg" alt=""></div>
                                    <div class="d-flex flex-row justify-content-start"> <strike style="color:red;"> <span class="fs-10" style="color:black;">₹ 32,000<span> </span></span></strike> <span class="ml-auto"><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating p-rating"></i><i class="fa fa-star p-rating"></i></span> </div>
                                    <div class="d-flex flex-row justify-content-start" style=" margin-bottom: 13px; "> <span style="margin-top: -4px;">₹30,000</span> <span class="ml-auto fs-10">23 Reviews</span> </div> <span>Acer laptop with 10GB RAM + 500 GB Hard Disk</span>
                                    <a href="#">Chi tiết</a>
                                </div>
                            </div>
                            <div class="col-md-2 text-center"> <span class="vertical-line"></span> </div>
                            <div class="col-md-5 padding-0">
                                <div class="bbb_combo">
                                    <div class="bbb_combo_image"><img class="bbb_combo_image" src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560924153/alcatel-smartphones-einsteiger-mittelklasse-neu-3m.jpg" alt=""></div>
                                    <div class="d-flex flex-row justify-content-start"> <strike style="color:red;"> <span class="fs-10" style="color:black;">₹ 32,000<span> </span></span></strike> <span class="ml-auto"><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i></span> </div>
                                    <div class="d-flex flex-row justify-content-start" style=" margin-bottom: 13px; "> <span style="margin-top: -4px;">₹30,000</span> <span class="ml-auto fs-10">23 Reviews</span> </div> <span>Acer laptop with 10GB RAM + 500 GB Hard Disk</span>
                                    <a href="#">Chi tiết</a>
                                </div>
                            </div>
                        </div>
                    </div> -->
                </div>
               
            </div>
        </div>
    		</div>
		</div>
	</div>
</body>
</html>