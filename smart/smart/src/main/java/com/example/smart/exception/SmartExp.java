package com.example.smart.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
public class SmartExp extends RuntimeException{

    private final ErrorMessage errorMessage;

    public SmartExp(String errorCode){
        ErrorMessage message = ErrorMessageLoader.getMessage(errorCode);
        if(Objects.isNull(message)){
            message = new ErrorMessage();
            message.setVn(errorCode);
        }
        this.errorMessage = message;
    }

    public SmartExp(String errorCode, Object... args){
        this.errorMessage = ErrorMessageLoader.getMessage(errorCode);
    }
}
