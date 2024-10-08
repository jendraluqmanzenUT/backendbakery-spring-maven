package com.jendra.Zen_Roti.entity;

import com.jendra.Zen_Roti.exception.UserNotFoundException;
import com.jendra.Zen_Roti.service.UserService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@Setter
@Slf4j
public class OrderDTO {

    private final LocalDateTime dateTime = LocalDateTime.now();
    private Long id;
    private Long userId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    public Order toOrder(UserService userService) throws UserNotFoundException {
        Order order = new Order();
        //if we don't find the user we throw an exception
        User user = userService.findById(userId);
        order.setUser(user);
        order.setShoppingCart(this.getShoppingCart());
        return order;

    }
}
