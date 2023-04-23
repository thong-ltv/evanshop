<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.evanshop.model.CardModel" %>

<body>
<form action="${request.getContentPath()}/evanshop/card/update" method="post">
	<input type="hidden" name="idCustomer" value="<%= session.getAttribute("idCustomer") %>" />
    <div class="container">
        <div class="row">
        
          <div class="col-md-10">
            <h2>Giỏ hàng của bạn</h2>
            <table class="table">
              <thead>
                <tr>
                  <th></th>
                  <th>Hình ảnh</th>
                  <th class="col-md-1">Product</th>
                  <th>Size</th>
                  <th>Color</th>
                  <th>Price</th>
                  <th class="col-md-3">Quantity</th>
                  <th class="col-md-1">Subtotal</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="cardModel" items="${listCartModel}">
                	<tr class="product" data-price="${cardModel.getPriceNew()}" data-quantity="${cardModel.getQuantity()}">
                	  <td><input type="checkbox" value="" name="checkedCart"/></td>
	                  <td class="me-0" style="width: 20%;"><img src="<c:url value="${cardModel.getImage()}"/>" alt="Hình ảnh 1" class="me-4" style="width: 50%;"></td>
	                  <td>${cardModel.getName()}</td>
	                  <input type="hidden" value="${cardModel.getProduct_id()}" name="idProduct"></input>
	                  <td>${cardModel.getSize()}</td>
	                  <input type="hidden" value="${cardModel.getSize()}" name="sizeProduct"></input>
                  	  <td>${cardModel.getColor()}</td>
                  	  <input type="hidden" name="colorProduct" value="${cardModel.getColor()}" ></input>
	                  <td >$ ${cardModel.getPriceNew()}</td>
	                  <input type="hidden" name="priceNew" value="${cardModel.getPriceNew()}" ></input>
	                  <td>
	                    <div class="input-group" style="width: 50%;">
	                      <button class="btn btn-outline-secondary minus-btn decrease" type="button" >-</button>
	                      <span class="quantity p-3 pt-1">${cardModel.getQuantity()}</span>
	                      <input type="hidden" name="quantityChange" value="${cardModel.getQuantity()}">
	                      <button class="btn btn-outline-secondary plus-btn increase" type="button" >+</button>
	                    </div>
	                  </td>
	                  <td class="price">$ ${cardModel.getPriceNew() * cardModel.getQuantity()}</td>
	                  <td><a href="${request.getContextPath()}/evanshop/card/delete?cart_id=${cardModel.getId()}" class="text-danger deleteProduct">Xóa</a></td>
	                </tr>
                </c:forEach>
                <%-- <tr class="product" data-price="15.00" data-quantity="1">
                  <td class="me-0" style="width: 20%;"><img src="<c:url value="/assets/web/img/ao_1.jpg"/>" alt="Hình ảnh 1" class="me-4" style="width: 50%;"></td>
                  <td>Product 2</td>
                  <td>$15.00</td>
                  <td>
                    <div class="input-group" style="width: 50%;">
                      <button class="btn btn-outline-secondary minus-btn decrease" type="button" >-</button>
                      <span class="quantity p-3 pt-1">1</span>
                      <button class="btn btn-outline-secondary plus-btn increase" type="button" >+</button>
                    </div>
                  </td>
                  <td class="price">$15.00</td>
                  <td><a href="#" class="text-danger deleteProduct">Xóa</a></td>
                </tr> --%>
              </tbody>
            </table>
            <% List<CardModel> listCarts = (List<CardModel>) request.getAttribute("listCartModel"); %>
            <% if(listCarts.size() > 0) { %>
            	<button type="submit" name="btnUpdateCart">Cập nhật giỏ hàng</button>
            <% } %>
          </div>
       
          <div class="col-md-2">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title p-2">Tổng</h5>
                <h6 class="card-subtitle mb-2 text-muted p-2 fw-bold">Tổng số sản phẩm: <span class="cart-quantity fs-5" id="totalProducts"></span></h6>
                <h6 class="card-subtitle mb-2 text-muted p-2 fw-bold">Thành tiền: <span class="cart-total fs-5" id="totalPrice"></span></h6>
                <a href="#" class="btn btn-primary p-2">Tiến hành thanh toán</a>
              </div>
            </div>
          </div>
       
        </div>
     </div>
     <form>
 	<script type="text/javascript">
 	const products = document.querySelectorAll('.product');
    const quantityProducts = document.getElementById("totalProducts");
    const totalPrices = document.getElementById("totalPrice");

    function tinhTongGiaTien(){
      let totalPrice = 0;
      products.forEach((product) => {
        const priceElement = product.querySelector('.price');

        let price = Number(priceElement.textContent.replace("$", ''));

        totalPrice += price;
    });
      return totalPrice;
    }

    function tinhTongSanPham(){
      let totalProduct = 0;
      products.forEach((product) => {
        const quantityElement = product.querySelector('.quantity');

        let quantity = Number(quantityElement.textContent);

        totalProduct += quantity;
    });
      return totalProduct;
    }
    
    quantityProducts.innerText = tinhTongSanPham();
    totalPrices.innerText = '$' + tinhTongGiaTien().toFixed(2);

function load(){
  products.forEach((product) => {
  const decreaseButton = product.querySelector('.decrease');

  const increaseButton = product.querySelector('.increase');
  const quantityElement = product.querySelector('.quantity');
  const priceElement = product.querySelector('.price');
  
  const quantityChange = product.querySelector('input[name="quantityChange"]');

  let price = parseFloat(product.dataset.price);
  let quantity = parseInt(product.dataset.quantity);

  decreaseButton.addEventListener('click', () => {
    if (quantity > 1) {
      quantity--;
      quantityElement.textContent = quantity;
      quantityChange.value = quantity;
      priceElement.textContent = '$' + (quantity * price).toFixed(2);
      product.dataset.quantity = quantity;
      quantityProducts.innerText = tinhTongSanPham();
      totalPrices.innerText = '$' + tinhTongGiaTien().toFixed(2);
    }
  });

  increaseButton.addEventListener('click', () => {
    quantity++;
    quantityElement.textContent = quantity;
    quantityChange.value = quantity;
    priceElement.textContent = '$' + (quantity * price).toFixed(2);
    product.dataset.quantity = quantity;
    quantityProducts.innerText = tinhTongSanPham();
    totalPrices.innerText = '$' + tinhTongGiaTien().toFixed(2);
  });

  // xóa sản phẩm
  const deleteProduct = product.querySelector('.deleteProduct');

  deleteProduct.addEventListener('click', () => {
  // Xóa sản phẩm tương ứng khỏi giỏ hàng
  const confirmation = confirm('Bạn có chắc muốn xóa giỏ hàng?');

  if (confirmation) {
    // Xóa giỏ hàng
    let changeQuantity = tinhTongSanPham() - Number(quantityElement.textContent);
    let chanePrice = tinhTongGiaTien() - Number(priceElement.textContent.replace("$", ''));
    product.className = "old-class";
    product.remove();
    quantityProducts.innerText = changeQuantity;
    totalPrices.innerText = '$' + chanePrice.toFixed(2);
  }
  
});
}); 
}
    load();

 	</script>
</body>

