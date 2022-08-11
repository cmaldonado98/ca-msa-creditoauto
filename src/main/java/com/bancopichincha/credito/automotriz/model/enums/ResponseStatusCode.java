package com.bancopichincha.credito.automotriz.model.enums;

import lombok.Getter;

@Getter
public enum ResponseStatusCode {
    OK("0", "success"),
    INVALID_PARAMETERS("1","Some or all the parameter are null or empty %s"),
    UNDEFINED_ERROR("500","Unexpected error: %s"),
    UNDEFINED_VALUE("-1","status not found"),
    CLIENT_DOES_NOT_EXISTS("2", "Client not found"),
    CLIENT_HAS_ASSOCIATED_INFORMATION("3", "Client has associated information assignment or credit application"),
    CAR_YARD_DOES_NOT_EXISTS("4", "Car yard not found"),
    ;

    private final String code;
    private final String message;

    ResponseStatusCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
