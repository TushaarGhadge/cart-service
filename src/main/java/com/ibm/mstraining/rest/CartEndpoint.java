package com.ibm.mstraining.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mstraining.model.Order;
import com.ibm.mstraining.model.ShoppingCart;
import com.ibm.mstraining.service.ShoppingCartService;


@RestController
//@RequestMapping("/cart")
public class CartEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(CartEndpoint.class);
	
	@Autowired
    private ShoppingCartService shoppingCartService;
	
	@GetMapping("/cart/{cartId}")
	public ShoppingCart getCart(@PathVariable long cartId) {

		return shoppingCartService.getShoppingCart(cartId);	
	}	
	
    @PostMapping("/cart/{cartId}/item/{itemId}/quantity/{quantity}")
    public ShoppingCart add(@PathVariable long cartId, 
    							@PathVariable long itemId, 
    							@PathVariable int quantity) {

    		return shoppingCartService.addToCart(cartId, itemId, quantity);
    }
    
    @DeleteMapping("/cart/{cartId}/item/{itemId}/quantity/{quantity}")
    public ShoppingCart delete(@PathVariable long cartId, 
    								@PathVariable long itemId, 
    								@PathVariable int quantity) {

        return shoppingCartService.removeFromCart(cartId, itemId, quantity);
    }
    
    @PostMapping("/checkout/{cartId}")
    public Order checkout(@PathVariable long cartId) {

        Order orderSaved = shoppingCartService.checkoutShoppingCart(cartId);
        return orderSaved;
    }    
}