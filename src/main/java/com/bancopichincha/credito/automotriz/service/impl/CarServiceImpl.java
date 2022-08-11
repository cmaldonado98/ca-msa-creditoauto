package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.car.CarDto;
import com.bancopichincha.credito.automotriz.repository.CarRepository;
import com.bancopichincha.credito.automotriz.service.CarService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public CommonResponseDto createCar(CarDto car) {
        return null;
    }
}
