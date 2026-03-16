<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List,com.pro1.dto.Cart,com.pro1.dto.CartItem" %>
<%
if(session.getAttribute("user")==null){
response.sendRedirect("login.jsp");
return;
}
%>
        <% Cart cartObj=(Cart) session.getAttribute("cart"); double subtotal=0.0; double tax=0.0; double
            deliveryFee=5.0; double total=0.0; int cartCount=0; if (cartObj !=null && cartObj.getItems() !=null) {
            subtotal=cartObj.getTotalPrice(); tax=subtotal * 0.10; total=subtotal + tax + deliveryFee; for (CartItem
            item : cartObj.getItems().values()) { cartCount +=item.getQuantity(); } } %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>FoodyHub - Your Cart</title>
                <link rel="stylesheet" href="css/style.css">
                <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
                <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
            </head>

            <body class="cart-page-bg" >

                <!-- ADDED NAVBAR AS REQUESTED -->
                <nav class="navbar"
                    style="background: rgba(0,0,0,0.5); backdrop-filter: blur(10px); width: 100%; position:fixed; top:0; z-index:100; padding: 1rem 5%;">
                    <a href="restaurant.jsp" class="logo"
                        style="color:white; font-size:1.5rem; font-weight:bold; text-decoration:none;">FoodDelivery</a>
                    <ul class="nav-links"
                        style="list-style:none; display:flex; gap:2rem; margin-left:auto; align-items:center;">
                        <li><a href="restaurant" style="color:white; text-decoration:none;">Restaurants</a></li>
                        <li>
                            <a href="cart.jsp"
                                style="color: #FF9966; text-decoration:none; display:flex; align-items:center; gap:5px;">
                                <ion-icon name="cart"></ion-icon> Cart (<%= cartCount %>)
                            </a>
                        </li>
                    </ul>
                </nav>

                <div class="app-container"
                    style="display: flex; justify-content: center; align-items: center; min-height: 100vh; padding-top: 80px;">

                    <% 
                    	Cart cart=(Cart)session.getAttribute("cart"); 
                    	Integer restaurantId = (Integer)session.getAttribute("oldRestaurantId"); 
                    	if(cart !=null && cart.getItems() !=null && !cart.getItems().isEmpty()) 
                    { %>

                        <div class="cart-page-container"
                            style="display: grid; grid-template-columns: 1fr 380px; gap: 4rem; width: 100%; max-width: 1100px;">

                            <!-- List Column -->
                            <div class="cart-items-list" style="display: flex; flex-direction: column; gap: 1.5rem;">

                                <% for(CartItem item : cart.getItems().values()) { %>
                                    <!-- Glass Item Card -->
                                    <div class="light-glass-card">
                                        <img src="<%=item.getImagePath()%>" alt="<%=item.getName()%>"
                                            class="cart-img-square-light">

                                        <div style="flex: 1;">
                                            <h3 style="font-size: 1.3rem; margin-bottom: 5px; font-weight: 700;">
                                                <%=item.getName() %>
                                            </h3>

                                            <!-- Quantity Controls -->
                                            <div style="display: flex; align-items: center; margin-top: 10px;">

                                                <form action="cart" style="display:inline;">
                                                    <input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                                                    <input type="hidden" name="action" value="update">
                                                    <input type="hidden" name="restaurantId" value="<%=restaurantId%>">
                                                    <input type="hidden" name="quantity"
                                                        value="<%=item.getQuantity() - 1%>">
                                                    <button class="qty-btn-light" <%if(item.getQuantity()==1){
                                                        %>disabled<%}%>>-</button>
                                                </form>

                                                <span class="qty-display-light">
                                                    <%=item.getQuantity()%>
                                                </span>

                                                <form action="cart" style="display:inline;">
                                                    <input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                                                    <input type="hidden" name="action" value="update">
                                                    <input type="hidden" name="restaurantId" value="<%=restaurantId%>">
                                                    <input type="hidden" name="quantity"
                                                        value="<%=item.getQuantity() + 1%>">
                                                    <button class="qty-btn-light">+</button>
                                                </form>
                                            </div>
                                        </div>

                                        <span style="font-weight:700; font-size:1.3rem;">₹<%= String.format("%.2f",
                                                item.getPrice() * item.getQuantity()) %></span>

                                        <!-- Remove Button -->
                                        <form action="cart" style="margin-left: 10px;">
                                            <input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                                            <input type="hidden" name="restaurantId" value="<%=restaurantId%>">
                                            <input type="hidden" name="action" value="delete">
                                            <button
                                                style="background:none; border:none; color:#D32F2F; cursor:pointer; font-size:1.4rem;">
                                                <ion-icon name="trash-outline"></ion-icon>
                                            </button>
                                        </form>
                                    </div>
                                    <% } %>

                                        <!-- Add More Items Button -->
                                        <div style="text-align:center; margin-top:0.5rem;">
                                            <!-- Fixed Typo EstaurantId -> RestaurantId -->
                                            
                                            <% if (restaurantId != null) { %>
<a href="menu?restaurantId=<%= restaurantId %>" class="btn-add-more">
    <ion-icon name="add-circle-outline" style="vertical-align: middle; margin-right: 5px;"></ion-icon> Add More Items
</a>
<% } %>
                                        </div>

                            </div>

                            <!-- Summary Column -->
                            <div class="cart-summary-col">
                                <div class="light-glass-summary">
                                    <div class="summary-row-light">
                                        <span>Subtotal:</span>
                                        <span>₹<%=String.format("%.2f", subtotal)%></span>
                                    </div>
                                    <div class="summary-row-light">
                                        <span>Delivery Fee:</span>
                                        <span>₹<%=String.format("%.2f", deliveryFee)%></span>
                                    </div>
                                    <div class="summary-row-light">
                                        <span>Tax (10%):</span>
                                        <span>₹<%=String.format("%.2f", tax)%></span>
                                    </div>

                                    <div class="summary-total-light">
                                        <span>Total:</span>
                                        <span>₹<%=String.format("%.2f", total)%></span>
                                    </div>

                                    <form action="checkout.jsp" method="post">
                                        <button class="btn-checkout-orange">Proceed to Checkout</button>
                                    </form>
                                </div>
                            </div>

                        </div>

                        <% } else { %>

                            <!-- Empty Cart State -->
                            <div style="display: flex; justify-content: center; width: 100%; padding: 4rem 0;">
                                <div
                                    style="background: rgba(255, 255, 255, 0.1); backdrop-filter: blur(10px); padding: 3rem; border-radius: 24px; text-align: center; max-width: 500px; border: 1px solid rgba(255,255,255,0.2); box-shadow: 0 15px 40px rgba(0,0,0,0.3);">

                                    <!-- Illustration -->
                                    <div style="margin-bottom: 2rem; position: relative; display: inline-block;">
                                        <div
                                            style="position: absolute; inset: 0; background: radial-gradient(circle, rgba(255,153,102,0.4) 0%, transparent 70%); filter: blur(20px);">
                                        </div>
                                        <img src="https://cdn3d.iconscout.com/3d/premium/thumb/empty-cart-5590709-4652401.png"
                                            alt="Empty Cart"
                                            style="width: 280px; height: auto; position: relative; z-index: 1; filter: drop-shadow(0 10px 30px rgba(0,0,0,0.3));">
                                    </div>

                                    <h2
                                        style="font-size: 2.2rem; font-weight: 800; margin-bottom: 0.5rem; color: white;">
                                        Your Cart is <span style="color: #FF9966;">Empty</span>
                                    </h2>

                                    <p
                                        style="font-size: 1.1rem; color: rgba(255,255,255,0.7); margin-bottom: 2.5rem; line-height: 1.6;">
                                        Looks like you haven't made your choice yet. The best food is waiting for you!
                                    </p>

                                    <a href="restaurant" style="text-decoration:none">
                                        <button class="btn-checkout-orange"
                                            style="width: 100%; padding: 1rem 2rem; border-radius: 50px; font-size: 1.2rem; display: flex; justify-content: center; align-items: center; gap: 10px;">
                                            Start Ordering <ion-icon name="arrow-forward-circle-outline"
                                                style="font-size: 1.4rem;"></ion-icon>
                                        </button>
                                    </a>
                                </div>
                            </div>

                            <% } %>

                </div>
<jsp:include page="/common-scripts.jsp" />
            </body>

            </html>