package com.bancopichincha.credito.automotriz.model.dto.car;

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
public class CarDto {

    Long idCar;

    @NotNull(message = "Error plate cannot be null")
    String plate;

    @NotNull(message = "Error model cannot be null")
    String model;

    @NotNull(message = "Error chassis number cannot be null")
    String chassisNumber;

    String type;

    @NotNull(message = "Error displacement cannot be null")
    Double displacement;

    @NotNull(message = "Error appraisal cannot be null")
    Double appraisal;

    @NotNull(message = "Error status cannot be null")
    String status;

    @NotNull(message = "Error id brand cannot be null")
    Long idBrand;
}
