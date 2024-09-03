package com.jendra.Zen_Roti.controller;

import com.jendra.Zen_Roti.entity.Order;
import com.jendra.Zen_Roti.entity.OrderDTO;
import com.jendra.Zen_Roti.exception.OrderNotFoundException;
import com.jendra.Zen_Roti.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Order> save(@RequestBody OrderDTO orderDTO) throws Exception {
        return new ResponseEntity<>(orderService.save(orderDTO), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Order> update(@RequestBody OrderDTO orderDTO) throws Exception {
        return new ResponseEntity<>(orderService.save(orderDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws OrderNotFoundException {

        orderService.deleteById(id);
        return new ResponseEntity<>("Order id: " + id + " successfully removed", HttpStatus.GONE);

    }

}
