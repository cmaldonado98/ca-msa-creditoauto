package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.exception.ApplicationException;
import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.car.CarDto;
import com.bancopichincha.credito.automotriz.model.entities.BrandEntity;
import com.bancopichincha.credito.automotriz.model.entities.CarEntity;
import com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode;
import com.bancopichincha.credito.automotriz.repository.BrandRepository;
import com.bancopichincha.credito.automotriz.repository.CarRepository;
import com.bancopichincha.credito.automotriz.repository.CreditApplicationRepository;
import com.bancopichincha.credito.automotriz.service.CarService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode.OK;
import static com.bancopichincha.credito.automotriz.util.Constant.CAR_RELEASED;

@Service
@Log4j2
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CreditApplicationRepository creditApplicationRepository;

    @Override
    @Transactional
    public CommonResponseDto createCar(CarDto car) {
        log.info(String.format("Creating car with model: %s", car.getModel()));
        BrandEntity brandEntity = brandRepository.findById(car.getIdBrand())
                .orElseThrow(() -> {
                    log.error(String.format("Brand not found id: %s", car.getIdBrand().toString()));
                    return new ApplicationException(ResponseStatusCode.BRAND_DOES_NOT_EXISTS);
                });

        CarEntity newCar = new CarEntity();
        newCar.setPlate(car.getPlate());
        newCar.setModel(car.getModel());
        newCar.setChassisNumber(car.getChassisNumber());
        newCar.setType(car.getType());
        newCar.setDisplacement(car.getDisplacement());
        newCar.setAppraisal(car.getAppraisal());
        newCar.setBrandEntity(brandEntity);
        newCar.setStatus(CAR_RELEASED);

        newCar = carRepository.save(newCar);

        return CommonResponseDto.builder()
                .code(OK.getCode())
                .message(OK.getMessage())
                .response(String.format("ID: %s Model: %s", newCar.getIdCar(), newCar.getModel()))
                .build();
    }

    @Override
    public CommonResponseDto updateCar(CarDto car) {
        log.info(String.format("Updating car with model: %s", car.getModel()));

        CarEntity carEntity = carRepository.findById(car.getIdCar())
                .orElseThrow(() -> {
                    log.error(String.format("Car to be updated not found id: %s", car.getIdCar().toString()));
                    return new ApplicationException(ResponseStatusCode.CAR_DOES_NOT_EXISTS);
                });
        BrandEntity brandEntity = brandRepository.findById(car.getIdBrand())
                .orElseThrow(() -> {
                    log.error(String.format("Brand not found id: %s", car.getIdBrand().toString()));
                    return new ApplicationException(ResponseStatusCode.BRAND_DOES_NOT_EXISTS);
                });

        carEntity.setPlate(car.getPlate());
        carEntity.setModel(car.getModel());
        carEntity.setChassisNumber(car.getChassisNumber());
        carEntity.setType(car.getType());
        carEntity.setDisplacement(car.getDisplacement());
        carEntity.setAppraisal(car.getAppraisal());
        carEntity.setBrandEntity(brandEntity);
        carEntity.setStatus(car.getStatus());

        carRepository.save(carEntity);

        return CommonResponseDto.build(OK);
    }

    @Override
    public CommonResponseDto deleteCar(Long id) {
        log.info(String.format("Deleting car with id: %s", id.toString()));

        if (creditApplicationRepository.existsByCarEntity_IdCar(id)) {
            throw new ApplicationException(ResponseStatusCode.CAR_HAS_ASSOCIATED_INFORMATION);
        }

        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(String.format("Car to be updated not found id: %s", id));
                    return new ApplicationException(ResponseStatusCode.CAR_DOES_NOT_EXISTS);
                });


        carRepository.delete(carEntity);
        return CommonResponseDto.build(OK);
    }
}
