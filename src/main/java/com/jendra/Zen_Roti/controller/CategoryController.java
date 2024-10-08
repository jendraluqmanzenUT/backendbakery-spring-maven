package com.jendra.Zen_Roti.controller;

import com.jendra.Zen_Roti.entity.Category;
import com.jendra.Zen_Roti.exception.CategoryNotFoundException;
import com.jendra.Zen_Roti.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){

        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Category> save(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws CategoryNotFoundException {
        categoryService.deleteById(id);
        return new ResponseEntity<>("Category id: "+id+" successfully removed", HttpStatus.GONE);
    }
}
