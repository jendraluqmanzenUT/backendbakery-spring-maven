package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.Payment;
import com.jendra.Zen_Roti.entity.PaymentDTO;
import com.jendra.Zen_Roti.entity.Status;
import com.jendra.Zen_Roti.exception.*;
import jakarta.mail.MessagingException;

import java.util.List;

public interface PaymentService {
    Payment save(PaymentDTO paymentsDTO) throws OrderNotFoundException, ProductNotFoundException, InsufficientFundsException, UserNotFoundException, AccountNotFoundException, MessagingException;
    Payment findById(Long id) throws PaymentNotFoundException;

    void deleteById(Long id) throws PaymentNotFoundException;

    Payment update(PaymentDTO paymentsDTO) throws PaymentNotFoundException;

    List<Payment> findAll();

}
