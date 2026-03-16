<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pro1.dto.Cart, com.pro1.dto.CartItem" %>
<%
if(session.getAttribute("user")==null){
response.sendRedirect("login.jsp");
return;
}
%>
<%
Cart cart = (Cart) session.getAttribute("cart");

double subtotal = 0.0;
double tax = 0.0;
double delivery = 5.0;
double total = 0.0;

if(cart == null || cart.getItems()==null || cart.getItems().isEmpty()){
    response.sendRedirect("cart.jsp");
    return;
}

for(CartItem item : cart.getItems().values()){
    subtotal += item.getPrice() * item.getQuantity();
}

tax = subtotal * 0.10;
total = subtotal + tax + delivery;
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Checkout</title>

<link rel="stylesheet" href="css/style.css">

<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>

</head>

<body class="cart-page-bg">

<nav class="navbar">
<a href="restaurant.jsp" class="logo">FoodyHub</a>
</nav>


<div class="app-container">

<h2>Payment Method</h2>

<form action="checkout" method="post">

<div class="payment-grid-glass">

<!-- CARD -->
<label class="payment-option-glass">
<input type="radio" name="paymentMethod" value="card" checked>

<div class="payment-card-box">
<ion-icon name="card-outline"></ion-icon>
<span>Card</span>
</div>

</label>


<!-- UPI -->
<label class="payment-option-glass">

<input type="radio" name="paymentMethod" value="upi" onclick="generateUPIQR()">

<div class="payment-card-box">
<ion-icon name="qr-code-outline"></ion-icon>
<span>UPI</span>
</div>

</label>


<!-- COD -->
<label class="payment-option-glass">

<input type="radio" name="paymentMethod" value="cod">

<div class="payment-card-box">
<ion-icon name="cash-outline"></ion-icon>
<span>Cash</span>
</div>

</label>

</div>


<!-- UPI QR -->
<div id="upiQR" style="display:none;text-align:center;margin-top:20px;">

<img id="qrImage" style="width:200px;height:200px;border-radius:10px;">

<p>Scan this QR with GPay / PhonePe / Paytm</p>

</div>
<div id="upiApps" style="display:none;text-align:center;margin-top:20px;">

<h3>Pay using UPI Apps</h3>

<div style="display:flex;justify-content:center;gap:20px;margin-top:10px;">

<button onclick="payUPI('gpay')" style="border:none;background:white;padding:10px;border-radius:10px;cursor:pointer;">
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Google_Pay_Logo.svg/512px-Google_Pay_Logo.svg.png" width="60">
</button>

<button onclick="payUPI('phonepe')" style="border:none;background:white;padding:10px;border-radius:10px;cursor:pointer;">
<img src="https://download.logo.wine/logo/PhonePe/PhonePe-Logo.wine.png" width="60">
</button>

<button onclick="payUPI('paytm')" style="border:none;background:white;padding:10px;border-radius:10px;cursor:pointer;">
<img src="https://download.logo.wine/logo/Paytm/Paytm-Logo.wine.png" width="60">
</button>

</div>

</div>


<!-- CARD DETAILS -->
<div class="glass-input-group">

<input type="text" class="glass-input" placeholder="xxxx xxxx xxxx xxxx">

<label class="glass-label">Card Number</label>

</div>


<div style="display:grid;grid-template-columns:1fr 1fr;gap:1rem;">

<div class="glass-input-group">

<input type="text" class="glass-input" placeholder="MM/YY">

<label class="glass-label">Expiry</label>

</div>

<div class="glass-input-group">

<input type="text" class="glass-input" placeholder="123">

<label class="glass-label">CVV</label>

</div>

</div>



<h3>Total : ₹<%= String.format("%.2f", total) %></h3>

<button type="submit" class="btn-checkout-orange">

Place Order

</button>

</form>

</div>

<script>

function generateUPIQR(){

const upiId = "yourupi@upi";
const name = "FoodyHub";
const amount = "<%= total %>";

const upiLink =
`upi://pay?pa=${upiId}&pn=${name}&am=${amount}&cu=INR`;

const qrURL =
"https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + encodeURIComponent(upiLink);

document.getElementById("qrImage").src = qrURL;

document.getElementById("upiQR").style.display="block";

document.getElementById("upiApps").style.display="block";

}

</script>

</body>
</html>