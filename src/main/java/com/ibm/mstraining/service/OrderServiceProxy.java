package com.ibm.mstraining.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.mstraining.model.Order;
import com.ibm.mstraining.model.ShoppingCart;

@FeignClient(name="order-service")
public interface OrderServiceProxy {
	@PostMapping("/order-service/placeOrder")
	public Order placeOrder(@RequestBody ShoppingCart cart);
}