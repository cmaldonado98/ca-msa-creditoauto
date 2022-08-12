package com.bancopichincha.credito.automotriz.model.dto.creditApplication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CreditApplicationStatusRequestDto {

    @NotNull(message = "Error id credit application cannot be null")
    Long idCreditApplication;

    @NotNull(message = "Error status cannot be null")
    String status;
}
