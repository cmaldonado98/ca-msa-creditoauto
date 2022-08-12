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
public class CreditApplicationDto {

    Long idCreditApplication;

    @NotNull(message = "Error months term cannot be null")
    Long monthsTerm;

    @NotNull(message = "Error fees cannot be null")
    Double fees;

    @NotNull(message = "Error entrance fee cannot be null")
    Double entranceFee;

    @NotNull(message = "Error observation cannot be null")
    String observation;

    @NotNull(message = "Error status cannot be null")
    String status;

    @NotNull(message = "Error id client cannot be null")
    Long idClient;

    @NotNull(message = "Error id car cannot be null")
    Long idCar;

    @NotNull(message = "Error id executive cannot be null")
    Long idExecutive;

    @NotNull(message = "Error id car yard cannot be null")
    Long idCarYard;
}
