package com.bancopichincha.credito.automotriz.service;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import com.bancopichincha.credito.automotriz.model.dto.assignment.AssignmentDto;

public interface AssignmentService {

    CommonResponseDto createAssignment(AssignmentDto assignment);
}
