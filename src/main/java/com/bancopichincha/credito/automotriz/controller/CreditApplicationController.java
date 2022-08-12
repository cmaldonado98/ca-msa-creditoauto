package com.bancopichincha.credito.automotriz.controller;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.creditApplication.CreditApplicationDto;
import com.bancopichincha.credito.automotriz.model.dto.creditApplication.CreditApplicationStatusRequestDto;
import com.bancopichincha.credito.automotriz.service.CreditApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;

@RestController
@RequestMapping("/credit-application")
@AllArgsConstructor
public class CreditApplicationController {

    private final CreditApplicationService creditApplicationService;

    @PostMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> createCreditApplication(@Valid @RequestBody CreditApplicationDto creditApplication) {
        creditApplication.setIdCreditApplication(null);
        return ResponseEntity.status(HttpStatus.OK).body(creditApplicationService.createCreditApplication(creditApplication));
    }

    @PutMapping
    @Produces("application/json")
    public ResponseEntity<CommonResponseDto> updateCreditApplicationStatus(@Valid @RequestBody CreditApplicationStatusRequestDto creditApplication) {
        return ResponseEntity.status(HttpStatus.OK).body(creditApplicationService.updateCreditApplicationStatus(creditApplication));
    }

}
