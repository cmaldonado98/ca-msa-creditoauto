package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.carYard.CarYardDto;
import com.bancopichincha.credito.automotriz.service.CarYardService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> updateCarYard(@RequestBody CarYardDto carYard) {
        return ResponseEntity.status(HttpStatus.OK).body(carYardService.updateCarYard(carYard));
    }

    @DeleteMapping("/{id}")
    @Produces("application/json")
    @Transactional
    public ResponseEntity<CommonResponseDto> deleteCarYard(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carYardService.deleteCarYard(id));
    }
}
