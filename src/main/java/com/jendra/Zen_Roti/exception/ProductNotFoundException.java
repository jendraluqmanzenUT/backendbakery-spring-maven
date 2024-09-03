package com.jendra.Zen_Roti.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg){
        super(msg);
    }
}
