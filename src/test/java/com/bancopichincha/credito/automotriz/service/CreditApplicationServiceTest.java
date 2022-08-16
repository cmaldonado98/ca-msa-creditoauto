package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.exception.ApplicationException;
import com.bancopichincha.credito.automotriz.model.dto.creditApplication.CreditApplicationDto;
import com.bancopichincha.credito.automotriz.model.dto.creditApplication.CreditApplicationStatusRequestDto;
import com.bancopichincha.credito.automotriz.model.entities.CarEntity;
import com.bancopichincha.credito.automotriz.model.entities.CarYardEntity;
import com.bancopichincha.credito.automotriz.model.entities.CreditApplicationEntity;
import com.bancopichincha.credito.automotriz.model.entities.ExecutiveEntity;
import com.bancopichincha.credito.automotriz.repository.*;
import com.bancopichincha.credito.automotriz.service.impl.CreditApplicationServiceImpl;
import com.bancopichincha.credito.automotriz.util.DataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bancopichincha.credito.automotriz.util.Constant.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CreditApplicationServiceImpl.class)
class CreditApplicationServiceTest {

    @MockBean
    private CreditApplicationRepository creditApplicationRepository;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private CarYardRepository carYardRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private ExecutiveRepository executiveRepository;

    @MockBean
    private AssignmentService assignmentService;

    @MockBean
    private AssignmentRepository assignmentRepository;

    @Autowired
    private CreditApplicationService creditApplicationService;

    private CreditApplicationDto getCreditApplication() {
        return CreditApplicationDto.builder()
                .monthsTerm(12L)
                .fees(100.00)
                .entranceFee(10.00)
                .observation("Test")
                .status("Register")
                .idClient(1L)
                .idCar(1L)
                .idExecutive(1L)
                .idCarYard(1L)
                .build();
    }

    private CreditApplicationStatusRequestDto getCreditApplicationStatusDispatched() {
        return CreditApplicationStatusRequestDto.builder()
                .idCreditApplication(1L)
                .status(DISPATCHED_CREDIT_APPLICATION)
                .build();
    }

    private CreditApplicationStatusRequestDto getCreditApplicationStatusCancelled() {
        return CreditApplicationStatusRequestDto.builder()
                .idCreditApplication(1L)
                .status(CANCELLED_CREDIT_APPLICATION)
                .build();
    }

    void mockClientOK() {
        when(clientRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(DataMock.getClient()));
    }

    void mockCarYardOK() {
        when(carYardRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(DataMock.getCarYard()));
    }

    void mockCarOk() {
        when(carRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(DataMock.getCar()));
    }

    void mockExecutiveOK() {
        when(executiveRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(DataMock.getExecutive()));
    }

    void mockSaveCreditApplicationOK() {
        when(creditApplicationRepository.save(Mockito.any(CreditApplicationEntity.class)))
                .thenReturn(DataMock.getCreditApplicationRegister());
    }

    void mockFindCreditApplicationOK() {
        when(creditApplicationRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(DataMock.getCreditApplicationRegister()));
    }

    void mockFindAssignmentByClientIdOK() {
        when(assignmentRepository.findByClient_IdClient(Mockito.anyLong()))
                .thenReturn(Optional.of(DataMock.getAssignment()));
    }

    @Test
    void whenCreateCreditApplicationOK() {
        List<CreditApplicationEntity> creditApplicationEntityList = new ArrayList<>();
        creditApplicationEntityList.add(DataMock.getCreditApplicationCancelled());
        mockClientOK();
        mockCarYardOK();
        mockCarOk();
        mockExecutiveOK();
        when(creditApplicationRepository.findByClientEntity_IdClient(Mockito.anyLong()))
                .thenReturn(creditApplicationEntityList);
        mockSaveCreditApplicationOK();

        Assertions.assertDoesNotThrow(() -> creditApplicationService.createCreditApplication(getCreditApplication()));
    }

    @Test
    void whenCreateCreditApplicationClientNotFound() {
        when(clientRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.createCreditApplication(getCreditApplication()));
    }

    @Test
    void whenCreateCreditApplicationCarYardNotFound() {
        mockClientOK();
        when(carYardRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.createCreditApplication(getCreditApplication()));
    }

    @Test
    void whenCreateCreditApplicationCarNotFound() {
        mockClientOK();
        mockCarYardOK();
        when(carRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.createCreditApplication(getCreditApplication()));
    }

    @Test
    void whenCreateCreditApplicationExecutiveNotFound() {
        mockClientOK();
        mockCarYardOK();
        mockCarOk();
        when(executiveRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.createCreditApplication(getCreditApplication()));
    }

    @Test
    void whenCreateCreditApplicationExecutiveNotAssignedCorrectCarYard() {
        mockClientOK();
        CarYardEntity carYardEntity = DataMock.getCarYard();
        carYardEntity.setIdCarYard(2L);
        when(carYardRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(carYardEntity));
        mockCarOk();
        ExecutiveEntity executiveEntity = DataMock.getExecutive();
        executiveEntity.setCarYardEntity(carYardEntity);
        when(executiveRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(executiveEntity));

        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.createCreditApplication(getCreditApplication()));
    }

    @Test
    void whenCreateCreditApplicationCarReserved() {
        mockClientOK();
        mockCarYardOK();
        CarEntity carEntity = DataMock.getCar();
        carEntity.setStatus(CAR_RESERVED);
        when(carRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(carEntity));
        mockExecutiveOK();

        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.createCreditApplication(getCreditApplication()));
    }

    @Test
    void whenCreateCreditApplicationClientHasRegisterApplication() {
        List<CreditApplicationEntity> creditApplicationEntityList = new ArrayList<>();
        creditApplicationEntityList.add(DataMock.getCreditApplicationRepeat());
        mockClientOK();
        mockCarYardOK();
        mockCarOk();
        mockExecutiveOK();
        when(creditApplicationRepository.findByClientEntity_IdClient(Mockito.anyLong()))
                .thenReturn(creditApplicationEntityList);

        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.createCreditApplication(getCreditApplication()));
    }


    @Test
    void whenUpdateCreditApplicationStatus() {
        mockFindCreditApplicationOK();
        Assertions.assertDoesNotThrow(() -> creditApplicationService.updateCreditApplicationStatus
                (getCreditApplicationStatusDispatched()));
    }

    @Test
    void whenUpdateCreditApplicationStatusCreditApplicationNotFound() {
        when(creditApplicationRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.updateCreditApplicationStatus
                (getCreditApplicationStatusDispatched()));
    }

    @Test
    void whenUpdateCreditApplicationStatusCancelledAssignmentNotFound() {
        mockFindCreditApplicationOK();
        when(assignmentRepository.findByClient_IdClient(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.updateCreditApplicationStatus
                (getCreditApplicationStatusCancelled()));
    }

    @Test
    void whenUpdateCreditApplicationStatusCancelledCarNotFound() {
        mockFindCreditApplicationOK();
        mockFindAssignmentByClientIdOK();
        when(carRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ApplicationException.class, () -> creditApplicationService.updateCreditApplicationStatus
                (getCreditApplicationStatusCancelled()));
    }

    @Test
    void whenUpdatedCreditApplicationStatusCancelledOK() {
        mockFindCreditApplicationOK();
        mockFindAssignmentByClientIdOK();
        mockCarOk();
        Assertions.assertDoesNotThrow(() -> creditApplicationService.updateCreditApplicationStatus
                (getCreditApplicationStatusCancelled()));
    }

}