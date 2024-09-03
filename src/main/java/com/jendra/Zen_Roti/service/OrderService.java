package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.Order;
import com.jendra.Zen_Roti.entity.OrderDTO;
import com.jendra.Zen_Roti.entity.Status;
import com.jendra.Zen_Roti.exception.*;

import java.util.List;

public interface OrderService {

    void bakeProducts(Order order) throws OutOfStockException;

    void changeOrderStatus(Order order, Status status);

    Order save(OrderDTO orderDTO) throws Exception;
    Order findById(Long id) throws OrderNotFoundException;

    void deleteById(Long id) throws OrderNotFoundException;

    Order update(Order orders) throws OrderNotFoundException;

    List<Order> findAll();
    boolean existsById(Long id);

}
