package com.bancopichincha.credito.automotriz.exception;

import com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicationException extends RuntimeException {

    private final ResponseStatusCode status;
}
