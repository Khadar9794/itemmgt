package com.dxctraining.exceptions;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String msg)
    {
        super(msg);
    }
}
