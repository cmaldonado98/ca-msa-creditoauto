package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.exception.ApplicationException;
import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.carYard.CarYardDto;
import com.bancopichincha.credito.automotriz.model.entities.CarYardEntity;
import com.bancopichincha.credito.automotriz.model.entities.ExecutiveEntity;
import com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode;
import com.bancopichincha.credito.automotriz.repository.AssignmentRepository;
import com.bancopichincha.credito.automotriz.repository.CarYardRepository;
import com.bancopichincha.credito.automotriz.repository.CreditApplicationRepository;
import com.bancopichincha.credito.automotriz.repository.ExecutiveRepository;
import com.bancopichincha.credito.automotriz.service.CarYardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode.OK;

@Service
@Log4j2
@AllArgsConstructor
public class CarYardServiceImpl implements CarYardService {

    private final CarYardRepository carYardRepository;

    private final AssignmentRepository assignmentRepository;

    private final ExecutiveRepository executiveRepository;

    private final CreditApplicationRepository creditApplicationRepository;

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

    @Override
    public CommonResponseDto updateCarYard(CarYardDto carYard) {
        log.info(String.format("Updating car yard with id: %s", carYard.getIdCarYard().toString()));

        CarYardEntity carYardEntity = carYardRepository.findById(carYard.getIdCarYard())
                .orElseThrow(() -> {
                    log.error(String.format("Car Yard to be updated not found id: %s", carYard.getIdCarYard().toString()));
                    return new ApplicationException(ResponseStatusCode.CAR_YARD_DOES_NOT_EXISTS);
                });

        carYardEntity.setName(carYard.getName());
        carYardEntity.setAddress(carYard.getAddress());
        carYardEntity.setPhone(carYard.getPhone());
        carYardEntity.setNumberSalesPoint(carYard.getNumberSalesPoint());

        carYardRepository.save(carYardEntity);

        return CommonResponseDto.build(OK);
    }

    @Override
    public CommonResponseDto deleteCarYard(Long id) {
        log.info(String.format("Deleting car yard with id: %s", id.toString()));

        if (assignmentRepository.existsByCarYardEntity_IdCarYard(id) || executiveRepository.existsByCarYardEntity_IdCarYard(id) || creditApplicationRepository.existsByCarYardEntity_IdCarYard(id)) {
            throw new ApplicationException(ResponseStatusCode.CAR_YARD_HAS_ASSOCIATED_INFORMATION);
        }

        CarYardEntity carYardEntity = carYardRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(String.format("Car Yard to be deleted not found id: %s", id));
                    return new ApplicationException(ResponseStatusCode.CAR_YARD_DOES_NOT_EXISTS);
                });
        carYardRepository.delete(carYardEntity);
        return CommonResponseDto.build(OK);

    }

    @Override
    public void initData() throws IOException {
        log.info("UPLOAD CAR YARDS DATA");
        if (carYardRepository.findAll().isEmpty()) {

            List<CarYardEntity> carYardEntities = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new ClassPathResource("files/car_yards.csv").getFile()));
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                CarYardEntity carYardEntity = new CarYardEntity();
                carYardEntity.setAddress(values[0]);
                carYardEntity.setName(values[1]);
                carYardEntity.setNumberSalesPoint(Long.valueOf(values[2]));
                carYardEntity.setPhone(values[3]);
                carYardEntities.add(carYardEntity);
            }
            carYardRepository.saveAll(carYardEntities);
        }

    }
}
