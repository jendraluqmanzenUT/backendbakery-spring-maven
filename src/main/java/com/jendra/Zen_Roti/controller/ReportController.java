package com.jendra.Zen_Roti.controller;


import com.jendra.Zen_Roti.entity.Ingredient;
import com.jendra.Zen_Roti.entity.Order;
import com.jendra.Zen_Roti.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/ordersPlaced")
    public ResponseEntity<List<Order>> ordersPlaced(@RequestBody List<LocalDate> dates){
        return new ResponseEntity<>(reportService.ordersPlaced(dates), HttpStatus.OK);
    }
    @GetMapping("/outstandingOrders")
    public ResponseEntity<List<Order>> outstandingOrders(@RequestBody List<LocalDate> dates){
        return new ResponseEntity<>(reportService.outstandingOrders(dates), HttpStatus.OK);
    }

    @GetMapping("/delivered")
    public ResponseEntity<List<Order>> delivered(@RequestBody List<LocalDate> dates){
        return new ResponseEntity<>(reportService.delivered(dates), HttpStatus.OK);
    }
    @GetMapping("/ingredientsToOrder")
    public ResponseEntity<List<Ingredient>> ingredientsToOrder(){
        return new ResponseEntity<>(reportService.ingredientsToOrder(), HttpStatus.OK);
    }

    @GetMapping("/stockIngredients")
    public ResponseEntity<List<Ingredient>> stockIngredients(){
        return new ResponseEntity<>(reportService.stockIngredients(), HttpStatus.OK);
    }

    @GetMapping("/mostSoldProduct/{fromDate}/{toDate}")
    public ResponseEntity<Map<Long,Integer>> mostSoldProduct(@PathVariable LocalDate fromDate, @PathVariable  LocalDate toDate){
        return new ResponseEntity<>(reportService.mostSoldProducts(fromDate,toDate),HttpStatus.OK);
    }
    @GetMapping("/mostSoldCategory/{fromDate}/{toDate}")
    public ResponseEntity<Map<Long,Integer>> mostSoldCategory(@PathVariable LocalDate fromDate, @PathVariable  LocalDate toDate){
        return new ResponseEntity<>(reportService.mostSoldCategories(fromDate,toDate),HttpStatus.OK);
    }


}
