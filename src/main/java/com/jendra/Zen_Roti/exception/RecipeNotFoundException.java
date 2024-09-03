package com.jendra.Zen_Roti.exception;

public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException(String msg){
        super(msg);
    }
}
