package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.CartItem;
import com.jendra.Zen_Roti.entity.Ingredient;
import com.jendra.Zen_Roti.entity.Order;
import com.jendra.Zen_Roti.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final IngredientService ingredientService;


    private final OrderService orderService;


    private final ProductService productService;

    public ReportServiceImpl(IngredientService ingredientService, OrderService orderService, ProductService productService) {
        this.ingredientService = ingredientService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public Map<Long, Integer> mostSoldProducts(LocalDate fromDate, LocalDate toDate) {
        return orderService.findAll().stream()
                .filter(order -> {
                    LocalDate date = order.getDateTime().toLocalDate();
                    return date.isBefore(toDate) || date.isAfter(fromDate) || date.isEqual(fromDate) || date.isEqual(toDate);
                })
                .flatMap(order -> order.getShoppingCart().getCartItems().stream())
                .collect(Collectors.groupingBy(
                        CartItem::getProductId,   // Key mapper
                        LinkedHashMap::new,       // Map factory
                        Collectors.summingInt(CartItem::getProductQty)  // Downstream collector
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,   // Key mapper
                        Map.Entry::getValue,  // Value mapper
                        (e1, e2) -> e1,      // Merge function (retain original value in case of collision)
                        LinkedHashMap::new   // Map factory
                ));
    }

    @Override
    public Map<Long, Integer> mostSoldCategories(LocalDate fromDate, LocalDate toDate) {
        return orderService.findAll().stream()
                .filter(order -> {
                    LocalDate date = order.getDateTime().toLocalDate();
                    return date.isEqual(fromDate) || date.isEqual(toDate) || date.isAfter(fromDate) || date.isBefore(toDate);
                })
                .flatMap(order -> order.getShoppingCart().getCartItems().stream())
                .collect(Collectors.groupingBy(
                        cartItem -> productService.findById(cartItem.getProductId()).getCategory().getId(),
                        LinkedHashMap::new,
                        Collectors.summingInt(CartItem::getProductQty)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // sorting by value
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // Searches orders by date, still under processing
    @Override
    public List<Order> ordersPlaced(List<LocalDate> dates) {
        return dates.stream().flatMap(date ->
                orderService.findAll().stream().filter(order -> order.getDateTime().toLocalDate().equals(date))
                        .filter(order -> order.getOrderStatusHistories().get(0).getStatus().equals(Status.Processing))
        ).toList();
    }

    // searches all orders of a certain dates that are not delivered
    @Override
    public List<Order> outstandingOrders(List<LocalDate> dates) {

        return dates.stream().flatMap(date -> orderService.findAll().stream()
                .filter(order -> order.getDateTime().toLocalDate().equals(date))
                .filter(order -> !order.getOrderStatusHistories().get(order.getOrderStatusHistories().size() - 1).getStatus().equals(Status.Delivered))
        ).toList();
    }

    // all delivered orders in from certain dates
    @Override
    public List<Order> delivered(List<LocalDate> dates) {
        return dates.stream().flatMap(date -> orderService.findAll().stream()
                .filter(order -> order.getDateTime().toLocalDate().equals(date))
                .filter(order -> order.getOrderStatusHistories().get(order.getOrderStatusHistories().size() - 1).getStatus().equals(Status.Delivered))
        ).toList();
    }

    // all orders ths
    @Override
    public List<Ingredient> ingredientsToOrder() {
        // we'll let the admin/ system manager declare their minimum quantity for each ingredient
        return ingredientService.findAll().stream().filter(ingr -> ingr.getQuantity() <= ingr.getMin_quantity()).toList();
    }

    @Override
    public List<Ingredient> stockIngredients() {
        // searches all ingredients that have a quantity that's greater than 0,
        // ingredients with quantity of 0 means they are finished, therefore not available in the stock
        return ingredientService.findAll().stream().filter(ingr -> ingr.getQuantity() > 0).toList();
    }

}
