package com.cabeleireiro.agendamentroApi.domain.exception;

public class ControllerException extends RuntimeException{
    public ControllerException(String msg){
        super(msg);
    }
}
