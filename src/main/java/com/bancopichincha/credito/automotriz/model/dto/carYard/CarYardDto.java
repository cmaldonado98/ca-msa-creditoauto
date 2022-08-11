package com.bancopichincha.credito.automotriz.model.dto.carYard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CarYardDto {

    Long idCarYard;

    @NotNull(message = "Error name cannot be null")
    String name;

    @NotNull(message = "Error address cannot be null")
    String address;

    @NotNull(message = "Error phone cannot be null")
    String phone;

    @NotNull(message = "Error number sales point cannot be null")
    Long numberSalesPoint;
}
