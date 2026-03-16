package com.pro1.dao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.pro1.dto.Order;

public interface OrderDAO {
 List getOrders();
 Order getOrder(int Order_Id);
 boolean insertOrder(Order order);
 //boolean insertOrder(int OrderId, int User_Id, int Restaurant_Id, Timestamp Order_Date, double Total_Amount,String Payment_Mode,String Address, String Status);
 boolean updateOrder(Order order);
 boolean deleteOrder(int Order_Id);
}
