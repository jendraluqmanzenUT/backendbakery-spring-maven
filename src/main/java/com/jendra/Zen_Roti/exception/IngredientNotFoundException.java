package com.jendra.Zen_Roti.exception;


public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(String msg){
        super(msg);
    }
}
