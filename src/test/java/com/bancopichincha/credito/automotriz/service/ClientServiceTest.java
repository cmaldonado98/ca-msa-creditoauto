package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.exception.ApplicationException;
import com.bancopichincha.credito.automotriz.mapper.GeneralMapper;
import com.bancopichincha.credito.automotriz.model.dto.client.ClientDto;
import com.bancopichincha.credito.automotriz.model.entities.ClientEntity;
import com.bancopichincha.credito.automotriz.repository.AssignmentRepository;
import com.bancopichincha.credito.automotriz.repository.ClientRepository;
import com.bancopichincha.credito.automotriz.repository.CreditApplicationRepository;
import com.bancopichincha.credito.automotriz.service.impl.ClientServiceImpl;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ClientServiceImpl.class)
class ClientServiceTest {

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private AssignmentRepository assignmentRepository;

    @MockBean
    private CreditApplicationRepository creditApplicationRepository;

    @MockBean
    private GeneralMapper mapper;

    @Autowired
    private ClientService clientService;

    @Test
    void whenGetAllClientsOK() {
        List<ClientEntity> clientEntityList = new ArrayList<>();
        clientEntityList.add(DataMock.getClient());
        when(clientRepository.findAll())
                .thenReturn(clientEntityList);

        Assertions.assertDoesNotThrow(() -> clientService.getAllClients());
    }

    @Test
    void whenGetClientByIdOK() {
        when(clientRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(DataMock.getClient()));

        Assertions.assertDoesNotThrow(() -> clientService.getClientById(1L));
    }

    @Test
    void whenGetClientByClientNotFound() {
        when(clientRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ApplicationException.class, () -> clientService.getClientById(1L));
    }

    @Test
    void whenCreateClientOK(){
        when(clientRepository.save(Mockito.any(ClientEntity.class)))
                .thenReturn(DataMock.getClient());

        Assertions.assertDoesNotThrow(() -> clientService.createClient(ClientDto.builder().build()));
    }

    @Test
    void whenUpdatedClientOK(){
        when(clientRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(DataMock.getClient()));

        Assertions.assertDoesNotThrow(() -> clientService.updateClient(ClientDto.builder()
                        .clientId(1L)
                .build()));
    }

    @Test
    void whenUpdatedClientNotFound(){
        when(clientRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ApplicationException.class, () -> clientService.updateClient(ClientDto.builder()
                        .clientId(1L)
                .build()));
    }

    @Test
    void whenDeletedClientOK(){
        when(assignmentRepository.findByClient_IdClient(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        when(creditApplicationRepository.existsByClientEntity_IdClient(Mockito.anyLong()))
                .thenReturn(Boolean.FALSE);
        when(clientRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(DataMock.getClient()));

        Assertions.assertDoesNotThrow(() -> clientService.deleteClient(1L));
    }

    @Test
    void whenInitDataOK(){
        List<ClientEntity> clientEntities = new ArrayList<>();
        when(clientRepository.findAll())
                .thenReturn(clientEntities);
        Assertions.assertDoesNotThrow(() -> clientService.initData());
    }
}