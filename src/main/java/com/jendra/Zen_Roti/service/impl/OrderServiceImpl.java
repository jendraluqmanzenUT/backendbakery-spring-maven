package com.jendra.Zen_Roti.service.impl;

public class OrderServiceImpl {
}
package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.*;
        import com.jendra.Zen_Roti.exception.OrderNotFoundException;
import com.jendra.Zen_Roti.exception.OutOfStockException;
import com.jendra.Zen_Roti.exception.ProductNotFoundException;
import com.jendra.Zen_Roti.repository.OrderRepository;
import com.jendra.Zen_Roti.repository.OrderStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderStatusHistoryRepository orderStatusHistoryRepository;

    private  final  OrderRepository orderRepository;

    private  final UserService userService;

    private  final  ShoppingCartService shoppingCartService;

    private  final  ProductService productService;

    private  final  IngredientService ingredientService;

    public OrderServiceImpl(OrderStatusHistoryRepository orderStatusHistoryRepository,
                            OrderRepository orderRepository,
                            UserService userService,
                            ShoppingCartService shoppingCartService,
                            ProductService productService,
                            IngredientService ingredientService
    ) {
        this.orderStatusHistoryRepository = orderStatusHistoryRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.ingredientService = ingredientService;
    }

    @Override
    public Order save(OrderDTO orderDTO) throws Exception {
        if (orderDTO == null) {
            throw new NullPointerException("Null order not allowed, provide none-null object");
        }

        Order order = orderDTO.toOrder(userService);
        order.setShoppingCart(shoppingCartService.save(order.getShoppingCart()));
        order.setTotalPrice(calculateTotal(order)); // set the total price of the order
        changeOrderStatus(order = orderRepository.save(order), Status.Processing);
        return order;
    }

    @Override
    public void changeOrderStatus(Order order, Status status) {
        OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
        orderStatusHistory.setOrder(order);
        orderStatusHistory.setDateTime(LocalDateTime.now());
        orderStatusHistory.setStatus(status);
        orderStatusHistoryRepository.save(orderStatusHistory);
    }

    private double calculateTotal(Order order) throws ProductNotFoundException {
        double totalPrice = 0;
        for (CartItem cartItem : order.getShoppingCart().getCartItems()) {
            totalPrice += productService.totalAmount(cartItem.getProductId(), cartItem.getProductQty());
        }
        return totalPrice;
    }


    // once an order is made, we bake, when we bake we use ingredients, meaning, we subtract
    // all used ingredients according to the recipe... only when the user has paid, not in order time.
    public void bakeProducts(Order order) throws OutOfStockException {
        order.getShoppingCart().getCartItems().forEach(cartItem -> {

            Map<Ingredient, Double> recipeIngredients=productService.findById(cartItem.getProductId()).getRecipe().getRecipeIngredients();
            for (Ingredient recipeIngr: recipeIngredients.keySet()) {
                // this method is the one that does the magic o reducing ingredients
                ingredientService.useIngredient(recipeIngr.getId(),recipeIngredients.get(recipeIngr),cartItem.getProductQty());
            }
        });
        // When I bake, my database stock copy must be updated with new data,
        // so that the stockDb(copy) now  references the current state of ingredients available from database in real time
        ingredientService.reloadStockFromDb();
    }

    @Override
    public Order findById(Long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order " + id + " not found, use an existing id!"));
    }

    @Override
    public void deleteById(Long id) throws OrderNotFoundException {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Can't delete order " + id + " not found, use an existing id!");
        }
        orderRepository.deleteById(id);
    }


    @Override
    public Order update(Order order) throws OrderNotFoundException {
        if (!orderRepository.existsById(order.getId())) {
            throw new OrderNotFoundException("Can't update order " + order.getId() + " not found, use an existing id!");
        }
        return orderRepository.save(order);
    }

    public boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
