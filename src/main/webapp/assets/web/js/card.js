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
      alert(quantity);
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
    alert(quantity);
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
