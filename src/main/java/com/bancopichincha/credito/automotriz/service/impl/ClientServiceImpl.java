package com.bancopichincha.credito.automotriz.service.impl;

import ch.qos.logback.core.net.server.Client;
import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.client.ClientDto;
import com.bancopichincha.credito.automotriz.model.entities.ClientEntity;
import com.bancopichincha.credito.automotriz.repository.ClientRepository;
import com.bancopichincha.credito.automotriz.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode.OK;

@Service
@AllArgsConstructor
@Log4j2
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public CommonResponseDto createClient(ClientDto client) {
        log.info(String.format("Creating client with name: %s and identification: %s", client.getNames(), client.getIdentification()));

        ClientEntity newClient = new ClientEntity();
        newClient.setIdentification(client.getIdentification());
        newClient.setNames(client.getNames());
        newClient.setSurnames(client.getSurnames());
        newClient.setAge(client.getAge());
        newClient.setDateOfBirth(client.getDateOfBirth());
        newClient.setAddress(client.getAddress());
        newClient.setMaritalStatus(client.getMaritalStatus());
        newClient.setSpouseIdentification(client.getSpouseIdentification());
        newClient.setSpouseName(client.getSpouseName());

        newClient = clientRepository.save(newClient);

        return CommonResponseDto.builder()
                .code(OK.getCode())
                .message(OK.getMessage())
                .response(String.format("ID: %s Name: %s", newClient.getIdClient(), newClient.getNames()))
                .build();
    }
}
