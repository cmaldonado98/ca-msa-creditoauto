package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.client.ClientDto;

public interface ClientService {

    CommonResponseDto createClient(ClientDto client);
    CommonResponseDto updateClient(ClientDto client);
    CommonResponseDto deleteClient(Long clientId);
}
