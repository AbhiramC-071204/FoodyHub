<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List,com.pro1.dto.Menu,com.pro1.dto.Restaurant,com.pro1.dto.Cart,com.pro1.dto.CartItem"%>

        <% // Calculate Cart Count Logic directly in JSP 
        Cart cart=(Cart) session.getAttribute("cart"); 
        int cartCount=0;
            if (cart !=null && cart.getItems() !=null) { 
            	for (CartItem item : cart.getItems().values()) 
            	{ 
            		cartCount +=item.getQuantity(); 
            		} 
            	}
            %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>FoodyHub - Menu</title>
                <link rel="stylesheet" href="css/style.css">
                <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
                <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
            </head>

            <body style="background-color: #050505;">

                <!-- 1. Top Branding (Replaces Navbar) -->
                <div class="neon-top-bar"
                    style="position: relative; display: flex; justify-content: center; align-items: center;">

                    <!-- Left: Restaurants Link -->
                    <a href="restaurant"
                        style="position: absolute; left: 20px; text-decoration: none; color: white; display:flex; align-items:center; gap:5px; font-weight:500;">
                        <ion-icon name="arrow-back-outline"></ion-icon> Restaurants
                    </a>

                    <span class="neon-brand">
                        <% Restaurant restaurant=(Restaurant)request.getAttribute("restaurant"); %>
                        <% if(restaurant !=null){%>
                            <%= restaurant.getName()%>
                            <%} %>
                    </span>

                    <!-- Right: Cart Link with Count -->
                    <a href="cart.jsp" style="position: absolute; right: 20px; text-decoration: none;">
                        <button class="icon-btn cart-btn">
                            <ion-icon name="cart-outline"></ion-icon>
                            <% if(cartCount > 0) { %>
                                <span class="cart-badge"><%= cartCount %></span>
                                <% } %>
                        </button>
                    </a>
                </div>

                <!-- 2. Firebird Hero -->
                <div class="firebird-hero">
                    <% if (restaurant !=null) { %>
                        <img src="<%=restaurant.getImage_Path()%>" alt="<%=restaurant.getName()%>" class="firebird-img">
                        <% } %>
                            <div class="firebird-overlay-text">
                                <h2>
                                    <%=restaurant.getName()%>
                                </h2>
                                <!-- Optional: Display cuisine/rating if available in simple text -->
                                <% if(restaurant !=null) { %>
                                    <p style="color: #ccc; font-size: 1rem; margin-top:10px;">
                                        <%= restaurant.getCuisine_Type() %> • ⭐ <%= restaurant.getRating() %>
                                    </p>
                                    <% } %>
                            </div>
                </div>

                <div class="app-container">

                    <!-- 3. Neon Tabs -->
                    <div class="neon-tabs">
                        <button class="neon-tab-btn active">Signature Bowls</button>
                        <button class="neon-tab-btn">Small Plates</button>
                        <button class="neon-tab-btn" style="border-color: var(--neon-blue);">Drinks</button>
                        <button class="neon-tab-btn" style="border-color: var(--accent-green);">Desserts</button>
                    </div>

                    <!-- 4. Menu List -->
                    <div class="neon-menu-list">

                        <% List<Menu> allMenusByRestaurantId = (List<Menu>)request.getAttribute("allMenusByRestaurantId");

                                if(allMenusByRestaurantId != null && !allMenusByRestaurantId.isEmpty()) {
                                for(Menu menu : allMenusByRestaurantId) {
                                // Check if item is already in cart
                                boolean isAlreadyInCart = false;
                                if (cart != null && cart.getItems() != null &&
                                cart.getItems().containsKey(menu.getMenu_Id())) {
                                isAlreadyInCart = true;
                                }
                                %>

                                <!-- Dynamic Item with Neon Design -->
                                <div class="neon-item">
                                    <div class="neon-img-container">
                                        <img src="<%= menu.getImage_Path() %>" class="neon-img"
                                            alt="<%= menu.getItem_Name() %>"
                                            onerror="this.src='https://images.unsplash.com/photo-1546069901-ba9599a7e63c'">
                                    </div>
                                    <div class="neon-details">
                                        <div class="neon-title">
                                            <%= menu.getItem_Name() %>
                                        </div>
                                        <div class="neon-desc">
                                            <%= menu.getDescription() %>
                                        </div>
                                        <div class="neon-price">₹<%= menu.getPrice() %>
                                        </div>
                                    </div>

                                    <!-- USER LOGIC & FORM PRESERVED EXACTLY -->
                                    <form action="cart" style="margin:0;">
                                        <input type="hidden" name="itemId" value="<%=menu.getMenu_Id()%>">
                                        <input type="hidden" name="restaurantId" value="<%=menu.getRestaurant_Id()%>">
                                        <input type="hidden" name="quantity" value="1">
                                        <input type="hidden" name="action" value="add">
                                        <input type="hidden" name="imagePath" value="<%=menu.getImage_Path()%>">

                                        <button type="submit" class="btn-neon-add <%= isAlreadyInCart ? " added" : ""
                                            %>">
                                            <%= isAlreadyInCart ? "Added <ion-icon name='checkmark-circle'></ion-icon>"
                                                : "Add to Cart <ion-icon name='cart-outline'></ion-icon>" %>
                                        </button>
                                    </form>
                                </div>

                                <% } } else { %>
                                    <p style="color:red; text-align:center;">No menu items found for this restaurant.
                                    </p>
                                    <% } %>

                    </div>

                </div>

                <!-- Floating View Cart Bar -->
                <div class="floating-cart-bar" id="floatingCartBar">
                     <div class="cart-bar-content">
                        <div class="cart-bar-icon-wrapper">
                            <ion-icon name="cart"></ion-icon>
                            <span class="cart-bar-badge" id="cartBarBadge">0</span>
                        </div>
                        <div class="cart-bar-text">
                            <h4>Items Added!</h4>
                            <p><span id="cartItemCount">0</span> item(s) in your cart</p>
                        </div>
                    </div>
                    <a href="cart.jsp" class="btn-view-cart">
                        View Cart
                        <ion-icon name="arrow-forward"></ion-icon>
                    </a>
                </div>

                <script>
                    let cartCount = <%= cartCount %>;

                    function updateCartBar() {
                        const cartBar = document.getElementById('floatingCartBar');
                        const cartBarBadge = document.getElementById('cartBarBadge');
                        const cartItemCount = document.getElementById('cartItemCount');

                        if (cartCount > 0) {
                            cartBar.classList.add('show');
                            cartBarBadge.textContent = cartCount;
                            cartItemCount.textContent = cartCount;
                        } else {
                            cartBar.classList.remove('show');
                        }
                    }

                    document.querySelectorAll('form[action="cart"]').forEach(form => {
                        form.addEventListener('submit', function (e) {
                            const button = form.querySelector('.btn-neon-add');

                            if (!button.classList.contains('added')) {
                                button.classList.add('added');
                                button.innerHTML = 'Added <ion-icon name="checkmark-circle"></ion-icon>';
                                cartCount++;
                                updateCartBar();
                            }

                            e.preventDefault();
                            const params = new URLSearchParams(new FormData(form)).toString();
                            fetch('cart?' + params, { method: 'GET' })
                                .then(res => {
                                    if (!res.ok) throw new Error();
                                })
                                .catch(() => {
                                    form.submit();
                                });
                        });
                    });

                    updateCartBar();
                </script>
<jsp:include page="/common-scripts.jsp" />
            </body>

            </html>