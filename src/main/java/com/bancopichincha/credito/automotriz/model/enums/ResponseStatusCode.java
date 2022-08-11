package com.bancopichincha.credito.automotriz.model.enums;

import lombok.Getter;

@Getter
public enum ResponseStatusCode {
    OK("0", "success"),
    INVALID_PARAMETERS("1","Some or all the parameter are null or empty"),
    UNDEFINED_ERROR("500","Unexpected error: %s"),
    UNDEFINED_VALUE("-1","status not found"),
    CLIENT_NOT_FOUND("2", "Client not found"),
    CAR_YARD_NOT_FOUND("2", "Car yard not found"),
    ;

    private final String code;
    private final String message;

    ResponseStatusCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
