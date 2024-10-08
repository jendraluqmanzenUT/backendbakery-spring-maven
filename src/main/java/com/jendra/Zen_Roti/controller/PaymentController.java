package com.jendra.Zen_Roti.controller;

import com.jendra.Zen_Roti.entity.Payment;
import com.jendra.Zen_Roti.entity.PaymentDTO;
import com.jendra.Zen_Roti.exception.*;
import com.jendra.Zen_Roti.service.PaymentService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/payments")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) throws PaymentNotFoundException {

        return new ResponseEntity<>(paymentService.findById(id), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAll() {
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Payment> save(@RequestBody PaymentDTO paymentDTO) throws OrderNotFoundException, UserNotFoundException, InsufficientFundsException, ProductNotFoundException, AccountNotFoundException, MessagingException{
        return new ResponseEntity<>(paymentService.save(paymentDTO), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Payment> update(@RequestBody PaymentDTO paymentDTO) throws OrderNotFoundException, UserNotFoundException, InsufficientFundsException, ProductNotFoundException, AccountNotFoundException, MessagingException {
        return new ResponseEntity<>(paymentService.update(paymentDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws PaymentNotFoundException {

        paymentService.deleteById(id);
        return new ResponseEntity<>("Payment id: " + id + " successfully removed", HttpStatus.GONE);

    }
}
