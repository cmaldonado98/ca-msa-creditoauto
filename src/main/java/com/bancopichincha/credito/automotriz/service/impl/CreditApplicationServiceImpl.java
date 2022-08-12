package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.exception.ApplicationException;
import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.assignment.AssignmentDto;
import com.bancopichincha.credito.automotriz.model.dto.creditApplication.CreditApplicationDto;
import com.bancopichincha.credito.automotriz.model.dto.creditApplication.CreditApplicationStatusRequestDto;
import com.bancopichincha.credito.automotriz.model.entities.*;
import com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode;
import com.bancopichincha.credito.automotriz.repository.*;
import com.bancopichincha.credito.automotriz.service.AssignmentService;
import com.bancopichincha.credito.automotriz.service.CarService;
import com.bancopichincha.credito.automotriz.service.CreditApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode.OK;
import static com.bancopichincha.credito.automotriz.util.Constant.*;

@Service
@Log4j2
@AllArgsConstructor
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private final CreditApplicationRepository creditApplicationRepository;

    private final ClientRepository clientRepository;

    private final CarYardRepository carYardRepository;

    private final CarRepository carRepository;
    private final ExecutiveRepository executiveRepository;
    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;
    private final CarService carService;

    @Override
    @Transactional
    public CommonResponseDto createCreditApplication(CreditApplicationDto creditApplication) {
        log.info(String.format("Creating credit application for client id: %s", creditApplication.getIdClient().toString()));
        ClientEntity clientEntity = clientRepository.findById(creditApplication.getIdClient())
                .orElseThrow(() -> {
                    log.error(String.format("Client not found id: %s", creditApplication.getIdClient().toString()));
                    throw new ApplicationException(ResponseStatusCode.CLIENT_DOES_NOT_EXISTS);
                });

        CarYardEntity carYardEntity = carYardRepository.findById(creditApplication.getIdCarYard())
                .orElseThrow(() -> {
                    log.error(String.format("Car yard not found id: %s", creditApplication.getIdCarYard().toString()));
                    throw new ApplicationException(ResponseStatusCode.CAR_YARD_DOES_NOT_EXISTS);
                });
        CarEntity carEntity = carRepository.findById(creditApplication.getIdCar())
                .orElseThrow(() -> {
                    log.error(String.format("Car not found id: %s", creditApplication.getIdCar().toString()));
                    throw new ApplicationException(ResponseStatusCode.CAR_DOES_NOT_EXISTS);
                });

        ExecutiveEntity executiveEntity = executiveRepository.findById(creditApplication.getIdExecutive())
                .orElseThrow(() -> {
                    log.error(String.format("Executive not found id: %s", creditApplication.getIdExecutive().toString()));
                    throw new ApplicationException(ResponseStatusCode.EXECUTIVE_DOES_NOT_EXISTS);
                });

        if (!executiveEntity.getCarYardEntity().getIdCarYard().equals(creditApplication.getIdCarYard())) {
            log.error(String.format("Executive not work in car yard id: %s", creditApplication.getIdCarYard().toString()));
            throw new ApplicationException(ResponseStatusCode.EXECUTIVE_DOES_NOT_WORK_IN_CAR_YARD);
        }

        if (carEntity.getStatus().equalsIgnoreCase(CAR_RESERVED)) {
            log.error(String.format("Car with ID: %s is reserved", creditApplication.getIdCar().toString()));
            throw new ApplicationException(ResponseStatusCode.CAR_IS_RESERVED);
        }

        List<CreditApplicationEntity> creditApplicationEntityList = creditApplicationRepository.findByClientEntity_IdClient(creditApplication.getIdClient());
        creditApplicationEntityList.forEach(credit -> {
            if (credit.getDateCreate().toLocalDate().equals(LocalDate.now()) && (credit.getStatus().equalsIgnoreCase(INIT_STATE_CREDIT_APPLICATION))) {
                log.error(String.format("Error Credit application for client id: %s on this date: %s", creditApplication.getIdClient().toString(), LocalDate.now()));
                throw new ApplicationException(ResponseStatusCode.CREDIT_APPLICATION_EXIST_FOR_DATE);
            }
        });

        CreditApplicationEntity newCreditApplication = new CreditApplicationEntity();

        newCreditApplication.setDateCreate(LocalDateTime.now());
        newCreditApplication.setMonthsTerm(creditApplication.getMonthsTerm());
        newCreditApplication.setFees(creditApplication.getFees());
        newCreditApplication.setEntranceFee(creditApplication.getEntranceFee());
        newCreditApplication.setObservation(creditApplication.getObservation());
        newCreditApplication.setStatus(INIT_STATE_CREDIT_APPLICATION);
        newCreditApplication.setClientEntity(clientEntity);
        newCreditApplication.setCarYardEntity(carYardEntity);
        newCreditApplication.setCarEntity(carEntity);
        newCreditApplication.setExecutiveEntity(executiveEntity);

        newCreditApplication = creditApplicationRepository.save(newCreditApplication);

        assignmentService.createAssignment(AssignmentDto.builder()
                .carYardId(creditApplication.getIdCarYard())
                .clientId(creditApplication.getIdClient())
                .build());

        return CommonResponseDto.builder()
                .code(OK.getCode())
                .message(OK.getMessage())
                .response(String.format("ID: %s", newCreditApplication.getIdCreditApplication()))
                .build();
    }

    @Override
    public CommonResponseDto updateCreditApplicationStatus(CreditApplicationStatusRequestDto creditApplication) {
        log.info(String.format("Updating credit application status with id: %s", creditApplication.getIdCreditApplication().toString()));

        CreditApplicationEntity creditApplicationEntity = creditApplicationRepository.findById(creditApplication.getIdCreditApplication())
                .orElseThrow(() -> {
                    log.error(String.format("Credit Application to be updated not found id %s", creditApplication.getIdCreditApplication().toString()));
                    throw new ApplicationException(ResponseStatusCode.CREDIT_APPLICATION_DOES_NOT_EXISTS);
                });

        if (creditApplication.getStatus().equalsIgnoreCase(CANCELLED_CREDIT_APPLICATION)) {
            AssignmentEntity assignmentEntity = assignmentRepository.findByClient_IdClient(creditApplicationEntity.getClientEntity().getIdClient())
                    .orElseThrow(() -> {
                        log.error(String.format("Assignment not found by client id: %s", creditApplicationEntity.getClientEntity().getIdClient()));
                        return new ApplicationException(ResponseStatusCode.ASSIGNMENT_DOES_NOT_EXISTS);
                    });
            CarEntity carEntity = carRepository.findById(creditApplicationEntity.getCarEntity().getIdCar())
                    .orElseThrow(() -> {
                        log.error(String.format("Car not found id: %s", creditApplicationEntity.getCarEntity().getIdCar()));
                        return new ApplicationException(ResponseStatusCode.CAR_DOES_NOT_EXISTS);
                    });
            assignmentService.deleteAssignment(assignmentEntity.getIdAssignment());
            carEntity.setStatus(CAR_RELEASED);
            carRepository.save(carEntity);
        }

        creditApplicationEntity.setDateCreate(LocalDateTime.now());
        creditApplicationEntity.setStatus(creditApplication.getStatus());

        creditApplicationRepository.save(creditApplicationEntity);

        return CommonResponseDto.build(OK);
    }
}
