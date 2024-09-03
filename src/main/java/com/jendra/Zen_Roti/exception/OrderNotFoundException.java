package com.jendra.Zen_Roti.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String msg){
        super(msg);
    }
}
