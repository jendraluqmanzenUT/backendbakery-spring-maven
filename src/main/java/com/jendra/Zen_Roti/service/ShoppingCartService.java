package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.ShoppingCart;
import com.jendra.Zen_Roti.exception.*;

import java.util.List;

public interface ShoppingCartService {
    boolean addProduct(Long productId,int productQty) throws OutOfStockException, ShoppingCartNotFoundException, ProductNotFoundException;
    boolean removeProduct(Long productId,int qty) throws ProductNotFoundException, InvalidProductQuantityException;

    ShoppingCart save(ShoppingCart shoppingCart) throws Exception;
    List<ShoppingCart> findAll();
    void deleteById(Long id) throws ShoppingCartNotFoundException;

    ShoppingCart update(ShoppingCart shoppingCart) throws ShoppingCartNotFoundException;

    ShoppingCart findById(Long id) throws ShoppingCartNotFoundException;
    boolean existsById(Long id);

    boolean validateCart(ShoppingCart cart) throws ShoppingCartNotFoundException, CartEmptyException, ProductNotFoundException;
}
