package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.carYard.CarYardDto;

import java.io.IOException;

public interface CarYardService {

    CommonResponseDto createCarYard(CarYardDto carYard);
    CommonResponseDto updateCarYard(CarYardDto carYard);
    CommonResponseDto deleteCarYard(Long id);
    void initData() throws IOException;
}
