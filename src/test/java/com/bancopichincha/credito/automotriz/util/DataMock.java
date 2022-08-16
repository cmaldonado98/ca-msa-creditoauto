package com.bancopichincha.credito.automotriz.util;

import com.bancopichincha.credito.automotriz.model.entities.*;

import java.time.LocalDateTime;

import static com.bancopichincha.credito.automotriz.util.Constant.*;
import static com.bancopichincha.credito.automotriz.util.Util.getDateTime;

public class DataMock {

    public static ClientEntity getClient(){
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setIdClient(1L);
        clientEntity.setIdentification("1234567890");
        clientEntity.setNames("Carlos");
        clientEntity.setSurnames("Maldonado");
        clientEntity.setAge(23L);
        clientEntity.setDateOfBirth(getDateTime("1998-08-29 00:00:00"));
        clientEntity.setAddress("Quito");
        clientEntity.setMaritalStatus("Casado");
        clientEntity.setSpouseIdentification("9876543211");
        clientEntity.setSpouseName("Maria");
        return clientEntity;
    }

    public static CarYardEntity getCarYard(){
        CarYardEntity carYardEntity = new CarYardEntity();
        carYardEntity.setIdCarYard(1L);
        carYardEntity.setName("Patio Mock");
        carYardEntity.setAddress("Av Mock");
        carYardEntity.setPhone("09999999999");
        carYardEntity.setNumberSalesPoint(123L);
        return carYardEntity;
    }

    public static BrandEntity getBrand(){
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setIdBrand(1L);
        brandEntity.setName("MOCK");
        return brandEntity;
    }

    public static CarEntity getCar(){
        CarEntity carEntity = new CarEntity();
        carEntity.setIdCar(1L);
        carEntity.setPlate("MOCK-9999");
        carEntity.setModel("Mock");
        carEntity.setChassisNumber("12345");
        carEntity.setType("SUV");
        carEntity.setDisplacement(123.00);
        carEntity.setAppraisal(999.99);
        carEntity.setBrandEntity(getBrand());
        carEntity.setStatus(CAR_RELEASED);

        return carEntity;
    }

    public static ExecutiveEntity getExecutive(){
        ExecutiveEntity executiveEntity = new ExecutiveEntity();

        executiveEntity.setIdExecutive(1L);
        executiveEntity.setIdentification("1234567890");
        executiveEntity.setNames("Ejecutivo");
        executiveEntity.setSurnames("Mock");
        executiveEntity.setAddress("Av Mock");
        executiveEntity.setPhone("987654321");
        executiveEntity.setCellphone("0999999999");
        executiveEntity.setAge(25L);
        executiveEntity.setCarYardEntity(getCarYard());
        return executiveEntity;
    }

    public static CreditApplicationEntity getCreditApplicationRegister(){
        CreditApplicationEntity creditApplicationEntity = new CreditApplicationEntity();
        creditApplicationEntity.setIdCreditApplication(1L);
        creditApplicationEntity.setDateCreate(getDateTime("2000-01-01 00:00:00"));
        creditApplicationEntity.setMonthsTerm(12L);
        creditApplicationEntity.setFees(100.00);
        creditApplicationEntity.setEntranceFee(50.00);
        creditApplicationEntity.setObservation("Mock");
        creditApplicationEntity.setStatus(INIT_STATE_CREDIT_APPLICATION);
        creditApplicationEntity.setClientEntity(getClient());
        creditApplicationEntity.setCarYardEntity(getCarYard());
        creditApplicationEntity.setCarEntity(getCar());
        creditApplicationEntity.setExecutiveEntity(getExecutive());
        return creditApplicationEntity;
    }

    public static CreditApplicationEntity getCreditApplicationCancelled(){
        CreditApplicationEntity creditApplicationEntity = new CreditApplicationEntity();
        creditApplicationEntity.setIdCreditApplication(1L);
        creditApplicationEntity.setDateCreate(getDateTime("2000-01-01 00:00:00"));
        creditApplicationEntity.setMonthsTerm(12L);
        creditApplicationEntity.setFees(100.00);
        creditApplicationEntity.setEntranceFee(50.00);
        creditApplicationEntity.setObservation("Mock");
        creditApplicationEntity.setStatus(CANCELLED_CREDIT_APPLICATION);
        creditApplicationEntity.setClientEntity(getClient());
        creditApplicationEntity.setCarYardEntity(getCarYard());
        creditApplicationEntity.setCarEntity(getCar());
        creditApplicationEntity.setExecutiveEntity(getExecutive());
        return creditApplicationEntity;
    }

    public static CreditApplicationEntity getCreditApplicationRepeat(){
        CreditApplicationEntity creditApplicationEntity = new CreditApplicationEntity();
        creditApplicationEntity.setIdCreditApplication(1L);
        creditApplicationEntity.setDateCreate(LocalDateTime.now());
        creditApplicationEntity.setMonthsTerm(12L);
        creditApplicationEntity.setFees(100.00);
        creditApplicationEntity.setEntranceFee(50.00);
        creditApplicationEntity.setObservation("Mock");
        creditApplicationEntity.setStatus(INIT_STATE_CREDIT_APPLICATION);
        creditApplicationEntity.setClientEntity(getClient());
        creditApplicationEntity.setCarYardEntity(getCarYard());
        creditApplicationEntity.setCarEntity(getCar());
        creditApplicationEntity.setExecutiveEntity(getExecutive());
        return creditApplicationEntity;
    }

    public static AssignmentEntity getAssignment(){
        AssignmentEntity assignmentEntity = new AssignmentEntity();
        assignmentEntity.setIdAssignment(1L);
        assignmentEntity.setDateAssignment(LocalDateTime.now());
        assignmentEntity.setClient(getClient());
        assignmentEntity.setCarYardEntity(getCarYard());

        return assignmentEntity;
    }
}
