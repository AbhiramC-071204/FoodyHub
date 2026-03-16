<!DOCTYPE html>
<html>
<head>
<title>Processing Payment</title>

<style>

body{
display:flex;
justify-content:center;
align-items:center;
height:100vh;
background:#111;
color:white;
font-family:sans-serif;
}

.status-box{
text-align:center;
}

.status{
font-size:22px;
margin-top:20px;
}

.loader{
border:6px solid #333;
border-top:6px solid #ff7e5f;
border-radius:50%;
width:60px;
height:60px;
animation:spin 1s linear infinite;
margin:auto;
}

@keyframes spin{
0%{transform:rotate(0deg);}
100%{transform:rotate(360deg);}
}

</style>

</head>

<body>

<div class="status-box">

<div class="loader"></div>

<div id="statusText" class="status">
Processing Payment...
</div>

</div>


<script>

setTimeout(function(){
document.getElementById("statusText").innerHTML="Payment Successful";
},2000);

setTimeout(function(){
document.getElementById("statusText").innerHTML="Order Placed";
},3500);

setTimeout(function(){
window.location="orderSuccess.jsp";
},5000);

</script>

</body>
</html>