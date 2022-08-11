package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.carYard.CarYardDto;
import com.bancopichincha.credito.automotriz.service.CarYardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/car-yard")
@AllArgsConstructor
public class CarYardController {

    private final CarYardService carYardService;

    @PostMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> createCarYard(@RequestBody CarYardDto carYard){
        carYard.setIdCarYard(null);
        return ResponseEntity.status(HttpStatus.OK).body(carYardService.createCarYard(carYard));
    }
}
