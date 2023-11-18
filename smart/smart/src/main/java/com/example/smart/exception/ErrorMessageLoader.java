package com.example.smart.exception;

import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ErrorMessageLoader {
    private static Map<String, ErrorMessage> errorMessageMap = new HashMap<>();

    public ErrorMessageLoader(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message_vn");
        Enumeration massageKeys = resourceBundle.getKeys();
        while (massageKeys.hasMoreElements()){
            String key = (String) massageKeys.nextElement();
            String value = resourceBundle.getString(key);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setCode(key);
            errorMessage.setVn(value);
            errorMessageMap.put(key, errorMessage);
        }
    }

    public static ErrorMessage getMessage(String errorCode){
        return errorMessageMap.get(errorCode);
    }

    public static ErrorMessage getMessage(String errorCode, Object args){
        ErrorMessage errorMessage = errorMessageMap.get(errorCode);
        if(Objects.nonNull(errorMessage.getVn())){
            errorMessage.setEn(String.format(errorMessage.getVn(), args));
        }
        if (Objects.nonNull(errorMessage.getEn())) {
            errorMessage.setEn(String.format(errorMessage.getEn(), args));
        }
        return errorMessage;
    }

}
