package com.pro1.dao;

import java.util.List;

import com.pro1.dto.Order_Item;

public interface OrderItemDAO {
  List getOrderItems();
  Order_Item getOrderItem(int Order_Item_ID);
  boolean insertOrderItem(Order_Item oi);	
  boolean updateOrderItem(Order_Item oi);
  boolean deleteOrderItem(int Order_Item_ID);
}
