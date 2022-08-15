package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.car.CarDto;
import com.bancopichincha.credito.automotriz.model.entities.CarEntity;
import com.bancopichincha.credito.automotriz.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.List;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/all")
    @Produces("application/json")
    public ResponseEntity<List<CarEntity>> getAllCars(){
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCars());
    }

    @GetMapping("/{id}")
    @Produces("application/json")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarById(id));
    }
    @PostMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> createCar(@Valid @RequestBody CarDto car){
        car.setIdCar(null);
        return ResponseEntity.status(HttpStatus.OK).body(carService.createCar(car));
    }

    @PutMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> updateCar(@Valid @RequestBody CarDto car) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.updateCar(car));
    }

    @DeleteMapping("/{id}")
    @Produces("application/json")
    @Transactional
    public ResponseEntity<CommonResponseDto> deleteCar(@Valid @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.deleteCar(id));
    }
}
