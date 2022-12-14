package com.bancopichincha.credito.automotriz.model.dto.assignment;

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
public class AssignmentDto{
    Long idAssignment;
    @NotNull(message = "Error client id cannot be null")
    Long clientId;
    @NotNull(message = "Error car yard id cannot be null")
    Long carYardId;
}
