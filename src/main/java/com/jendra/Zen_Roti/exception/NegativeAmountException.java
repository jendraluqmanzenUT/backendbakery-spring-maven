package com.jendra.Zen_Roti.exception;

public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException(String negativePaymentNotAllowed) {
        super(negativePaymentNotAllowed);
    }
}
