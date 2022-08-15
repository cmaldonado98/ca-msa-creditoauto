package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.carYard.CarYardDto;
import com.bancopichincha.credito.automotriz.model.entities.CarYardEntity;
import com.bancopichincha.credito.automotriz.service.CarYardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.List;

@RestController
@RequestMapping("/car-yard")
@AllArgsConstructor
public class CarYardController {

    private final CarYardService carYardService;

    @GetMapping("/all")
    @Produces("application/json")
    public ResponseEntity<List<CarYardEntity>> getAllCarYards(){
        return ResponseEntity.status(HttpStatus.OK).body(carYardService.getAllCarYards());
    }

    @GetMapping("/{id}")
    @Produces("application/json")
    public ResponseEntity<CarYardEntity> getCarYardById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(carYardService.getCarYardById(id));
    }

    @PostMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> createCarYard(@Valid @RequestBody CarYardDto carYard){
        carYard.setIdCarYard(null);
        return ResponseEntity.status(HttpStatus.OK).body(carYardService.createCarYard(carYard));
    }

    @PutMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> updateCarYard(@Valid @RequestBody CarYardDto carYard) {
        return ResponseEntity.status(HttpStatus.OK).body(carYardService.updateCarYard(carYard));
    }

    @DeleteMapping("/{id}")
    @Produces("application/json")
    @Transactional
    public ResponseEntity<CommonResponseDto> deleteCarYard(@Valid @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carYardService.deleteCarYard(id));
    }
}
