package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.car.CarDto;
import com.bancopichincha.credito.automotriz.model.entities.CarEntity;

import java.util.List;

public interface CarService {

    CommonResponseDto createCar(CarDto car);

    CommonResponseDto updateCar(CarDto car);
    CommonResponseDto deleteCar(Long id);
    List<CarEntity> getAllCars();

    CarEntity getCarById(Long id);
}
