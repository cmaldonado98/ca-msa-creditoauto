package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.client.ClientDto;
import com.bancopichincha.credito.automotriz.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> createClient(@Validated @RequestBody ClientDto client) {
        client.setClientId(null);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.createClient(client));
    }

    @PutMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> updateClient(@Validated @RequestBody ClientDto client) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(client));
    }

    @DeleteMapping("/{id}")
    @Produces("application/json")
    @Transactional
    public ResponseEntity<CommonResponseDto> deleteClient(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.deleteClient(id));
    }
}
