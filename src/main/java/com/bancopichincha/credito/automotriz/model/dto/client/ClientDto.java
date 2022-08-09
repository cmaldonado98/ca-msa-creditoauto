package com.bancopichincha.credito.automotriz.model.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ClientDto {
    Long clientId;
    String identification;
    String names;
    String surnames;
    Long age;
    LocalDateTime dateOfBirth;
    String address;
    String maritalStatus;
    String spouseIdentification;
    String spouseName;
}
