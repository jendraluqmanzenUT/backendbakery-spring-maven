package com.jendra.Zen_Roti.exception;

public class MissingIngredientException extends RuntimeException{
    public MissingIngredientException(String msg){
        super(msg);
    }
}
