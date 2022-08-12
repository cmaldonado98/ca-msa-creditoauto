package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.creditApplication.CreditApplicationDto;
import com.bancopichincha.credito.automotriz.model.dto.creditApplication.CreditApplicationStatusRequestDto;

public interface CreditApplicationService {

    CommonResponseDto createCreditApplication(CreditApplicationDto creditApplication);
    CommonResponseDto updateCreditApplicationStatus(CreditApplicationStatusRequestDto creditApplication);
}
