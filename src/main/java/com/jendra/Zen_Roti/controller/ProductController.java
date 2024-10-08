package com.jendra.Zen_Roti.controller;

import com.jendra.Zen_Roti.entity.Product;
import com.jendra.Zen_Roti.entity.ProductDTO;
import com.jendra.Zen_Roti.exception.CategoryNotFoundException;
import com.jendra.Zen_Roti.exception.ProductNotFoundException;
import com.jendra.Zen_Roti.exception.RecipeNotFoundException;
import com.jendra.Zen_Roti.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) throws ProductNotFoundException {

        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody ProductDTO productDTO) throws RecipeNotFoundException, CategoryNotFoundException {
        return new ResponseEntity<>(productService.save(productDTO), HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<Product> update(@RequestBody ProductDTO productDTO) throws RecipeNotFoundException, CategoryNotFoundException {
        return new ResponseEntity<>(productService.update(productDTO), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws ProductNotFoundException {

        productService.deleteById(id);
        return new ResponseEntity<>("Product id: "+id+" Successfully removed", HttpStatus.GONE);

    }

}
