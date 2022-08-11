package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.client.ClientDto;
import com.bancopichincha.credito.automotriz.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> createClient(@RequestBody ClientDto client) {
        client.setClientId(null);

//        if (Boolean.TRUE.equals(StringUtils.isBlank(client.getNames())) || StringUtils.isBlank(client.getIdentification())) {
//            return ResponseEntity.status(HttpStatus.OK).body(CommonResponseDto.build(ResponseStatusCode.INVALID_PARAMETERS));
//        }

        return ResponseEntity.status(HttpStatus.OK).body(clientService.createClient(client));

    }
}
