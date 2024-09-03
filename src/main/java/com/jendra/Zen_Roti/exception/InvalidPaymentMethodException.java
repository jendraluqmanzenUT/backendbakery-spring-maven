package com.jendra.Zen_Roti.exception;

public class InvalidPaymentMethodException extends RuntimeException {
    public InvalidPaymentMethodException(String msg) {
        super(msg);
    }
}
