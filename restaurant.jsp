<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.pro1.dto.User"%>
<%@ page import="com.pro1.dto.Cart"%>
<%@ page import="com.pro1.dto.CartItem"%>
<%@ page import="com.pro1.dto.Restaurant"%>
<%
if(session.getAttribute("user")==null){
response.sendRedirect("login.jsp");
return;
}
%>
<%
User user = (User) session.getAttribute("user");

Cart cartObj = (Cart) session.getAttribute("cart");

int cartCount = 0;

if(cartObj != null && cartObj.getItems() != null){
    for(CartItem item : cartObj.getItems().values()){
        cartCount += item.getQuantity();
    }
}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FoodyHub | Food Delivery</title>

<link rel="stylesheet" href="css/style.css">

<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

</head>

<body>

<div class="app-container">
<nav class="navbar">

<!-- Logo -->
<a href="#" class="logo">
<div class="logo-icon">
<ion-icon name="fast-food"></ion-icon>
</div>
FoodyHub
</a>

<!-- Center Navigation -->
<ul class="nav-links">

<li><a href="#" class="active">Restaurants</a></li>

<% if(user == null){ %>

<li><a href="login.jsp">Login</a></li>
<li><a href="index.html">Sign Up</a></li>

<% } else { %>

<li><a href="cart.jsp">Orders</a></li>

<% } %>

</ul>

<!-- Right Icons -->
<div class="nav-actions">

<!-- Search -->
<button onclick="toggleSearch()" class="icon-btn">
<ion-icon name="search-outline"></ion-icon>
</button>

<!-- Cart -->
<a href="cart.jsp">
<button class="icon-btn cart-btn">
<ion-icon name="cart-outline"></ion-icon>
<span class="cart-badge"><%= cartCount %></span>
</button>
</a>

<% if(user != null){ %>

<!-- Profile -->
<div style="width:40px;height:40px;border-radius:50%;
background:linear-gradient(135deg,#7f5af0,#2cb67d);
display:flex;align-items:center;justify-content:center;
color:white;font-weight:bold;">

<%= user.getUser_Name().substring(0,1).toUpperCase() %>

</div>

<!-- Logout -->
<form action="logout" method="get">

<button style="
background:linear-gradient(135deg,#ff7e5f,#ff3c3c);
border:none;
padding:8px 14px;
border-radius:20px;
color:white;
cursor:pointer;">

Logout

</button>

</form>

<% } %>

</div>

</nav>
				<!-- Hero Section -->
				<header class="hero-banner">
					<!-- Slider Container -->
					<div class="hero-slider" id="heroSlider">
						<div class="hero-slide"></div>
						<div class="hero-slide"></div>
						<div class="hero-slide"></div>
					</div>

					<div class="hero-overlay"></div>

					<div class="hero-content">
						<h1>EXQUISITE FLAVORS<br>DELIVERED.</h1>
						<p>Order from top chefs & Michelin-starred restaurants.</p>
						<button class="btn-hero">Explore Collection</button>
					</div>

					<div class="hero-dots">
						<span class="dot active"></span>
						<span class="dot"></span>
						<span class="dot"></span>
					</div>
				</header>

				<script>
					// Simple Slideshow Script
					const slider = document.getElementById('heroSlider');
					const dots = document.querySelectorAll('.dot');
					let currentSlide = 0;
					const totalSlides = 3;

					function nextSlide() {
						currentSlide = (currentSlide + 1) % totalSlides;
						updateSlider();
					}

					function updateSlider() {
						// Move the slider
						slider.style.transform = "translateX(-" + (currentSlide * 100) + "%)";


						// Update dots
						dots.forEach(dot => dot.classList.remove('active'));
						dots[currentSlide].classList.add('active');
					}

					// Auto slide every 3 seconds
					setInterval(nextSlide, 3000);
				</script>

				<!-- Main Content -->

				<!-- Restaurant Grid -->
				<div class="restaurant-grid" id="restaurant-container">

					<%
List<Restaurant> allRestaurants =
        (List<Restaurant>) request.getAttribute("allRestaurants");

if (allRestaurants != null && !allRestaurants.isEmpty()) {
    for (Restaurant restaurant : allRestaurants) {
%>

<a href="menu?restaurantId=<%=restaurant.getRestaurant_Id()%>">
    <div class="restaurant-card">
        <div class="card-content-wrapper">
            <div class="card-image-container">
                <img src="<%=restaurant.getImage_Path()%>"
                     alt="<%=restaurant.getName()%>"
                     class="card-image">
            </div>

            <div class="card-info">
                <div class="card-header">
                    <h3 class="restaurant-name">
                        <%=restaurant.getName()%>
                    </h3>
                    <div class="rating-badge">
                        <ion-icon name="star"></ion-icon>
                        <%=restaurant.getRating()%>
                    </div>
                </div>

                <p class="cuisine-type">
                    <%=restaurant.getCuisine_Type()%>
                </p>

                <div class="meta-info">
                    <span class="time-badge">
                        <ion-icon name="time-outline"></ion-icon>
                        <%=restaurant.getDelivery_Time()%>
                    </span>
                    <span class="address">
                        <%=restaurant.getAddress()%>
                    </span>
                </div>
            </div>
        </div>
    </div>
</a>

<%
    } 
} else { 
%>

<p style="color:red; grid-column: 1/-1; text-align: center;">
    No restaurants available.
</p>

<%
}
%>

				</div>

			</div>
			<jsp:include page="/common-scripts.jsp" />
			<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
		</body>

		</html>