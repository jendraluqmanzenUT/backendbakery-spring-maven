package com.jendra.Zen_Roti.entity;

import com.anakie.restApiBakery.service.ProductService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@RequiredArgsConstructor
@Table(name="`order`")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderStatusHistory> orderStatusHistories=new ArrayList<>();

    @Column(scale = 2)
    private double totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @Column(name="orderDateTime")
    private LocalDateTime dateTime=LocalDateTime.now();


}