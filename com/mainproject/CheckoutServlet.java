package com.mainproject;

import java.io.IOException;
import java.sql.Timestamp;

import com.pro1.dao.OrderDAOImpl;
import com.pro1.dao.OrderItemDAOImpl;
import com.pro1.dto.Cart;
import com.pro1.dto.CartItem;
import com.pro1.dto.Order;
import com.pro1.dto.Order_Item;
import com.pro1.dto.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        String address = req.getParameter("address");
        String paymentMethod = req.getParameter("paymentMethod");
        String fullName = req.getParameter("fullname");
        String city = req.getParameter("city");
        String phone = req.getParameter("phone");

        session.setAttribute("customerName", fullName);
        session.setAttribute("customerPhone", phone);

        Integer restaurantIdObj = (Integer) session.getAttribute("oldRestaurantId");

        if (restaurantIdObj == null) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        int restaurantId = restaurantIdObj;

        if (user == null) {
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req, resp);
            return;
        }

        if (cart != null && user != null && !cart.getItems().isEmpty()) {

            Order order = new Order();

            order.setUser_Id(user.getUser_ID());
            order.setRestaurant_Id(restaurantId);
            order.setOrder_Date(new Timestamp(System.currentTimeMillis()));
            order.setAddress(address);
            order.setStatus("Pending");
            order.setPayment_Mode(paymentMethod);

            /* -------- TOTAL CALCULATION -------- */

            double subtotal = 0.0;
            double tax = 0.0;
            double deliveryFee = 5.0;

            for (CartItem item : cart.getItems().values()) {
                subtotal += item.getQuantity() * item.getPrice();
            }

            tax = subtotal * 0.10;
            double totalAmount = subtotal + tax + deliveryFee;

            order.setTotal_Amount(totalAmount);

            /* -------- INSERT ORDER -------- */

            OrderDAOImpl orderDAOImpl = new OrderDAOImpl();

            int orderId = orderDAOImpl.insertOrder(order);

            /* -------- GENERATE ORDER NUMBER -------- */

            String orderNumber = "ORD" + System.currentTimeMillis();

            req.setAttribute("orderId", orderNumber);
            session.setAttribute("orderNumber", orderNumber);

            session.setAttribute("lastOrderId", orderId);

            /* -------- INSERT ORDER ITEMS -------- */

            OrderItemDAOImpl orderItemDAOImpl = new OrderItemDAOImpl();

            for (CartItem item : cart.getItems().values()) {

                int itemId = item.getItemId();
                int quantity = item.getQuantity();
                double totalPrice = item.getPrice() * item.getQuantity();

                Order_Item orderItem = new Order_Item();

                orderItem.setOrder_ID(orderId);
                orderItem.setMenu_ID(itemId);
                orderItem.setQuantity(quantity);
                orderItem.setTotal_Amount(totalPrice);

                orderItemDAOImpl.insertOrderItem(orderItem);
            }

            /* -------- PASS DATA TO JSP -------- */

            req.setAttribute("order", order);
            req.setAttribute("totalAmount", totalAmount);
            req.setAttribute("orderNumber", orderNumber);

            /* -------- CLEAR CART -------- */

            session.removeAttribute("cart");
            session.removeAttribute("oldRestaurantId");

            /* -------- REDIRECT TO CONFIRMATION PAGE -------- */

            RequestDispatcher rd = req.getRequestDispatcher("/OrderConfirmation.jsp");
            rd.forward(req, resp);

        } else {

            resp.sendRedirect("cart.jsp");
        }
    }
}