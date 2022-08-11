package com.bancopichincha.credito.automotriz.model.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ClientDto {

    Long clientId;
    @NotNull(message = "Error identification cannot be null")
    String identification;
    @NotNull(message = "Error names cannot be null")
    String names;
    @NotNull(message = "Error surnames cannot be null")
    String surnames;
    @NotNull(message = "Error age cannot be null")
    Long age;
    @NotNull(message = "Error date of birth cannot be null")
    LocalDateTime dateOfBirth;
    @NotNull(message = "Error address cannot be null")
    String address;
    @NotNull(message = "Error marital status cannot be null")
    String maritalStatus;
    @NotNull(message = "Error spouse identification cannot be null")
    String spouseIdentification;
    @NotNull(message = "Error spouse name cannot be null")
    String spouseName;
}
