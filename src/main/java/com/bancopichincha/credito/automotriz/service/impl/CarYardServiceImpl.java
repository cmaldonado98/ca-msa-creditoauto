package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.carYard.CarYardDto;
import com.bancopichincha.credito.automotriz.model.entities.CarYardEntity;
import com.bancopichincha.credito.automotriz.repository.CarYardRepository;
import com.bancopichincha.credito.automotriz.service.CarYardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode.OK;

@Service
@Log4j2
@AllArgsConstructor
public class CarYardServiceImpl implements CarYardService {

    private final CarYardRepository carYardRepository;

    @Override
    @Transactional
    public CommonResponseDto createCarYard(CarYardDto carYard) {
        log.info(String.format("Creating car yard with name: %s", carYard.getName()));

        CarYardEntity newCarYard = new CarYardEntity();
        newCarYard.setName(carYard.getName());
        newCarYard.setAddress(carYard.getAddress());
        newCarYard.setPhone(carYard.getPhone());
        newCarYard.setNumberSalesPoint(carYard.getNumberSalesPoint());

        newCarYard = carYardRepository.save(newCarYard);

        return CommonResponseDto.builder()
                .code(OK.getCode())
                .message(OK.getMessage())
                .response(String.format("ID: %s", newCarYard.getIdCarYard()))
                .build();
    }
}
