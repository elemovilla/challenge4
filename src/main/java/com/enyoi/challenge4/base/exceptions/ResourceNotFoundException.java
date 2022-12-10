package com.enyoi.challenge4.base.exceptions;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String menssage) {
        super(menssage);
    }
}
