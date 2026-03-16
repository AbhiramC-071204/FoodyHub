<%@ page import="com.pro1.dto.Order" %>
<%
String orderNumber = (String) session.getAttribute("orderNumber");

Double totalAmount = (Double) request.getAttribute("totalAmount");

String name = (String) session.getAttribute("customerName");

String phone = (String) session.getAttribute("customerPhone");

if(name == null){
    name = "Customer";
}

if(phone == null){
    phone = "Not Available";
}
%>

<%
Order order = (Order) request.getAttribute("order");
%>
<%
String paymentMode = (order != null) ? order.getPayment_Mode() : "";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodyHub - Order Confirmed</title>
    <link rel="stylesheet" href="css/style.css">
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</head>

<body class="cart-page-bg">

    <div class="confirmation-container">

        <div class="confirmation-wrapper">

            <!-- 1. Success Animation -->
            <div class="success-anim">
                <ion-icon name="checkmark-circle" class="success-icon-large"></ion-icon>
            </div>

            <!-- 2. Neon Text -->
            <h1 class="order-title-neon">Order Placed!</h1>
            <p class="order-subtitle">Your delicious food is being prepared.</p>

          

            <!-- 3. Order Details Glass Card -->
            <div class="order-details-glass">
               <div class="detail-row-shine">

<span style="opacity:0.7;">Payment</span>

<span style="color:#00ff88; font-weight:600;">

<%
if("cod".equalsIgnoreCase(paymentMode)){
%>

Cash on Delivery

<ion-icon name="cash-outline"
style="vertical-align:middle; color:#00ff88;"></ion-icon>

<%
}else{
%>

Paid Successfully

<ion-icon name="checkmark-circle-outline"
style="vertical-align:middle; color:#00ff88;"></ion-icon>

<%
}
%>

</span>

</div>
            </div>

<button onclick="sendBill()" 
style="
background: linear-gradient(135deg,#25D366,#128C7E);
color:white;
border:none;
padding:12px 25px;
border-radius:30px;
font-size:16px;
cursor:pointer;
margin-top:15px;
box-shadow:0 4px 15px rgba(0,0,0,0.4);
">

Send Invoice to WhatsApp

</button>

<br><br>
            <!-- 5. Action Buttons -->
            <button class="btn-track">Track Order</button>

            <div style="margin-top:10px;">
                <a href="restaurant" class="btn-home-ghost">Back to Home</a>
            </div>

        </div>

    </div>
<jsp:include page="/common-scripts.jsp" />

<script>

function sendWhatsApp(){

var orderId = "<%= orderNumber %>";
var amount = "<%= totalAmount %>";
var phone = "<%= phone %>";

var message =
"Order ID: " + orderId +
"%0ACustomer: <%= name %>" +
"%0AAmount Paid: ₹" + amount +
"%0AThanks for ordering from FoodyHub";

window.open(
"https://wa.me/" + phone + "?text=" + message,
"_blank"
);

}

</script>
<button onclick="sendBill()">Send Invoice to WhatsApp</button>
<script>

function sendBill(){

var orderId = "<%= session.getAttribute("orderNumber") %>";
var amount = "<%= request.getAttribute("totalAmount") %>";

var msg = "Order ID: " + orderId +
"%0AAmount Paid: ₹" + amount +
"%0AThank you for ordering from FoodyHub";

window.open("https://wa.me/?text=" + msg, "_blank");

}

</script>
</body>


</html>