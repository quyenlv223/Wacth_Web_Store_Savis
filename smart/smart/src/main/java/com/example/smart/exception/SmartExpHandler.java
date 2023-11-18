package com.example.smart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SmartExpHandler {

    @ExceptionHandler(SmartExp.class)
    @ResponseBody
    ResponseEntity<?> worldPhoneNotFound(SmartExp e){
        return new ResponseEntity(e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
