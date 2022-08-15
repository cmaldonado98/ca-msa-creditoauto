package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.carYard.CarYardDto;
import com.bancopichincha.credito.automotriz.model.entities.CarYardEntity;

import java.io.IOException;
import java.util.List;

public interface CarYardService {

    CommonResponseDto createCarYard(CarYardDto carYard);
    CommonResponseDto updateCarYard(CarYardDto carYard);
    CommonResponseDto deleteCarYard(Long id);
    List<CarYardEntity> getAllCarYards();
    CarYardEntity getCarYardById(Long id);
    void initData() throws IOException;
}
