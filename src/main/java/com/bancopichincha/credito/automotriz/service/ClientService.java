package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.client.ClientDto;

import java.io.IOException;
import java.util.List;

public interface ClientService {

    CommonResponseDto createClient(ClientDto client);
    CommonResponseDto updateClient(ClientDto client);
    CommonResponseDto deleteClient(Long clientId);
    List<ClientDto> getAllClients();

    ClientDto getClientById(Long id);
    void initData() throws IOException;
}
