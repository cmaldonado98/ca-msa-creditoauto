package com.bancopichincha.credito.automotriz.model.dto;

import com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommonResponseDto {
    private String code;
    private String message;
    private String response;

    public static CommonResponseDto build(ResponseStatusCode status){
        return CommonResponseDto.builder().code(status.getCode()).message(status.getMessage()).build();
    }
}
