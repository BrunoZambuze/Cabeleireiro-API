package com.cabeleireiro.agendamentroApi.api.exceptionhandler;

import com.cabeleireiro.agendamentroApi.domain.exception.ControllerException;
import com.cabeleireiro.agendamentroApi.domain.exception.RegraNegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<String> ControllerException(ControllerException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<String> RegraDeNegocioException(RegraNegocioException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
