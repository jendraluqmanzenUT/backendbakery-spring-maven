package com.jendra.Zen_Roti.entity;

import com.jendra.Zen_Roti.exception.OrderNotFoundException;
import com.jendra.Zen_Roti.service.OrderService;
import com.jendra.Zen_Roti.service.PaymentService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private Long orderId;

    private double amount;
    @Enumerated(EnumType.STRING)
    private PAYMENT_METHOD paymentMethod;

    public Payment toPayment(OrderService orderService) throws OrderNotFoundException {
        Payment payment=new Payment();
        payment.setOrder(orderService.findById(orderId));
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        return payment;
    }
}
