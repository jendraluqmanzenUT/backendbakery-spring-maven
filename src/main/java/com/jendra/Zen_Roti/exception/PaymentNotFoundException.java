package com.jendra.Zen_Roti.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String msg){
        super(msg);
    }
}
