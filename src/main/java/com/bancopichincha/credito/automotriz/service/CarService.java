package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.car.CarDto;

public interface CarService {

    CommonResponseDto createCar(CarDto car);
}
