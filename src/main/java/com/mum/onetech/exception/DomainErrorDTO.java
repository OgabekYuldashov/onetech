package com.mum.onetech.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DomainErrorDTO {
    private String errorType;
    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public DomainErrorDTO(String errorType) {
        this.errorType = errorType;
    }

    public void addFieldError(FieldErrorDTO fieldError){
        fieldErrors.add(fieldError);
    }
}
