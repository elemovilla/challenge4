package com.enyoi.challenge4.base.exceptions;

public class AlreadyExists extends  RuntimeException{
    private static final String DESCRIPTION = "dato ya existe";

    public AlreadyExists(String mensaje) {
        super(String.format("%s. %s", DESCRIPTION, mensaje));
    }
}