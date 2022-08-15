package com.bancopichincha.credito.automotriz.mapper;

import com.bancopichincha.credito.automotriz.model.dto.car.CarDto;
import com.bancopichincha.credito.automotriz.model.dto.client.ClientDto;
import com.bancopichincha.credito.automotriz.model.entities.CarEntity;
import com.bancopichincha.credito.automotriz.model.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class GeneralMapper {

    public ClientDto mapClient(ClientEntity client){
        return ClientDto.builder()
                .clientId(client.getIdClient())
                .identification(client.getIdentification())
                .names(client.getNames())
                .surnames(client.getSurnames())
                .age(client.getAge())
                .dateOfBirth(client.getDateOfBirth())
                .address(client.getAddress())
                .maritalStatus(client.getMaritalStatus())
                .spouseIdentification(client.getSpouseIdentification())
                .spouseName(client.getSpouseName())
                .build();
    }

    public CarDto mapCar(CarEntity car){
        return CarDto.builder()
                .idCar(car.getIdCar())
                .plate(car.getPlate())
                .model(car.getModel())
                .chassisNumber(car.getChassisNumber())
                .type(car.getType())
                .displacement(car.getDisplacement())
                .appraisal(car.getAppraisal())
                .status(car.getStatus())
                .idBrand(car.getBrandEntity().getIdBrand())
                .build();
    }
}
