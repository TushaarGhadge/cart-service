package com.ibm.mstraining.service;

import com.ibm.mstraining.model.Order;
import com.ibm.mstraining.model.ShoppingCart;

public interface ShoppingCartService {

    public ShoppingCart calculateCartPrice(ShoppingCart sc);

    public ShoppingCart getShoppingCart(long cartId);

    public ShoppingCart addToCart(long cartId, long itemId, int quantity);

    public ShoppingCart removeFromCart(long cartId, long itemId, int quantity);

    public Order checkoutShoppingCart(long cartId);

}
