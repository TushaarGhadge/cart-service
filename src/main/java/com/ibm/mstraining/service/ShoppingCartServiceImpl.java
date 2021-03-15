package com.ibm.mstraining.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.mstraining.model.Order;
import com.ibm.mstraining.model.Product;
import com.ibm.mstraining.model.ShoppingCart;
import com.ibm.mstraining.model.ShoppingCartItem;

@Component
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private CatalogServiceProxy catalogService;

    @Autowired
    private PriceCalculationService priceCalculationService;
    
    @Autowired
    private OrderServiceProxy orderService;

    private Map<Long, ShoppingCart> cartDB = new HashMap<>();

    @Override
    public ShoppingCart calculateCartPrice(ShoppingCart sc) {
        priceCalculationService.priceShoppingCart(sc);
        cartDB.put(sc.getId(), sc);
        return sc;
    }

    @Override
    public ShoppingCart getShoppingCart(long cartId) {
        ShoppingCart sc = cartDB.get(cartId);
        if (sc == null) {
            sc = new ShoppingCart();
            sc.setId(cartId);
            cartDB.put(cartId, sc);
        }
        return sc;
    }

    @Override
    public ShoppingCart addToCart(long cartId, long itemId, int quantity) {
        ShoppingCart sc = getShoppingCart(cartId);
        if (quantity <= 0) {
            return sc;
        }
        Product product;
        product = getProduct(itemId);
        if (product == null) {
            return sc;
        }
        Optional<ShoppingCartItem> cartItem = sc.getShoppingCartItemList().stream().filter(sci -> sci.getProduct().getItemId() ==(itemId)).findFirst();
        if (cartItem.isPresent()) {
            cartItem.get().setQuantity(cartItem.get().getQuantity() + quantity);
        } else {
            ShoppingCartItem newCartItem = new ShoppingCartItem();
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            newCartItem.setPrice(product.getPrice());
            sc.addShoppingCartItem(newCartItem);
        }
        calculateCartPrice(sc);
        cartDB.put(sc.getId(), sc);
        return sc;
    }

    @Override
    public ShoppingCart removeFromCart(long cartId, long itemId, int quantity) {
        ShoppingCart sc = getShoppingCart(cartId);
        if (quantity <= 0) {
            return sc;
        }
        Optional<ShoppingCartItem> cartItem = sc.getShoppingCartItemList().stream().filter(sci -> sci.getProduct().getItemId()==(itemId)).findFirst();
        if (cartItem.isPresent()) {
            if (cartItem.get().getQuantity() <= quantity) {
                sc.removeShoppingCartItem(cartItem.get());
            } else {
                cartItem.get().setQuantity(cartItem.get().getQuantity() - quantity);
            }
        }
        calculateCartPrice(sc);
        cartDB.put(sc.getId(), sc);
        return sc;
    }

    @Override
    public Order checkoutShoppingCart(long cartId) {
       return orderService.placeOrder(getShoppingCart(cartId)); 
    }

    private Product getProduct(long itemId) {
        return catalogService.getProduct(itemId);
    }
}
