<%
String orderNumber = (String) session.getAttribute("orderNumber");
Double totalAmount = (Double) request.getAttribute("totalAmount");
String name = (String) session.getAttribute("customerName");
String phone = (String) session.getAttribute("customerPhone");

if(orderNumber == null){ orderNumber="N/A"; }
if(name == null){ name="Customer"; }
if(phone == null){ phone="Not Available"; }
if(totalAmount == null){ totalAmount=0.0; }
%>

<!DOCTYPE html>
<html>
<head>

<title>Invoice</title>

<style>

body{
font-family:sans-serif;
background:#f5f5f5;
}

.invoice{
width:500px;
margin:auto;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0 5px 20px rgba(0,0,0,0.2);
}

button{
background:linear-gradient(135deg,#25D366,#128C7E);
color:white;
border:none;
padding:10px 20px;
border-radius:20px;
cursor:pointer;
}

</style>

</head>

<body>

<div class="invoice">

<h2>FoodyHub Invoice</h2>

<p><b>Order ID:</b> <%= orderNumber %></p>

<p><b>Customer:</b> <%= name %></p>

<p><b>Phone:</b> <%= phone %></p>

<p><b>Total Paid:</b> ₹ <%= totalAmount %></p>

<hr>

<h3>Thank you for ordering!</h3>

<button onclick="sendBill()">Send Bill to WhatsApp</button>

</div>

<script>

function sendBill(){

var orderId = "<%= orderNumber %>";
var amount = "<%= totalAmount %>";
var customer = "<%= name %>";
var phone = "<%= phone %>";

var msg =
"FoodyHub Order Bill" +
"%0AOrder ID: " + orderId +
"%0ACustomer: " + customer +
"%0APhone: " + phone +
"%0ATotal Paid: ₹" + amount +
"%0AThank you for ordering!";

window.open(
"https://wa.me/?text=" + msg,
"_blank"
);

}

</script>

</body>
</html>