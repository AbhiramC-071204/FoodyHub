package com.pro1.dto;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Integer,CartItem> items; //key id
	
	public Cart() {
		this.items = new HashMap<>();
	}
	
	//Cart.java to CartServlet
	public void addItem(CartItem item) {
		int itemId = item.getItemId();
		if(items.containsKey(itemId))
		{
			CartItem existingItem = items.get(itemId);
			int newQty = item.getQuantity();
			int oldQty = existingItem.getQuantity();
			int sumQty = newQty + oldQty;
			existingItem.setQuantity(sumQty);
		}
		else
		{
			items.put(itemId,item);
		}
	}
	
	public void updateItem(int itemId,int quantity)
	{
		if(items.containsKey(itemId))
		{
			if(quantity <= 0)
			{
				items.remove(itemId);
			}
			else
			{
				CartItem existingCartItem = items.get(itemId);
				existingCartItem.setQuantity(quantity);
			}
		}
	}
	
	public void removeItem(int itemId)
	{
		items.remove(itemId);
	}
	
	public Map<Integer,CartItem> getItems()
	{
		return items;
	}
	
	public double getTotalPrice()
	{	
		double totalprice = 0.0;
		
		for(CartItem item : items.values()) {	
			totalprice = totalprice + item.getPrice() * item.getQuantity();	
		}
		
		return totalprice;
		
		//return items.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
	}

	public void clear() {
		items.clear();
	}
	
}
