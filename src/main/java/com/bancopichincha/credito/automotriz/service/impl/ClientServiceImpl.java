package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.exception.ApplicationException;
import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.client.ClientDto;
import com.bancopichincha.credito.automotriz.model.entities.ClientEntity;
import com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode;
import com.bancopichincha.credito.automotriz.repository.AssignmentRepository;
import com.bancopichincha.credito.automotriz.repository.ClientRepository;
import com.bancopichincha.credito.automotriz.repository.CreditApplicationRepository;
import com.bancopichincha.credito.automotriz.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode.OK;
import static com.bancopichincha.credito.automotriz.util.Util.getDateTime;

@Service
@AllArgsConstructor
@Log4j2
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AssignmentRepository assignmentRepository;
    private final CreditApplicationRepository creditApplicationRepository;

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

    @Override
    public CommonResponseDto updateClient(ClientDto client) {
        log.info(String.format("Updating client with id: %s", client.getClientId().toString()));
        ClientEntity clientEntity = clientRepository.findById(client.getClientId())
                .orElseThrow(() -> {
                    log.error(String.format("Client to be updated not found id: %s", client.getClientId().toString()));
                    return new ApplicationException(ResponseStatusCode.CLIENT_DOES_NOT_EXISTS);
                });

        clientEntity.setIdentification(client.getIdentification());
        clientEntity.setNames(client.getNames());
        clientEntity.setSurnames(client.getSurnames());
        clientEntity.setAge(client.getAge());
        clientEntity.setDateOfBirth(client.getDateOfBirth());
        clientEntity.setAddress(client.getAddress());
        clientEntity.setMaritalStatus(client.getMaritalStatus());
        clientEntity.setSpouseIdentification(client.getSpouseIdentification());
        clientEntity.setSpouseName(client.getSpouseName());
        clientRepository.save(clientEntity);

        return CommonResponseDto.build(OK);
    }

    @Override
    public CommonResponseDto deleteClient(Long clientId) {
        log.info(String.format("Deleting client with id: %s", clientId.toString()));

        if (assignmentRepository.findByClient_IdClient(clientId).isPresent() || creditApplicationRepository.existsByClientEntity_IdClient(clientId)) {
            throw new ApplicationException(ResponseStatusCode.CLIENT_HAS_ASSOCIATED_INFORMATION);
        }

        ClientEntity clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> {
                    log.error(String.format("Client to be deleted not found id: %s", clientId));
                    return new ApplicationException(ResponseStatusCode.CLIENT_DOES_NOT_EXISTS);
                });
        clientRepository.delete(clientEntity);
        return CommonResponseDto.build(OK);
    }

    @Override
    public void initData() throws IOException {
        log.info("LOAD CLIENT DATA FROM CSV");
        if (clientRepository.findAll().isEmpty()) {

            List<ClientEntity> clientEntities = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new ClassPathResource("files/clients.csv").getFile()));
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                ClientEntity clientEntity = new ClientEntity();
                clientEntity.setIdentification(values[0]);
                clientEntity.setNames(values[1]);
                clientEntity.setSurnames(values[2]);
                clientEntity.setAge(Long.valueOf(values[3]));
                clientEntity.setDateOfBirth(getDateTime(values[4]));
                clientEntity.setAddress(values[5]);
                clientEntity.setMaritalStatus(values[6]);
                clientEntity.setSpouseIdentification(values[7]);
                clientEntity.setSpouseName(values[8]);
                clientEntities.add(clientEntity);
            }
            clientRepository.saveAll(clientEntities);
        }
    }
}
