package com.jendra.Zen_Roti.controller;

import com.jendra.Zen_Roti.entity.ShoppingCart;
import com.jendra.Zen_Roti.exception.ShoppingCartNotFoundException;
import com.jendra.Zen_Roti.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shoppingCarts")

public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ShoppingCart> findById(@PathVariable Long id) throws ShoppingCartNotFoundException {

        return new ResponseEntity<>(shoppingCartService.findById(id), HttpStatus.OK);

    }
    @PostMapping("/save")
    public ResponseEntity<ShoppingCart> save(@RequestBody ShoppingCart shoppingCart) throws Exception {

        return new ResponseEntity<>(shoppingCartService.save(shoppingCart), HttpStatus.OK);

    }
    @PostMapping("/update")
    public ResponseEntity<ShoppingCart> update(@RequestBody ShoppingCart shoppingCart) throws Exception {

        return new ResponseEntity<>(shoppingCartService.update(shoppingCart), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ShoppingCart>> findAll(){
        return new ResponseEntity<>(shoppingCartService.findAll(), HttpStatus.OK);
    }



}
