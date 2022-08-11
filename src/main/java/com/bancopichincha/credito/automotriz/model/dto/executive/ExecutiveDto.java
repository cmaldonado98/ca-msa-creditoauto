package com.bancopichincha.credito.automotriz.model.dto.executive;

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
public class ExecutiveDto {

    Long idExecutive;

    @NotNull(message = "Error identification cannot be null")
    String identification;

    @NotNull(message = "Error names cannot be null")
    String names;

    @NotNull(message = "Error surnames cannot be null")
    String surnames;

    @NotNull(message = "Error address cannot be null")
    String address;

    @NotNull(message = "Error phone cannot be null")
    String phone;

    @NotNull(message = "Error cellphone cannot be null")
    String cellphone;

    @NotNull(message = "Error age cannot be null")
    Long age;

    @NotNull(message = "Error idCarYard cannot be null")
    Long idCarYard;
}
