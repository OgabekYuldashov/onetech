package com.mum.onetech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public DomainErrorDTO handleInvalidBinding(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        DomainErrorDTO domainError = new DomainErrorDTO("ValidationError");
        for(FieldError fe : fieldErrors){
            domainError.addFieldError(new FieldErrorDTO(fe.getField(), fe.getDefaultMessage()));
        }
        return domainError;
    }
}
