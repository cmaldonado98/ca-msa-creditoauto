package com.bancopichincha.credito.automotriz.model.dto.carYard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CarYardDto {
    Long idCarYard;
    String name;
    String address;
    String phone;
        String numberSalesPoint;
}
