package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.repository.*;
import com.bancopichincha.credito.automotriz.service.impl.CreditApplicationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class CreditApplicationServiceTest {

    @Mock
    private CreditApplicationRepository creditApplicationRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CarYardRepository carYardRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private ExecutiveRepository executiveRepository;

    @Mock
    private AssignmentService assignmentService;

    @Mock
    private AssignmentRepository assignmentRepository;

    @InjectMocks
    private CreditApplicationServiceImpl creditApplicationService;

    @Test
    void createCreditApplication() {
    }

    @Test
    void updateCreditApplicationStatus() {
    }
}